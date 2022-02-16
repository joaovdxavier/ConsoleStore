package main.java.menu;

import main.java.datamanagment.DataStoreManager;
import main.java.enums.MenuNames;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import main.java.inpututils.InputUtil;
import main.java.dataobjects.Product;
import java.io.IOException;
import java.util.ArrayList;

public class StoreMenu implements MenuItem {
    private static /*@ spec_public non_null @*/ String productShortInfo = "Product id: %s; Product name: %s";

    @Override
    /*@ also
    @ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public /*@ pure @*/ void displayMenu() throws NotLoggedInException, NonExistentProductId, IOException {
        ArrayList<Product> products = DataStoreManager.getInstance().getProducts();

        System.out.println("Product list.");
        for (Product product : products) {
            System.out.println(String.format(productShortInfo, product.getId(), product.getName()));
        }

        System.out.println("Type id of product to watch and add it to basket.\nType 0 to return to Main menu.\n");

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
    /*@ also
    @ assignable \nothing;
    @*/
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.STORE;
    }
}
