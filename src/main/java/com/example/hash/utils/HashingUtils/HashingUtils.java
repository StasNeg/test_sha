package com.example.hash.utils.HashingUtils;

import com.example.hash.model.hash.HashType;
import com.example.hash.service.HashTypeService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class HashingUtils {

    private HashTypeService service;

    private HashType hashType;
    @Autowired
    public HashingUtils(HashTypeService service) {
        this.service = service;
    }


    public String getHashString(String originalString) {
        if(hashType == null)
            hashType = service.findById(1);
        String salt = hashType.getSalt();
        switch (hashType.getHashType()) {
            case "1":
                return getHash1(originalString + salt);
            case "2":
                return getHash2(originalString + salt);
            case "3":
                return getHash3(originalString + salt);
            default:
                throw new RuntimeException("Wrong hashType");
        }
    }

    private String getHash2(String originalString) {
        return Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
    }

    private String getHash3(String originalString) {
        return Hashing.sha384()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
    }

    private String getHash1(String originalString) {
        return Hashing.sha1()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
    }
}
