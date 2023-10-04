package com.capstone.kuhako.services.JoinModuleServices;

import com.capstone.kuhako.models.JoinModule.Transactions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface TransactionsService {
    void createTransactions(Long collectorId, Transactions transactions);

    Iterable<Transactions> getTransactions();

//    Iterable<Transactions>getTransactionsByResellerId(Long collectorId);

    ResponseEntity deleteTransactions(Long collectorId, Long id);

    ResponseEntity updateTransactions(Long collectorId, Long id, Transactions transactions);
}
