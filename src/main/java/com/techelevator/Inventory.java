package com.techelevator;

import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {
    private Map<String, Product> inventory;
    public Inventory(Map<String, Product> inventory) {
        this.inventory = inventory;
    }

    //TODO (make a method to manipulate inventory)

    public boolean checkInventory(String itemToPurchase) {
        return inventory.get(itemToPurchase).getQuantity() > 0;
    }

    public List<Product> buildProductList() throws FileNotFoundException {
        List<Product> productArrayList = new ArrayList<>();
        productArrayList.addAll(inventory.values());
        Collections.sort(productArrayList, new Sortbyslot());
        return productArrayList;
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }


}
