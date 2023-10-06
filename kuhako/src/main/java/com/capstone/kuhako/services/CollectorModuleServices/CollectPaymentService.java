package com.capstone.kuhako.services.CollectorModuleServices;

import com.capstone.kuhako.models.CollectorModules.CollectPayments;
import org.springframework.http.ResponseEntity;

public interface CollectPaymentService {
    void createCollectPayment(Long collectorId, CollectPayments collectPayments);

    Iterable<CollectPayments> getCollectPayment();

    Iterable<CollectPayments>getCollectPaymentByResellerId(Long collectorId);

    ResponseEntity deleteCollectPayment(Long collectorId, Long id);

    ResponseEntity updateCollectPayment(Long collectorId, Long id, CollectPayments collectPayments);
}
