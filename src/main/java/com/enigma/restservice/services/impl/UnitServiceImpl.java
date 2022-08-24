package com.enigma.restservice.services.impl;

import com.enigma.restservice.entities.Unit;
import com.enigma.restservice.exeptions.EntityNotFoundException;
import com.enigma.restservice.repositories.UnitRepository;
import com.enigma.restservice.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements EntityService<Unit, Integer> {

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public Unit save(Unit entity) {
        return unitRepository.save(entity);
    }

    @Override
    public Unit removeById(Integer id) {
        Unit entity = findById(id);
        unitRepository.delete(entity);

        return entity;
    }

    @Override
    public Unit findById(Integer id) {
        return unitRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Unit> findAll(Unit entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.ASC.equals(direction) ? Sort.by(direction, "id").ascending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return unitRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }
}