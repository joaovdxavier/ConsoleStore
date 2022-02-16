package menu;

import datamanagment.DataBasketManager;
import dataobjects.Product;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;

import java.io.IOException;

public class ProductAddingMenu implements MenuItem {
    private static /*@ spec_public nullable @*/ Product productToAdd;

    private static /*@ spec_public non_null @*/ String chooseString = "How many of %s do you wanna add to basket? (from 1 to 100000)";

    @Override
    /*@ also
    @ assignable \nothing;
    @ signals_only NotLoggedInException, NonExistentProductId, IOException;
    @*/
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {
        System.out.println(String.format(chooseString, productToAdd.getName()));
        int count;
        count = InputUtil.getIntFromConsole();
        if (DataBasketManager.getInstance().addProductToBasket(productToAdd, count)) {
            System.out.println("Product was added successfully!");
        } else {
            System.out.println("Incorrect count of products. Product wasn't added to the basket.");
        }
    }
    
    /*@ requires productToAdd != null;
    @ assignable \everything;
    @ ensures ProductAddingMenu.productToAdd == productToAdd;
    @ signals_only NotLoggedInException, NonExistentProductId, IOException;
    @*/
    public static void displayMenuWithProduct(Product productToAdd) throws NotLoggedInException, NonExistentProductId, IOException {
        ProductAddingMenu.productToAdd = productToAdd;
        ProductAddingMenu productAddingMenu = new ProductAddingMenu();
        MenuManager.getInstance().displaySelectedMenu(productAddingMenu);
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public MenuNames getMenuName() {
        return MenuNames.PRODUCT_ADDING;
    }
}
