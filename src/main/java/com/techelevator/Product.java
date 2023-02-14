package com.techelevator;

import java.math.BigDecimal;
import java.util.Comparator;

public abstract class Product {
    private String name;
    private BigDecimal cost;
    private String slot;
    private int quantity = 5;

    public Product(String name, BigDecimal cost, String slot) {
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

    public BigDecimal getCost() {
        return cost;
    }

}

 class SortBySlot implements Comparator<Product> {

     @Override
     public int compare(Product a, Product b) {
         return a.getSlot().compareTo(b.getSlot());
     }
 }
