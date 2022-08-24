package com.enigma.restservice.repositories;

import com.enigma.restservice.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
