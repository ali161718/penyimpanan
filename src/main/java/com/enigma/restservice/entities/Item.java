package com.enigma.restservice.entities;

import javax.persistence.*;

@Table(name = "item")
@Entity
public class Item extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
