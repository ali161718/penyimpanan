package com.enigma.restservice.models;

import com.enigma.restservice.entities.TypeTransaction;

public class TransactionSummary {

    private Long id;
    private TypeTransaction typeTransaction;
    private Long amount;

    public TransactionSummary(Long id, TypeTransaction typeTransaction, Long amount) {
        this.id = id;
        this.typeTransaction = typeTransaction;
        this.amount = amount;
    }

    public Long getTotalTransactions() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionSummary{" +
                "id=" + id +
                ", typeTransaction=" + typeTransaction +
                ", amount=" + amount +
                '}';
    }
}
