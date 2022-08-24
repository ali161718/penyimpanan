package com.enigma.restservice.models;

import com.enigma.restservice.validation.annotations.MinLength;

import javax.validation.constraints.NotBlank;

public class UnitModel {

    private Integer id;

    @MinLength(1)
    @NotBlank(message = "{name.notblank}")
    private String name;

    private String description;

    public UnitModel(Integer id, @NotBlank(message = "{name.notblank}") String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UnitModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UnitModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
