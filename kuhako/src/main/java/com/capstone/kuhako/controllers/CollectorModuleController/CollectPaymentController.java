package com.capstone.kuhako.controllers.CollectorModuleController;

import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.CollectorModules.CollectPayments;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.repositories.CollectorRepository;
import com.capstone.kuhako.repositories.ResellerRepositories.ContractsRepository;
import com.capstone.kuhako.services.CollectorModuleServices.CollectPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/collector")
public class CollectPaymentController {
    @Autowired
    CollectPaymentService collectPaymentService;
    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private ContractsRepository contractsRepository;

    @PostMapping("/collectPayment/{collectorId}")
    public ResponseEntity<Object> createTransactions(@PathVariable Long collectorId, @RequestParam("contractId") Long contractId, @RequestParam("amountPayment") double amountPayment, @RequestParam("paymentType") String paymentType, @RequestParam("base64Data")String base64Data, @RequestParam("contentType")String contentType) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        Contracts contracts = contractsRepository.findById(contractId).orElse(null);
        if (collector != null && contracts != null) {
            if (contracts.getCollector().equals(collector)){
               Date currentDate = new Date();
                if(currentDate.before(contracts.getDueDate())){
                    if (contracts.getDebtRemaining() >= amountPayment){
                        byte[] data = Base64.getDecoder().decode(base64Data);
                        CollectPayments collectPayments = new CollectPayments();
                        collectPayments.setContracts(contracts);
                        collectPayments.setAmountPayments(amountPayment);
                        collectPayments.setPaymentType(paymentType);
                        collectPayments.setTransactionProof(data);
                        collectPayments.setTransactionProofContentType(contentType);
                        collectPaymentService.createCollectPayment(collectorId, collectPayments);
                        return new ResponseEntity<>("Payment created successfully", HttpStatus.CREATED);
                    }else {
                        return new ResponseEntity<>("Payment exceeds remaining debt", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Your contract is now on Dispute, You cannot pay this contract", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Collector is not in charge on this contract", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Collector/Contract does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/collectPayment", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectPayment() {
        return new ResponseEntity<>(collectPaymentService.getCollectPayment(), HttpStatus.OK);
    }

    @RequestMapping(value = "/collectPayment/{collectorId}/{collectPayments_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCollectPayment(@PathVariable Long collectorId, @PathVariable Long collectPayments_id) {
        return collectPaymentService.deleteCollectPayment(collectorId,collectPayments_id);
    }

    @RequestMapping(value="/collectPayment/{collectorId}/{collectPayments_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollectPayment(@PathVariable Long collectorId,@PathVariable Long collectPayments_id, @RequestBody CollectPayments collectPayments) {
        return collectPaymentService.updateCollectPayment(collectorId,collectPayments_id, collectPayments);
    }
}
