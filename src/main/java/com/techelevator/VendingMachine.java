package com.techelevator;

import java.math.BigDecimal;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    Inventory inventory;
    private List<Product> productList;
    private BigDecimal currentMoney = new BigDecimal("00.00");
    private BigDecimal totalSales = new BigDecimal("00.00");

    public VendingMachine(Inventory inventory, List<Product> productList) {
        this.inventory = inventory;
        this.productList = productList;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public boolean checkMoney(String itemToPurchase) {
        int compareTo = currentMoney.compareTo(inventory.getInventory().get(itemToPurchase).getCost());
        if (compareTo == 1 || compareTo == 0) {
            return true;
        }
        return false;
    }

    public void addMoney(BigDecimal moneyToAdd) {
            currentMoney = currentMoney.add(moneyToAdd);
            fileDepositToLog(moneyToAdd);
    }

    public void setCurrentMoney(BigDecimal currentMoney) {
            this.currentMoney = currentMoney;
    }

    public void makePurchase(String itemToPurchase) {
        //todo (utilize inventory method)
        totalSales  = totalSales.add(inventory.getInventory().get(itemToPurchase).getCost());

        currentMoney =  currentMoney.subtract(inventory.getInventory().get(itemToPurchase).getCost());
        inventory.getInventory().get(itemToPurchase).reduceQuantity();
        filePurchaseToLog(inventory.getInventory().get(itemToPurchase));
    }

    public int[] makeChange() {
        int[] changeArray = new int[4];
        BigDecimal temp = new BigDecimal("100.00");
        int change = (currentMoney.multiply(temp)).intValue();
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

    public String[] displayInventory() {
        String[] inventory = new String[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getQuantity() > 0) {
               inventory[i] = productList.get(i).getSlot() + ") " + productList.get(i).getName() + " " +
                       productList.get(i).getCost();
            } else {
                inventory[i] = productList.get(i).getSlot() + ") " + productList.get(i).getName() + " " +
                        productList.get(i).getCost() + " SOLD OUT";
            }
        }
        return inventory;
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

    public void fileDepositToLog(BigDecimal amountToDeposit) {
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
            for (Product product : productList) {
                pw.println(product.getName() + "|" + Math.abs(product.getQuantity() - 5));
            }
            pw.println();
            pw.println("**Total Sales** " + totalSales);
        } catch (IOException e) {
            System.out.println("Failed to create file, try again.");
        }
    }
}