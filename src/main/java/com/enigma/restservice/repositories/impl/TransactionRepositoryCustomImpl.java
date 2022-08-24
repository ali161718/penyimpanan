package com.enigma.restservice.repositories.impl;

import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.models.TransactionSummary;
import com.enigma.restservice.repositories.TransactionRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TransactionSummary> transactionSummaries(LocalDate dateFrom, LocalDate dateTo) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TransactionSummary> query = builder.createQuery(TransactionSummary.class);
        Root<Transaction> root = query.from(Transaction.class);

        query
                .multiselect(
                        builder.count(root.get("id")),
                        root.get("typeTransaction"),
                        builder.sum(root.get("amount"))
                )
                .where(
                        builder.between(
                                builder.function(
                                        "DATE",
                                        Date.class,
                                        root.get("createdDate")
                                ),
                                Date.valueOf(dateFrom),
                                Date.valueOf(dateTo)
                        )
                )
                .groupBy(root.get("typeTransaction"));

        return entityManager.createQuery(query).getResultList();
    }
}