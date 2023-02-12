package com.techelevator;

import java.io.*;
import java.util.*;

public class FileParser {
    private final String inventoryPath;

    public FileParser(String inventoryPath) {
        this.inventoryPath = inventoryPath;
    }


    public Map<String,Product> buildVendingMachineInventory() throws FileNotFoundException {
        Map<String, Product> vendingMachineInventory = new HashMap<>();
        File file = new File(inventoryPath);
        try (Scanner inventoryReader = new Scanner(file)) {
            while (inventoryReader.hasNextLine()) {
                String currentLine = inventoryReader.nextLine();
                vendingMachineInventory.put(getProductFromLine(currentLine).getSlot(),getProductFromLine(currentLine));
            }
        }   catch (FileNotFoundException  e) {
                    System.out.println("Invalid file path, try again.");
            }
//        for (int i = 0; i < productList.size(); i++) {
//            vendingMachineInventory.put(productList.get(i).getSlot(), productList.get(i));
//        }
        return vendingMachineInventory;
    }




    public Product getProductFromLine(String line) {
        Product newProduct;
        String[] lineParts = line.split("\\|");
        String productSlot = lineParts[0];
        String productName = lineParts[1];
        double productPrice = Double.parseDouble(lineParts[2]);
        String productType = lineParts[3];
        if(productType.equals("Gum")) {
            newProduct = new Gum(productName, productPrice, productSlot);
        } else if (productType.equals("Candy")) {
            newProduct = new Candy(productName, productPrice, productSlot);
        } else if (productType.equals("Drink")) {
            newProduct = new Drink(productName, productPrice, productSlot);
        } else {
            newProduct = new Chip(productName, productPrice, productSlot);
        }
        return newProduct;
    }





}
