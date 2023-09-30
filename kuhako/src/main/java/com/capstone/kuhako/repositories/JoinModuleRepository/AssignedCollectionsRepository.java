package com.capstone.kuhako.repositories.JoinModuleRepository;

import com.capstone.kuhako.models.JoinModule.AssignedCollections;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedCollectionsRepository extends CrudRepository<AssignedCollections,Object> {
}
