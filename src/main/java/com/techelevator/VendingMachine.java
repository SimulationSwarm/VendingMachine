package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    Inventory inventory;
    UserInterface userInterface;
    private double currentMoney = 0.0;

    public VendingMachine(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addMoney(int moneyToAdd) {
        if (moneyToAdd > 0) {
            currentMoney += moneyToAdd;
            fileDepositToLog(moneyToAdd);
        }
    }

    public void setCurrentMoney(Double currentMoney) {
        if(currentMoney > 0) {
            this.currentMoney = currentMoney;
        }
    }

    public void makePurchase(String itemToPurchase) {
        //todo (utilize inventory method)
        currentMoney -= inventory.getInventory().get(itemToPurchase).getCost();
        inventory.getInventory().get(itemToPurchase).reduceQuantity();
        for (Product product : inventory.getProductsInInventory()) {
            if (product.getSlot().equals(itemToPurchase)) {
                product.reduceQuantity();
            }
        }

        //TODO (no souts outside of UI)
        System.out.println(inventory.getInventory().get(itemToPurchase).getName() + " " +
                inventory.getInventory().get(itemToPurchase).getCost() + " Current Balance: " +
                getCurrentMoney());
        //TODO (no souts outside of UI)
        System.out.println((inventory.getInventory().get(itemToPurchase).getMessage()));
        filePurchaseToLog(inventory.getInventory().get(itemToPurchase));
    }


    public boolean checkMoney(String itemToPurchase) {
          return (inventory.getInventory().get(itemToPurchase).getCost() <= currentMoney);
    }

    public Double getCurrentMoney() {
        return currentMoney;
    }

    public void giveBackChange() {
        int change = (int)(Math.ceil(currentMoney * 100));
        int quarters = change / 25;
        change = change % 25;
        int dimes = change / 10;
        change = change % 10;
        int nickels = change / 5;
        change = change % 5;
        int pennies = change;
        //TODO (no souts outside of UI)
        System.out.println("Your change is: " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and "
        + pennies + " pennies.");
        fileDispenseChangeToLog();
    }
        //TODO (maybe a Logger class?)
    public void filePurchaseToLog(Product product) {
        File log = new File("Log.txt");
        String dateTimeForOutput = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"));

        try (PrintWriter pw = new PrintWriter(new FileWriter(log, true))) {
            pw.println(dateTimeForOutput + " " + product.getName() + " " + product.getSlot()
                    + " " + "$" + product.getCost() + " " + "$" + currentMoney);
        } catch (IOException e) {
            System.out.println("Nope!");
        }
    }

    public void fileDepositToLog(int amountToDeposit) {
        File log = new File("Log.txt");
        String dateTimeForOutput = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"));

        try (PrintWriter pw = new PrintWriter(new FileWriter(log, true))) {
        pw.println(dateTimeForOutput + " " + "FEED MONEY: " + "$" + amountToDeposit
                + ".00 " + "$" + currentMoney);
        } catch (IOException e) {
            System.out.println("Nope!");
        }
    }

    public void fileDispenseChangeToLog() {
        File log = new File("Log.txt");
        String dateTimeForOutput = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a"));
        try (PrintWriter pw = new PrintWriter(new FileWriter(log, true))) {
            pw.println(dateTimeForOutput + " GIVE CHANGE: " + "$" + currentMoney + " $0.00");

        } catch (IOException e) {
            System.out.println("Nope!");
        }
    }
}