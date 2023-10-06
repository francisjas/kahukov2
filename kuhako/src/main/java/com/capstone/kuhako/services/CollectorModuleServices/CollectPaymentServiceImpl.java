package com.capstone.kuhako.services.CollectorModuleServices;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.ClientModules.PayDues;
import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.ResellerModule.ContractsHistory;
import com.capstone.kuhako.models.CollectorModules.CollectPayments;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.Reseller;
import com.capstone.kuhako.repositories.ClientModuleRepository.PayDuesRepository;
import com.capstone.kuhako.repositories.ClientRepository;
import com.capstone.kuhako.repositories.ResellerRepositories.ContractsHistoryRepository;
import com.capstone.kuhako.repositories.ResellerRepositories.ContractsRepository;
import com.capstone.kuhako.repositories.CollectorModuleRepository.CollectPaymentRepository;
import com.capstone.kuhako.repositories.CollectorRepository;
import com.capstone.kuhako.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class CollectPaymentServiceImpl implements CollectPaymentService {
    @Autowired
    private CollectPaymentRepository collectPaymentRepository;
    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private ContractsRepository contractsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ResellerRepository resellerRepository;
    @Autowired
    private ContractsHistoryRepository contractsHistoryRepository;
    @Autowired
    private PayDuesRepository payDuesRepository;

    // Create Transactions
    public void createCollectPayment(Long collectorId, CollectPayments collectPayments){
//        try {
            Collector collector = collectorRepository.findById(collectorId).get();
            Contracts contracts = contractsRepository.findById(collectPayments.getContracts().getContracts_id()).get();
            Client client = clientRepository.findById(contracts.getClient().getClient_id()).get();
            Reseller reseller = resellerRepository.findById(contracts.getReseller().getReseller_id()).get();
            collectPayments.setCollector(collector);
            collectPayments.setContractsHistory(null);
            Date date = new Date();
            collectPayments.setTransactionDate(date);
            contracts.setDebtRemaining(contracts.getDebtRemaining() - collectPayments.getAmountPayments());
            contractsRepository.save(contracts);
//            if (!file.isEmpty()) {
//                // Get the bytes and content type of the uploaded file
//                byte[] fileData = file.getBytes();
//                String contentType = file.getContentType();
//
//                // Set the transaction proof and content type
//                transactions.setTransactionProof(fileData);
//                transactions.setTransactionProofContentType(contentType);
//            }
            collectPaymentRepository.save(collectPayments);
            if (contracts.getDebtRemaining() == 0) {
                ContractsHistory contractsHistory = new ContractsHistory(
                        contracts.getReseller(),
                        contracts.getClient(),
                        contracts.getCollector(),
                        contracts.getItemName(),
                        contracts.getItemPrice(),
                        contracts.getDueDate(),
                        contracts.getPaymentType(),
                        contracts.getSpecifications(),
                        new HashSet<>(contracts.getTransactions()),
                        new HashSet<>(contracts.getPayDues())
                );
                contractsHistoryRepository.save(contractsHistory);
                client.setReseller(null);
                client.setCollector(null);
                reseller.getClients().remove(client);
                collector.getClients().remove(client);
                client.setContract(null);
                reseller.getContracts().remove(contracts);
                collector.getContracts().remove(contracts);
                List<CollectPayments> collectPaymentsList = collectPaymentRepository.findByContracts(contracts);
                for (CollectPayments transaction : collectPaymentsList) {
                    transaction.setContractsHistory(contractsHistory);
                    transaction.setContracts(null);
                    collectPaymentRepository.save(transaction);
                }
                List<PayDues> payDuesList = payDuesRepository.findByContracts(contracts);
                for (PayDues payDue : payDuesList) {
                    payDue.setContractsHistory(contractsHistory);
                    payDue.setContracts(null);
                    payDuesRepository.save(payDue);
                }
                // Remove the contract and its transactions
                contractsRepository.delete(contracts);
            }
//        } catch (IOException e) {
//            // Handle any IO exceptions if they occur during file processing
//            e.printStackTrace(); // You can log the error or handle it as needed
//        }
    }


    // Get all Collector
    public Iterable<CollectPayments> getCollectPayment(){
        return collectPaymentRepository.findAll();
    }

    public Iterable<CollectPayments>getCollectPaymentByResellerId(Long collectorId){
        return collectPaymentRepository.findCollectPaymentsByCollectorId(collectorId);
    }


    // delete Collectors

    public ResponseEntity deleteCollectPayment(Long collectorId, Long id){
        CollectPayments collectPaymentsToDelete = collectPaymentRepository.findById(id).orElse(null);
        if (collectPaymentsToDelete != null && collectPaymentsToDelete.getCollector().getCollector_id().equals(collectorId)) {
            collectPaymentRepository.deleteById(id);
            return new ResponseEntity<>("Transactions Deleted Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Transactions Not Found",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity updateCollectPayment(Long collectorId, Long id, CollectPayments collectPayments){
        CollectPayments collectPaymentsForUpdate = collectPaymentRepository.findById(id).orElse(null);
        if (collectPaymentsForUpdate != null && collectPaymentsForUpdate.getCollector().getCollector_id().equals(collectorId)){
//            transactionsForUpdate.setCollectionStatus(transactions.getCollectionStatus());
//            transactionsForUpdate.setRequiredCollectibles(transactions.getRequiredCollectibles());
            collectPaymentRepository.save(collectPaymentsForUpdate);
            return new ResponseEntity("Transactions updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Transactions updated successfully", HttpStatus.NOT_FOUND);
    }
}
