package com.techelevator;

import java.sql.Array;
import java.util.*;

public class VendingMachine {
    Inventory inventory;
    UserInterface userInterface;
    private Double currentMoney = 0.0;

    public VendingMachine(Inventory inventory) {
        this.inventory = inventory;
    }



    public void addMoney(int moneyToAdd) {
        currentMoney += moneyToAdd;
    }

    public void makePurchase(String itemToPurchase) {
        currentMoney -= inventory.getInventory().get(itemToPurchase).getCost();
        inventory.getInventory().get(itemToPurchase).reduceQuantity();
        System.out.println((inventory.getInventory().get(itemToPurchase).getMessage()));
    }


    public boolean checkMoney(String itemToPurchase) {
          return (inventory.getInventory().get(itemToPurchase).getCost() <= currentMoney);
    }

    public Double getCurrentMoney() {
        return currentMoney;
    }
}