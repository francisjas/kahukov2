package com.capstone.kuhako.repositories.JoinModuleRepository;

// Import statements
import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.JoinModule.Contracts;
import com.capstone.kuhako.models.JoinModule.ContractsClient;
import com.capstone.kuhako.models.JoinModule.ContractsCollector;
import com.capstone.kuhako.models.JoinModule.ContractsReseller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractsRepository extends CrudRepository<Contracts,Object> {

    // Method to find contracts by client and payment status
    List<Contracts> findByClientAndPaymentStatus(Client client, boolean status);

    // Query to find contracts by reseller id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.JoinModule.ContractsReseller(cl.username, co.itemName, co.itemPrice, co.paymentType) FROM Contracts co JOIN co.client cl WHERE co.reseller.reseller_id = :resellerId AND co.paymentStatus = true")
    Iterable<ContractsReseller> findContractsByResellerId(@Param("resellerId") Long resellerId);

    // Query to find contracts by collector id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.JoinModule.ContractsCollector(cl.username, co.debtRemaining) FROM Contracts co JOIN co.client cl WHERE co.collector.collector_id = :collectorId AND co.paymentStatus = true")
    Iterable<ContractsCollector> findContractsByCollectorId(@Param("collectorId") Long collectorId);

    // Query to find contract history by collector id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.JoinModule.ContractsCollector(cl.username, co.debtRemaining) FROM Contracts co JOIN co.client cl WHERE co.collector.collector_id = :collectorId AND co.paymentStatus = false")
    Iterable<ContractsCollector> findContractsHistoryByCollectorId(@Param("collectorId") Long collectorId);

    // Query to find contracts by client id and payment status
    @Query("SELECT NEW com.capstone.kuhako.models.JoinModule.ContractsClient(co.itemName, co.itemPrice) FROM Contracts co WHERE co.client.client_id = :clientId AND co.paymentStatus = true")
    Iterable<ContractsClient> findContractsByClientId(@Param("clientId") Long collectorId);
}

