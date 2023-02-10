package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UserInterface {
    VendingMachine vendingMachine;

    public UserInterface(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    Scanner userInput = new Scanner(System.in);


    public void run() throws FileNotFoundException {
        mainMenu();
    }

    public void mainMenu() throws FileNotFoundException {
        String menuSelection = "";
        while (!menuSelection.equals("3")) {
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            menuSelection = userInput.nextLine();
            if (menuSelection.equals("1")) {
                vendingMachine.inventory.displayInventory();
            } else if (menuSelection.equals("2")) {
                purchaseMenu();

            } else if (menuSelection.equals("3")) {
                System.out.println("Goodbye!");
                System.exit(1);
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
    }

    public void purchaseMenu() throws FileNotFoundException {
        String purchaseMenuSelection = "";
        while (!purchaseMenuSelection.equals("3")) {
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            purchaseMenuSelection = userInput.nextLine();
            if (purchaseMenuSelection.equals("1")) {
                feedMoney();
                } else if (purchaseMenuSelection.equals("2")) {
                purchaseProductMenu();
            }
        }
        vendingMachine.giveBackChange();
        vendingMachine.setCurrentMoney(0.0);
    }

    public void feedMoney() throws FileNotFoundException {
        String feedMoneyMenuSelection = "";
        while (!feedMoneyMenuSelection.equals("3")) {
            System.out.println("(1) Add Money");
            System.out.println("(2) Current Balance");
            System.out.println("(3) Return to Previous Menu");
            feedMoneyMenuSelection = userInput.nextLine();
            if (feedMoneyMenuSelection.equals("1")) {
                System.out.println("How much would you like to add?");
                int moneyToAdd = Integer.parseInt(userInput.nextLine());
                    if (moneyToAdd < 0) {
                        System.out.println("Invalid amount.");
                    } else {
                        vendingMachine.addMoney(moneyToAdd);
                    }
            } else if (feedMoneyMenuSelection.equals("2")) {
                System.out.println("Your current balance is " + vendingMachine.getCurrentMoney() + ".");
            }
        }
    }

    public void purchaseProductMenu() throws FileNotFoundException {
        vendingMachine.inventory.displayInventory();
        String itemPurchased = userInput.nextLine();
        if (!vendingMachine.inventory.checkInventory(itemPurchased)) {
            System.out.println("Selected item is out of stock.");

        }
        if (!vendingMachine.checkMoney(itemPurchased)) {
            System.out.println("Insufficient funds.");
        }
        vendingMachine.makePurchase(itemPurchased);
    }
}