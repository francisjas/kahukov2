package com.capstone.kuhako.services.JoinModuleServices;

import com.capstone.kuhako.models.JoinModule.*;
import org.springframework.http.ResponseEntity;

public interface ContractsService {


    void createContract(Long resellerId, Contracts contracts);

    Iterable<Contracts> getContracts();

    Iterable<ContractsReseller>getContractsByResellerId(Long resellerId);

    Iterable<ContractsCollector>getContractsByCollectorId(Long resellerId);

    Iterable<ContractsCollector>getContractsHistoryByCollectorId(Long resellerId);

    Iterable<ContractsClient>getContractsByClientId(Long resellerId);

    Iterable<ContractsClient>getContractsHistoriesByClientId(Long resellerId);

    ResponseEntity deleteContract(Long resellerId, Long id);

    ResponseEntity updateContract(Long collectorId, Transactions transactions);
}