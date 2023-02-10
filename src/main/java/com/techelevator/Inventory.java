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

    public boolean checkInventory(String itemToPurchase) {
        return inventory.get(itemToPurchase).getQuantity() >= 0;
    }

    public void displayInventory() {
        for (Product product : productsInInventory) {
            System.out.println((product.getSlot() + ") " + product.getName() + " " + product.getCost()));
        }
//        List<String> keys = new ArrayList<>(inventory.keySet());
//        Collections.sort(keys);
//        List<Product> products = new ArrayList<>(inventory.values());

//        for (int i = 0; i < products.size(); i++) {
//            inventoryDisplay.add(keys.get(i) + ") " + products.get(i).getName() + " " + products.get(i).getCost());
//        }
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }

    public List<Product> getProductsInInventory() {
        return productsInInventory;
    }

}
