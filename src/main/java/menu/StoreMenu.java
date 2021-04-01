package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import dataobjects.Product;

public class StoreMenu {
    public static void displayStoreMenu(MenuData menuData) throws NotLoggedInException, NonExistentProductId {
        System.out.println("Product list.");
        for (Product product : menuData.getProducts()) {
            System.out.print("Product id: " + product.getId());
            System.out.println(". Product name: " + product.getName());

        }

        System.out.println("Type id of product to watch and add it to basket.\n" +
                "Type 0 to return to Main menu.\n");

        int variant = InputUtil.getInt();

        if (variant < 0 && variant > (menuData.getProducts().size())) {
            throw new NonExistentProductId();
        } else {
            ProductMenu.displayProductMenu(menuData, variant);
        }
    }
}
