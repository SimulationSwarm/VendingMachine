package com.techelevator;

public class Gum extends Product {
    private String message = "Chew Chew, Yum!";


    public Gum (String name, double cost, String slot) {
        super(name, cost, slot);
    }

    @Override
    public String getMessage() {
        return message;
    }



}
