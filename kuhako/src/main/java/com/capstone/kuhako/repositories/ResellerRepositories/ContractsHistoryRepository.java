package com.capstone.kuhako.repositories.ResellerRepositories;

import com.capstone.kuhako.models.ResellerModule.ContractsHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractsHistoryRepository extends CrudRepository<ContractsHistory,Object> {

}
