package com.capstone.kuhako.repositories.ResellerRepositories;

// Import statements
import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.ClientModules.ContractsClient;
import com.capstone.kuhako.models.CollectorModules.ContractsCollector;
import com.capstone.kuhako.models.ResellerModule.ContractsReseller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractsRepository extends CrudRepository<Contracts,Object> {

    // Query to find contracts by reseller id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.ResellerModule.ContractsReseller(co.contracts_id, cl.username, co.itemName, co.itemPrice, co.paymentType) FROM Contracts co JOIN co.client cl WHERE co.reseller.reseller_id = :resellerId ")
    Iterable<ContractsReseller> findContractsByResellerId(@Param("resellerId") Long resellerId);

    // Query to find contracts by collector id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.CollectorModules.ContractsCollector(co.contracts_id,cl.username, co.debtRemaining) FROM Contracts co JOIN co.client cl WHERE co.collector.collector_id = :collectorId ")
    Iterable<ContractsCollector> findContractsByCollectorId(@Param("collectorId") Long collectorId);

    // Query to find contract history by collector id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.CollectorModules.ContractsCollector(co.contractsHistory_id, cl.username, co.itemPrice) FROM ContractsHistory co JOIN co.client cl WHERE co.collector.collector_id = :collectorId")
    Iterable<ContractsCollector> findContractsHistoryByCollectorId(@Param("collectorId") Long collectorId);

    // Query to find contracts by client id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.ClientModules.ContractsClient(co.contracts_id, co.itemName, co.itemPrice) FROM Contracts co WHERE co.client.client_id = :clientId")
    Iterable<ContractsClient> findContractsByClientId(@Param("clientId") Long collectorId);

    @Query("SELECT NEW com.capstone.kuhako.models.ClientModules.ContractsClient(co.contractsHistory_id, co.itemName, co.itemPrice) FROM ContractsHistory co WHERE co.client.client_id = :clientId")
    Iterable<ContractsClient> findContractsHistoryByClientId(@Param("clientId") Long collectorId);
}

