package com.capstone.kuhako.services;

import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.Reseller;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResellerService {
    // Create Client
    void createReseller(Reseller reseller);
    // Get Client
    Iterable<Reseller> getUsername();
    // Delete Client
    List<Collector> getCollectorIdByReseller(Long resellerId );
    ResponseEntity deleteReseller(Long id);
    // Update a Client
    ResponseEntity updateReseller(Long id, Reseller reseller);

    ResponseEntity assignCollectorToClient(Long resellerId, Long collectorId, Long clientId);
}
