package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Product {
    private String message = "Chew Chew, Yum!";


    public Gum (String name, BigDecimal cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }



}
