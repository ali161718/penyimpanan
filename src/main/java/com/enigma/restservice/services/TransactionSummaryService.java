package com.enigma.restservice.services;

import com.enigma.restservice.models.TransactionSummary;
import java.time.Month;
import java.time.Year;
import java.util.List;

public interface TransactionSummaryService {
    public List<TransactionSummary> transactionSummaries(Year year, Month month, Integer date);
}
