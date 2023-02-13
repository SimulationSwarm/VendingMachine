package com.techelevator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
	public static void main(String[] args) throws FileNotFoundException {
		FileParser fileParser = new FileParser("vendingmachine.csv");
		Inventory inventory = new Inventory(fileParser.buildVendingMachineInventory());
		VendingMachine vendingMachine = new VendingMachine(inventory, inventory.buildProductList());
		UserInterface ui = new UserInterface(vendingMachine);
		ui.run();
	}
}