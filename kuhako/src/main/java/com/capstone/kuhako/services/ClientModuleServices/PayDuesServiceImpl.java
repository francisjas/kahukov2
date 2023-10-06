package com.capstone.kuhako.services.ClientModuleServices;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.ClientModules.PayDues;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.ResellerModule.ContractsHistory;
import com.capstone.kuhako.models.CollectorModules.CollectPayments;
import com.capstone.kuhako.models.Reseller;
import com.capstone.kuhako.repositories.ClientModuleRepository.PayDuesRepository;
import com.capstone.kuhako.repositories.ClientModuleRepository.TransactionHistoryRepository;
import com.capstone.kuhako.repositories.ClientRepository;
import com.capstone.kuhako.repositories.CollectorRepository;
import com.capstone.kuhako.repositories.ResellerRepositories.ContractsHistoryRepository;
import com.capstone.kuhako.repositories.ResellerRepositories.ContractsRepository;
import com.capstone.kuhako.repositories.CollectorModuleRepository.CollectPaymentRepository;
import com.capstone.kuhako.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class PayDuesServiceImpl implements PayDuesService{
    @Autowired
    private PayDuesRepository payDuesRepository;
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
    private CollectPaymentRepository collectPaymentRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    
    public void createPayDues(Long clientId, PayDues payDues){
        /*try {*/
            Client client = clientRepository.findById(clientId).get();
            Contracts contracts = contractsRepository.findById(payDues.getContracts().getContracts_id()).get();
            Collector collector = collectorRepository.findById(contracts.getCollector().getCollector_id()).get();
            Reseller reseller = resellerRepository.findById(contracts.getReseller().getReseller_id()).get();
            payDues.setClient(client);
            contracts.setDebtRemaining(contracts.getDebtRemaining() - payDues.getAmountPayment());
            contractsRepository.save(contracts);
            Date date = new Date();
            payDues.setPayDate(date);

        /*    if (!file.isEmpty()) {
                // Get the bytes and content type of the uploaded file
                byte[] fileData = file.getBytes();
                String contentType = file.getContentType();

                // Set the transaction proof and content type
                payDues.setTransactionProof(fileData);
                payDues.setTransactionProofContentType(contentType);
            }*/
            payDuesRepository.save(payDues);
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
                List<PayDues> payDuesList = payDuesRepository.findByContracts(contracts);
                for (PayDues payDue : payDuesList) {
                    payDue.setContractsHistory(contractsHistory);
                    payDue.setContracts(null);
                    payDuesRepository.save(payDue);
                }
                List<CollectPayments> collectPaymentsList = collectPaymentRepository.findByContracts(contracts);
                for (CollectPayments transaction : collectPaymentsList) {
                    transaction.setContractsHistory(contractsHistory);
                    transaction.setContracts(null);
                    collectPaymentRepository.save(transaction);
                }
                contractsRepository.delete(contracts);
            }
       /* }catch (IOException e){

            e.printStackTrace();
        }*/
    }

    public Iterable<PayDues> getPayDues(){
        return payDuesRepository.findAll();
    }
    
    public Iterable<PayDues> getPayDuesByClientId(Long clientId) {
        return payDuesRepository.findPayDuesByClientId(clientId);
    }
    public ResponseEntity deletePayDues(Long clientId,Long id){
        PayDues payDuesToDelete = payDuesRepository.findById(id).orElse(null);

        if (payDuesToDelete != null && payDuesToDelete.getClient().getClient_id().equals(clientId)) {
            payDuesRepository.deleteById(id);
            return new ResponseEntity<>("Pay Dues Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pay Dues not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updatePayDues(Long clientId,Long id, PayDues payDues){
        PayDues payDuesForUpdate = payDuesRepository.findById(id).orElse(null);
        if (payDuesForUpdate != null && payDuesForUpdate.getClient().getClient_id().equals(clientId)) {
            payDuesForUpdate.setAmountPayment(payDues.getAmountPayment());
            payDuesForUpdate.setReferenceNumber(payDues.getReferenceNumber());
            payDuesForUpdate.setPaymentType(payDues.getPaymentType());
            payDuesForUpdate.setTransactionProof(payDues.getTransactionProof());
            payDuesRepository.save(payDuesForUpdate);
            return new ResponseEntity<>("Pay Dues Updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Pay Dues not found",HttpStatus.NOT_FOUND);
    }
}
