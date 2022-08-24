package com.enigma.restservice.services.impl;

import com.enigma.restservice.entities.Stock;
import com.enigma.restservice.exeptions.EntityNotFoundException;
import com.enigma.restservice.models.StockSummary;
import com.enigma.restservice.repositories.StockRepository;
import com.enigma.restservice.services.EntityService;
import com.enigma.restservice.services.StockSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements EntityService<Stock, Integer>, StockSummaryService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock save(Stock entity) {
        return stockRepository.save(entity);
    }

    @Override
    public Stock removeById(Integer id) {
        Stock entity = findById(id);
        stockRepository.delete(entity);

        return entity;
    }

    @Override
    public Stock findById(Integer id) {
        return stockRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Stock> findAll(Stock entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.ASC.equals(direction) ? Sort.by(direction, "id").ascending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return stockRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }

    @Override
    public List<StockSummary> stockSummaries() {
        return stockRepository.stockSummaries();
    }
}
