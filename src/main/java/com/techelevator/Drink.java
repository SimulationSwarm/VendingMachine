package com.techelevator;

public class Drink extends Product {

    private String message = "Glug glug, Yum!";

    public Drink(String name, double cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }


}
