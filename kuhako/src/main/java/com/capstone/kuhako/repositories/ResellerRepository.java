package com.capstone.kuhako.repositories;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.Reseller;
import com.capstone.kuhako.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResellerRepository extends CrudRepository<Reseller,Object> {
}