package com.capstone.kuhako.services.ClientModuleServices;

import com.capstone.kuhako.models.ClientModules.DuePayments;
import com.capstone.kuhako.models.ClientModules.PayDues;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface PayDuesService {
    void createPayDues(Long clientId, PayDues payDues);

    Iterable<PayDues> getPayDues();

    Iterable<PayDues>getPayDuesByClientId(Long clientId);

    ResponseEntity deletePayDues(Long clientId, Long id);

    ResponseEntity updatePayDues(Long clientId,Long id, PayDues payDues);
}
