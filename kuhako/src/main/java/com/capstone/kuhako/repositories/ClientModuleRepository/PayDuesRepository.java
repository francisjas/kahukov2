package com.capstone.kuhako.repositories.ClientModuleRepository;

import com.capstone.kuhako.models.ClientModules.PayDues;
import com.capstone.kuhako.models.ResellerModule.Contracts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayDuesRepository extends CrudRepository<PayDues,Object> {
    List<PayDues> findByContracts(Contracts contracts);
    @Query("SELECT pd FROM PayDues pd WHERE pd.contracts.client.client_id = :clientId")
    Iterable<PayDues> findPayDuesByClientId(@Param("clientId") Long clientId);
}
