package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import dataobjects.Product;

import java.io.IOException;
import java.util.ArrayList;

public class StoreMenu implements MenuItem {
    @Override
    public void displayMenu() throws NotLoggedInException, NonExistentProductId, IOException {
        ArrayList<Product> products = DataStoreManager.getInstance().getProducts();

        System.out.println("Product list.");
        for (Product product : products) {
            System.out.print("Product id: " + product.getId());
            System.out.println(". Product name: " + product.getName());
        }

        System.out.println("Type id of product to watch and add it to basket.\n" +
                "Type 0 to return to Main menu.\n");

        int variant = InputUtil.getIntFromConsole();

        if (variant < 0 || variant > (products.size())) {
            throw new NonExistentProductId();
        } else {
            if (variant != 0 ) {
                ProductMenu.displayMenuWithProductId(variant);
            }
        }
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.STORE;
    }
}
