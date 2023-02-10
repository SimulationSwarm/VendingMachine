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

        String menuSelection;
        do {
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
       } while (!menuSelection.equals("3"));

    }

    public void purchaseMenu() throws FileNotFoundException {
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        String menuSelection = userInput.nextLine();
        while (!menuSelection.equals("3")) {
            if (menuSelection.equals("1")) {
                feedMoney();
                } else if (menuSelection.equals("2")) {
                purchaseProductMenu();
            }
        }
        vendingMachine.giveBackChange();
        vendingMachine.setCurrentMoney(0.0);
        mainMenu();


    }

    public void feedMoney() throws FileNotFoundException {
        System.out.println("(1) Add Money");
        System.out.println("(2) Current Balance");
        System.out.println("(3) Return to Previous Menu");
        String menuSelection = userInput.nextLine();
        while (!menuSelection.equals("3")) {
            if (menuSelection.equals("1")) {
                System.out.println("How much would you like to add?");
                int moneyToAdd = Integer.parseInt(userInput.nextLine());
                while (moneyToAdd != 0) {
                    if (moneyToAdd < 0) {
                        System.out.println("Invalid amount.");
                        return;
                    } else {
                        vendingMachine.addMoney(moneyToAdd);
                        return;
                    }
                }
            } else if (menuSelection.equals("2")) {
                System.out.println("Your current balance is " + vendingMachine.getCurrentMoney() + ".");
                return;
            }
        }
        purchaseMenu();
    }

    public void purchaseProductMenu() throws FileNotFoundException {
        vendingMachine.inventory.displayInventory();
        String itemPurchased = userInput.nextLine();
        if (!vendingMachine.inventory.checkInventory(itemPurchased)) {
            System.out.println("Selected item is out of stock.");
            purchaseMenu();
        }
        if (!vendingMachine.checkMoney(itemPurchased)) {
            System.out.println("Insufficient funds.");
            purchaseMenu();
        }
        vendingMachine.makePurchase(itemPurchased);
        purchaseMenu();

    }

}