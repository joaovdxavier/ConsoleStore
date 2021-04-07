package menu;

import datamanagment.DataBasketManager;
import dataobjects.Product;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;

import java.io.IOException;

public class ProductAddingMenu implements MenuItem {
    private static Product productToAdd;

    @Override
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {
        System.out.println("How many of " +
                productToAdd.getName() +
                " do you wanna add to basket? (from 1 to 100000)");
        int count;
        count = InputUtil.getIntFromConsole();
       if (DataBasketManager.getInstance().addProductToBasket(productToAdd, count)) {
           System.out.println("Product was added successfully!");
       } else {
           System.out.println("Incorrect count of products. Product wasn't added to the basket.");
       }
    }

    public static void displayMenuWithProduct(Product productToAdd) throws NotLoggedInException, NonExistentProductId, IOException {
        ProductAddingMenu.productToAdd = productToAdd;
        MenuManager.getInstance().displaySelectedMenu(MenuNames.PRODUCT_ADDING);
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.PRODUCT_ADDING;
    }
}
