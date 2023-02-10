package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.*;

public class VendingMachine {
    Inventory inventory;
    UserInterface userInterface;
    private double currentMoney = 0.0;

    public VendingMachine(Inventory inventory) {
        this.inventory = inventory;
    }



    public void addMoney(int moneyToAdd) {
        currentMoney += moneyToAdd;
        fileDepositToLog(moneyToAdd);
    }

    public void setCurrentMoney(Double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public void makePurchase(String itemToPurchase) {
        //todo (utilize inventory method)
        currentMoney -= inventory.getInventory().get(itemToPurchase).getCost();
        inventory.getInventory().get(itemToPurchase).reduceQuantity();
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
        System.out.println("Your change is: " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, "
        + pennies + " pennies.");
        fileDispenseChangeToLog();
    }
        //TODO (maybe a Logger class?)
    public void filePurchaseToLog(Product product) {
        Date date = new Date();
        File log = new File("Log.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(log, true))) {
            pw.println(String.valueOf(date) + " " + date.getTime() + " " + product.getName() + " " + product.getSlot()
                    + " " + "$" + product.getCost() + " " + "$" + currentMoney);
        } catch (IOException e) {
            System.out.println("Nope!");
        }
    }

    public void fileDepositToLog(int amountToDeposit) {
        Date date = new Date();
        File log = new File("Log.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(log, true))) {
        pw.println(String.valueOf(date) + " " + date.getTime() + " " + "FEED MONEY: " + "$" + amountToDeposit
                + ".00 " + "$" + currentMoney);
        } catch (IOException e) {
            System.out.println("Nope!");
        }
    }

    public void fileDispenseChangeToLog() {
        Date date = new Date();
        File log = new File("Log.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(log, true))) {
            pw.println(String.valueOf(date) + " " + date.getTime() + " GIVE CHANGE: " + "$" + currentMoney + " $0.00");

        } catch (IOException e) {
            System.out.println("Nope!");
        }
    }
}