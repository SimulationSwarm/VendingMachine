package com.techelevator;

public class Chip extends Product {

    private String message = "Crunch Crunch, Yum!";

    public Chip(String name, double cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
