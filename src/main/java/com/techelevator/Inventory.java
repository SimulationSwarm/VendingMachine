package com.techelevator;

import java.util.List;
import java.util.Map;

public class Inventory {

    private Map<String, Product> inventory;
    private List<Product> productsInInventory;


    public Inventory(Map<String, Product> inventory, List<Product> productsInInventory) {
        this.inventory = inventory;
        this.productsInInventory = productsInInventory;
    }

    //TODO (make a method to manipulate inventory)

    public boolean checkInventory(String itemToPurchase) {
        return inventory.get(itemToPurchase).getQuantity() > 0;
    }

    public void displayInventory() {
        //TODO (no souts outside of UI(?))
        for (Product product : productsInInventory) {
            System.out.println((product.getSlot() + ") " + product.getName() + " " + product.getCost()));
        }
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }

    public List<Product> getProductsInInventory() {
        return productsInInventory;
    }

}
