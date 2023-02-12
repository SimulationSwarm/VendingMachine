package com.techelevator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        for (Product product : inventory.values()) {
            productArrayList.add(product);
        }
//        try (Scanner inventoryReader = new Scanner(file)) {
//            while (inventoryReader.hasNextLine()) {
//                String currentLine = inventoryReader.nextLine();
//                productArrayList.add(getProductFromLine(currentLine));
//            }
//
//        }   catch (FileNotFoundException  e) {
//            System.out.println("Invalid file path, try again.");
//        }
        return productArrayList;
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }


}
