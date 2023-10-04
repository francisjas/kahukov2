package com.capstone.kuhako.repositories;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.Collector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectorRepository extends CrudRepository<Collector,Object> {
    Collector findByUsername(String username);

    @Query("SELECT DISTINCT c " +
            "FROM Collector c " +
            "JOIN FETCH c.contracts co " +
            "WHERE c.reseller.reseller_id = :resellerId")
    List<Collector> findCollectorsWithContractsByResellerId(@Param("resellerId") Long resellerId);

}
