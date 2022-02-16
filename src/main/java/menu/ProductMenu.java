package main.java.menu;

import main.java.datamanagment.DataStoreManager;
import main.java.enums.MenuNames;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import main.java.inpututils.InputUtil;
import main.java.dataobjects.Product;

import java.io.IOException;

public class ProductMenu implements MenuItem {
    private static /*@ spec_public nullable @*/ int productId;

    @Override
    /*@ also
    @ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public /*@ pure @*/ void displayMenu() throws NonExistentProductId, IOException, NotLoggedInException {
        Product currentProduct = findProduct(productId);

        if (currentProduct == null) {
            throw new NonExistentProductId();
        } else {
            System.out.println(currentProduct);
            System.out.println("Type 1 to add a product to the basket.");
            System.out.println("Type 2 to return to menu.");

            int paragraph;
            do {
                paragraph = InputUtil.getIntFromConsole();
                if (paragraph == 1) {
                    ProductAddingMenu.displayMenuWithProduct(currentProduct);
                }
            } while (paragraph != 1 && paragraph != 2);
        }
    }

    /*@ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public static /*@ pure @*/ Product findProduct(int productId) throws IOException, NotLoggedInException, NonExistentProductId {
        Product foundProduct = null;
        for (Product product : DataStoreManager.getInstance().getProducts()) {
            if (product.getId() == productId) {
                foundProduct = product;
                break;
            }
        }
        return  foundProduct;
    }

    /*@ assignable \everything;
    @ ensures ProductMenu.productId == productId;
    @ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public static void displayMenuWithProductId(int productId) throws NotLoggedInException, NonExistentProductId, IOException {
        ProductMenu.productId = productId;
        ProductMenu productMenu = new ProductMenu();
        MenuManager.getInstance().displaySelectedMenu(productMenu);
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.PRODUCT;
    }
}
