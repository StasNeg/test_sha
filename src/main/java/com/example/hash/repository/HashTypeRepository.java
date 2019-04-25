package com.example.hash.repository;

import com.example.hash.model.hash.HashType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTypeRepository extends CrudRepository<HashType, Integer> {
    List<HashType> findAll();
}
