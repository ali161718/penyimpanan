package com.enigma.restservice.services.impl;

import com.enigma.restservice.entities.Item;
import com.enigma.restservice.exeptions.EntityNotFoundException;
import com.enigma.restservice.repositories.ItemRepository;
import com.enigma.restservice.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements EntityService<Item, Integer> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item save(Item entity) {
        return itemRepository.save(entity);
    }

    @Override
    public Item removeById(Integer id) {
        Item entity = findById(id);
        itemRepository.delete(entity);

        return entity;
    }

    @Override
    public Item findById(Integer id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Item> findAll(Item entity, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.Direction.DESC.equals(direction) ? Sort.by(direction, "id").descending() : Sort.by("id");
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return itemRepository.findAll(Example.of(entity, matcher), PageRequest.of(page, size, sort));
    }
}