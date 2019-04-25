package com.example.hash.model.hash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "hash_type")
public class HashType {

    @Id
    private Integer id;


    @Column(name = "hash_type", nullable = false)
    @NotBlank
    private String hashType;

    @Column(name = "salt", nullable = false)
    @NotBlank
    private String salt;

    public HashType() {
    }

    public HashType(@NotBlank String hashType, @NotBlank String salt) {
        this.hashType = hashType;
        this.salt = salt;
    }

    public HashType(Integer id, @NotBlank String hashType, @NotBlank String salt) {
        this.id = id;
        this.hashType = hashType;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHashType() {
        return hashType;
    }

    public void setHashType(String hashType) {
        this.hashType = hashType;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
