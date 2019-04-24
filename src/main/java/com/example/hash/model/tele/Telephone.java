package com.example.hash.model.tele;

import com.example.hash.model.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "telephone")
//, uniqueConstraints = {@UniqueConstraint(columnNames = "number", name = "telephone_unique_number_idx")})
public class Telephone extends AbstractBaseEntity {

    public Telephone() {
    }

    @Column(name = "number", nullable = false)
    @NotBlank
    private String number;

    @Column(name = "hash", nullable = false)
    @NotBlank
    private String hash;

    public Telephone(@NotBlank String number, @NotBlank String hash) {

        this.number = number;
        this.hash = hash;
    }

    public Telephone(Long id, @NotBlank String number, @NotBlank String hash) {
        super(id);
        this.number = number;
        this.hash = hash;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "number='" + number + '\'' +
                '}';
    }
}