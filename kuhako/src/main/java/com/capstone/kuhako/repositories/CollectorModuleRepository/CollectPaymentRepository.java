package com.capstone.kuhako.repositories.CollectorModuleRepository;

import com.capstone.kuhako.models.CollectorModules.FollowUp;
import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.CollectorModules.CollectPayments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectPaymentRepository extends CrudRepository<CollectPayments,Object> {
    List<CollectPayments> findByContracts(Contracts contracts);

    @Query("SELECT cp FROM CollectPayments cp WHERE cp.collector.collector_id = :collectorId")
    List<CollectPayments> findCollectPaymentsByCollectorId(@Param("collectorId") Long collectorId);


}
