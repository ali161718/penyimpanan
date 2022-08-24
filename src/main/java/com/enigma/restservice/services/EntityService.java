package com.enigma.restservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface EntityService<E, K> {

    public E save(E entity);

    public E removeById(K id);

    public E findById(K id);

    public Page<E> findAll(E entity, int page, int size, Sort.Direction direction);

}
