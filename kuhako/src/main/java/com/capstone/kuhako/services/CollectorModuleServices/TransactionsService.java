package com.capstone.kuhako.services.CollectorModuleServices;

import com.capstone.kuhako.models.ResellerModule.Transactions;
import org.springframework.http.ResponseEntity;

public interface TransactionsService {
    void createTransactions(Long collectorId, Transactions transactions);

    Iterable<Transactions> getTransactions();

//    Iterable<Transactions>getTransactionsByResellerId(Long collectorId);

    ResponseEntity deleteTransactions(Long collectorId, Long id);

    ResponseEntity updateTransactions(Long collectorId, Long id, Transactions transactions);
}
