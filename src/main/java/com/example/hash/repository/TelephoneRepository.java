package com.example.hash.repository;

import com.example.hash.model.tele.Telephone;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TelephoneRepository extends CrudRepository<Telephone, Long> {
    @Modifying
    @Transactional
    @Query(value="LOAD DATA INFILE :filename INTO TABLE telephone fields TERMINATED BY '\t' " +
            "LINES TERMINATED BY '\n' ;", nativeQuery = true)
    void bulkLoadData(String filename);

    @Modifying
    @Transactional
    @Query(value="CREATE UNIQUE INDEX hash_unique ON telephone (hash) using HASH;", nativeQuery = true)
    void addIndexToHash();

    Optional<Telephone> findFirstByHash(String hash);
}