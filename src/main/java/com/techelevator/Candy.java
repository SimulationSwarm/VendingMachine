package com.techelevator;

public class Candy extends Product {
    private String message = "Munch Munch, Yum!";

    public Candy(String name, double cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }



}
