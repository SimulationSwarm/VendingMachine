package com.techelevator;

public abstract class Product {
    private String name;
    private Double cost;
    private String slot;
    private int quantity = 5;

    public Product(String name, double cost, String slot) {
        this.name = name;
        this.cost = cost;
        this.slot = slot;
    }

    public void reduceQuantity() {
        quantity -= 1;
    }

    public abstract String getMessage();

    public String getSlot() {
        return slot;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
