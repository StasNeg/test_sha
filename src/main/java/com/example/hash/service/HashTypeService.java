package com.example.hash.service;

import com.example.hash.model.hash.HashType;
import com.example.hash.repository.HashTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HashTypeService {

    private HashTypeRepository repository;

    @Autowired
    public HashTypeService(HashTypeRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public HashType saveOrEdit(HashType current) {
        current.setId(1);
        current = repository.save(current);
        return current;
    }

    public HashType findById(Integer i) {
        return repository.findById(i).orElse(null);
    }
}
