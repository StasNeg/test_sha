package com.example.hash.service;

import com.example.hash.model.tel.Telephone;
import com.example.hash.repository.TelephoneRepository;
import com.example.hash.utils.HashingUtils.HashingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Transactional(readOnly = true)
public class TelephoneService  {

    private TelephoneRepository telephoneRepository;
    private HashingUtils hashingUtils;

    @Autowired
    public TelephoneService(TelephoneRepository telephoneRepository, HashingUtils hashingUtils) {
        this.telephoneRepository = telephoneRepository;
        this.hashingUtils = hashingUtils;
    }

    public boolean saveAll(Iterable<Telephone> telefones){
        return Objects.nonNull(telephoneRepository.saveAll(telefones));
    }

    public Telephone getHashByPhoneNumber(String phoneNumber){
        return telephoneRepository.findFirstByHash(hashingUtils.getHashString(phoneNumber)).orElse(new Telephone());
    }

    public Telephone getTelephoneByHash(String hash){
        return telephoneRepository.findFirstByHash(hash).orElse(new Telephone());
    }

}