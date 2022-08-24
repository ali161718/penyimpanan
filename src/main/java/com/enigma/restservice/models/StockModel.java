package com.enigma.restservice.models;

public class StockModel{

    private Integer id;
    private ItemModel item;
    private Integer quantity;
    private UnitModel unit;

    public StockModel(Integer id, ItemModel item, Integer quantity, UnitModel unit) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
    }

    public StockModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UnitModel getUnit() {
        return unit;
    }

    public void setUnit(UnitModel unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                ", unit=" + unit +
                '}';
    }
}
