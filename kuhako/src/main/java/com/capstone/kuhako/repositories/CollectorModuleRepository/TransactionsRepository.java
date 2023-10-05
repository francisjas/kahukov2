package com.capstone.kuhako.repositories.CollectorModuleRepository;

import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.ResellerModule.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends CrudRepository<Transactions,Object> {
    List<Transactions> findByContracts(Contracts contracts);
}
