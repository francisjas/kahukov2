package com.capstone.kuhako.services.ResellerServices;

import com.capstone.kuhako.models.ClientModules.ContractsClient;
import com.capstone.kuhako.models.CollectorModules.ContractsCollector;
import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.ResellerModule.ContractsReseller;
import com.capstone.kuhako.models.ResellerModule.Transactions;
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