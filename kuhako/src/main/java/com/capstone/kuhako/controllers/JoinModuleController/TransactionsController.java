package com.capstone.kuhako.controllers.JoinModuleController;

import com.capstone.kuhako.models.JoinModule.Contracts;
import com.capstone.kuhako.models.JoinModule.Transactions;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.repositories.CollectorRepository;
import com.capstone.kuhako.repositories.JoinModuleRepository.ContractsRepository;
import com.capstone.kuhako.services.JoinModuleServices.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class TransactionsController {
    @Autowired
    TransactionsService transactionsService;
    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private ContractsRepository contractsRepository;

    @RequestMapping(value="/transactions/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createTransactions(@PathVariable Long collectorId, @RequestBody Transactions transactions) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        Contracts contracts = contractsRepository.findById(transactions.getContracts().getContracts_id()).orElse(null);
        if (collector != null && contracts != null) {
            if (contracts.getCollector().equals(collector)){
                if (contracts.getDebtRemaining() >= transactions.getAmountPayments()){
                    transactionsService.createTransactions(collectorId, transactions);
                    return new ResponseEntity<>("Transactions created successfully", HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("Payment exceeds remaining debt", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Collector is not in charge on this contract", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Collector/Contract does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/transactions", method = RequestMethod.GET)
    public ResponseEntity<Object> getTransactions() {
        return new ResponseEntity<>(transactionsService.getTransactions(), HttpStatus.OK);
    }

//    @RequestMapping(value="/transactions/collector/{collectorId}", method = RequestMethod.GET)
//    public ResponseEntity<Object> getTransactionsByCollectorId(@PathVariable Long collectorId) {
//        return new ResponseEntity<>(transactionsService.getTransactionsByCollectorId(collectorId), HttpStatus.OK);
//    }

    @RequestMapping(value = "/transactions/{collectorId}/{transactions_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransactions(@PathVariable Long collectorId, @PathVariable Long transactions_id) {
        return transactionsService.deleteTransactions(collectorId,transactions_id);
    }

    @RequestMapping(value="/transactions/{collectorId}/{transactions_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTransactions(@PathVariable Long collectorId,@PathVariable Long transactions_id, @RequestBody Transactions transactions) {
        return transactionsService.updateTransactions(collectorId,transactions_id, transactions);
    }
}
