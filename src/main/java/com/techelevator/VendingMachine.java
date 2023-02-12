package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Array;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    Inventory inventory;
    private List<Product> productList;
    private double currentMoney = 0.0;
    private double totalSales = 0.00;




    public VendingMachine(Inventory inventory, List<Product> productList) {
        this.inventory = inventory;
        this.productList = productList;
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
        totalSales += inventory.getInventory().get(itemToPurchase).getCost();
        currentMoney -= inventory.getInventory().get(itemToPurchase).getCost();
        inventory.getInventory().get(itemToPurchase).reduceQuantity();
        filePurchaseToLog(inventory.getInventory().get(itemToPurchase));
    }


    public boolean checkMoney(String itemToPurchase) {
          return (inventory.getInventory().get(itemToPurchase).getCost() <= currentMoney);
    }

    public void displayInventory() {
        //TODO (no souts outside of UI(?))
        for (Product product : productList) {
            if (product.getQuantity() > 0) {
                System.out.println(product.getSlot() + ") " + product.getName() + " " + product.getCost());
            } else {
                System.out.println(product.getSlot() + ") " + product.getName() + " " + product.getCost() + " SOLD OUT");
            }
        }
    }

    public Double getCurrentMoney() {
        return currentMoney;
    }

    public int[] makeChange() {
        int[] changeArray = new int[4];

        int change = (int)(Math.ceil(currentMoney * 100));
        int quarters = change / 25;
        change = change % 25;
        int dimes = change / 10;
        change = change % 10;
        int nickels = change / 5;
        change = change % 5;
        int pennies = change;

        changeArray[0] = quarters;
        changeArray[1] = dimes;
        changeArray[2] = nickels;
        changeArray[3] = pennies;
        return changeArray;
    }

    public String giveBackChange() {
        int[] changeArray = makeChange();
        String pennies = "pennies";
        String nickels = "nickels";
        String dimes = "dimes";
        String quarters = "quarters";

        if(changeArray[0] == 1) {
            quarters = "quarter";
        }
        if(changeArray[1] == 1) {
            dimes = "dime";
        }
        if(changeArray[2] == 1) {
            nickels = "nickel";
        }
        if(changeArray[3] == 1) {
            pennies = "penny";
        }

        String giveBackChange = "Your change is: " + changeArray[0] + " " + quarters + ", " + changeArray[1] + " " +
                dimes + ", " + changeArray[2] + " " + nickels + ", and " + changeArray[3] + " " + pennies + ".";
        fileDispenseChangeToLog();

        return giveBackChange;
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

    public void createSalesReport() {
        String salesReportFile = "Sales-Report-" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy-h-mm-ssa"));
        File salesReport = new File(salesReportFile + ".txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(salesReport, true))) {
            pw.println("Total Sales: " + totalSales);
        } catch (IOException e) {
            System.out.println("Failed to create file, try again.");

        }
    }
}