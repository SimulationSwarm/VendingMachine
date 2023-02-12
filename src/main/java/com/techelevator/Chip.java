package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Product {

    private String message = "Crunch Crunch, Yum!";

    public Chip(String name, BigDecimal cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
