package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Optional;

public class VendingMachineTest {

    @Test
    public void what_if_add_negative_money() throws FileNotFoundException {
        FileParser fileParser = new FileParser("vendingmachine.csv");
        Inventory inventory = new Inventory(fileParser.buildVendingMachineInventory(fileParser.buildProductList()), fileParser.buildProductList());
        VendingMachine vendingMachine = new VendingMachine(inventory);
        vendingMachine.addMoney(-5);
        Assert.assertEquals(0.0, vendingMachine.getCurrentMoney(), 0.01);
    }

    @Test
    public void what_if_setCurrentMoney_is_passed_negative_number() throws FileNotFoundException {
        FileParser fileParser = new FileParser("vendingmachine.csv");
        Inventory inventory = new Inventory(fileParser.buildVendingMachineInventory(fileParser.buildProductList()), fileParser.buildProductList());
        VendingMachine sut = new VendingMachine(inventory);
        sut.setCurrentMoney(-4.0);
        Assert.assertEquals(0, sut.getCurrentMoney(), 0.01);
    }
}
