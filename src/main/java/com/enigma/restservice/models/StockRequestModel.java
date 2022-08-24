package com.enigma.restservice.models;

public class StockRequestModel {

    private Integer itemId;
    private Integer quantity;
    private Integer unitId;

    public StockRequestModel(Integer itemId, Integer quantity, Integer unitId) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitId = unitId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        return "StockRequestModel{" +
                "itemId=" + itemId +
                ", quantity=" + quantity +
                ", unitId=" + unitId +
                '}';
    }
}
