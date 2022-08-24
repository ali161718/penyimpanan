package com.enigma.restservice.services.impl;

import com.enigma.restservice.entities.Transaction;
import com.enigma.restservice.exeptions.EntityNotFoundException;
import com.enigma.restservice.models.TransactionSummary;
import com.enigma.restservice.repositories.TransactionRepository;
import com.enigma.restservice.services.EntityService;
import com.enigma.restservice.services.TransactionSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class TransactionServiceImpl implements EntityService<Transaction, Integer>, TransactionSummaryService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction entity) {
        return transactionRepository.save(entity);
    }

    @Override
    public Transaction removeById(Integer id) {
        Transaction entity = findById(id);
        transactionRepository.delete(entity);

        return entity;
    }

    @Override
    public Transaction findById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Transaction> findAll(Transaction entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.ASC.equals(direction) ? Sort.by(direction, "id").ascending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return transactionRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }


    @Override
    public List<TransactionSummary> transactionSummaries(Year year, Month month, Integer date) {
        LocalDate dateFrom = LocalDate.of(year.getValue(), 1, 1);
        LocalDate dateTo = LocalDate.of(year.getValue(), 12, 1);

        if (month != null) {
            dateFrom = dateFrom.withMonth(month.getValue());
            dateTo = dateTo.withMonth(month.getValue());
        }

        if (date != null) {
            dateFrom = dateFrom.withDayOfMonth(1);
            dateTo = dateTo.withDayOfMonth(date);
        } else {
            dateFrom = dateFrom.withDayOfMonth(1);
            dateTo = dateTo.with(TemporalAdjusters.lastDayOfMonth());
        }
        List<TransactionSummary> enities = transactionRepository.transactionSummaries(dateFrom, dateTo);

        return enities;

    }
}
