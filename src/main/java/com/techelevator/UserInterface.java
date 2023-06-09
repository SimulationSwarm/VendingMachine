package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
                String[] inventory = vendingMachine.displayInventory();
                for (String displayProduct : inventory) {
                    System.out.println(displayProduct);
                }


            } else if (menuSelection.equals("2")) {
                purchaseMenu();

            } else if (menuSelection.equals("3")) {
                System.out.println("Goodbye!");
                System.exit(1);
            } else if (menuSelection.equals("4")) {
              vendingMachine.createSalesReport();
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
            if (!purchaseMenuSelection.equals("1") && !purchaseMenuSelection.equals("2") && !purchaseMenuSelection.equals("3")) {
                System.out.println("Invalid input, try again.");
            }
            if (purchaseMenuSelection.equals("1")) {
                feedMoney();
            } else if (purchaseMenuSelection.equals("2")) {
                purchaseProductMenu();
            }
        }
        System.out.println(vendingMachine.giveBackChange());
        vendingMachine.setCurrentMoney(BigDecimal.valueOf(0.0));
    }

    public void feedMoney() throws FileNotFoundException {
        String feedMoneyMenuSelection = "";
        while (!feedMoneyMenuSelection.equals("3")) {
            System.out.println("(1) Add Money");
            System.out.println("(2) Current Balance");
            System.out.println("(3) Return to Previous Menu");
            feedMoneyMenuSelection = userInput.nextLine();
            if (!feedMoneyMenuSelection.equals("1") && !feedMoneyMenuSelection.equals("2") && !feedMoneyMenuSelection.equals("3")) {
                System.out.println("Invalid input, try again.");
            }
            if (feedMoneyMenuSelection.equals("1")) {
                System.out.println("How much would you like to add?");
                try {
                    BigDecimal moneyToAdd = BigDecimal.valueOf(Long.parseLong(userInput.nextLine()));

                    if (moneyToAdd.intValue() <= 0) {
                        System.out.println("Please enter a reasonable number :)");
                    } else {
                        vendingMachine.addMoney(moneyToAdd);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a reasonable number :)");
                }
            } else if (feedMoneyMenuSelection.equals("2")) {
                System.out.println("Your current balance is " + vendingMachine.getCurrentMoney() + ".");
            }
        }
    }

    public void purchaseProductMenu() throws FileNotFoundException {
        String[] inventory = vendingMachine.displayInventory();
        for (String displayProduct : inventory) {
            System.out.println(displayProduct);
        }
        System.out.println("Select product slot:");
        String itemPurchased = userInput.nextLine();
        if (!vendingMachine.inventory.getInventory().keySet().contains(itemPurchased)) {
            System.out.println("Invalid entry, try again");
            return;
        }
        if (!vendingMachine.inventory.checkInventory(itemPurchased)) {
            System.out.println("Selected item is out of stock.");
            return;
        }
        if (!vendingMachine.checkMoney(itemPurchased)) {
            System.out.println("Insufficient funds.");
            return;
        }
        vendingMachine.makePurchase(itemPurchased);
        System.out.println(
            vendingMachine.inventory.getInventory().get(itemPurchased).getName() +
            " " + vendingMachine.inventory.getInventory().get(itemPurchased).getCost() +
            " Current Balance: " +vendingMachine.getCurrentMoney());

        System.out.println((vendingMachine.inventory.getInventory().get(itemPurchased).getMessage()));
    }
}