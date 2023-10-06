package com.capstone.kuhako.repositories.CollectorModuleRepository;

import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.CollectorModules.CollectPayments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectPaymentRepository extends CrudRepository<CollectPayments,Object> {
    List<CollectPayments> findByContracts(Contracts contracts);
}
