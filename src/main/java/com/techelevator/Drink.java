package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Product {

    private String message = "Glug glug, Yum!";

    public Drink(String name, BigDecimal cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }


}
