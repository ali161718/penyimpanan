package com.enigma.restservice.entities;

import javax.persistence.*;

@Table(name = "transaction")
@Entity
public class Transaction extends AbstractEntity {

    private Integer amount;
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    public Transaction() {
    }

    public Transaction(Integer amount, String description, TypeTransaction typeTransaction) {
        this.amount = amount;
        this.description = description;
        this.typeTransaction = typeTransaction;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
}
