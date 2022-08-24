package com.enigma.restservice.repositories;

import com.enigma.restservice.models.TransactionSummary;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepositoryCustom {

    public List<TransactionSummary> transactionSummaries(LocalDate dateFrom, LocalDate dateTo);
}
