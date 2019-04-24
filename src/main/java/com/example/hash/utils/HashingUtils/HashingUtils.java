package com.example.hash.utils.HashingUtils;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class HashingUtils {

    @Value("${hash.type}")
    private String hashType;

    public String getHashString(String originalString, String salt) {
        switch (hashType) {
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
