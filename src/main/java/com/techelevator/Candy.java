package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Product {
    private String message = "Munch Munch, Yum!";

    public Candy(String name, BigDecimal cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }



}
