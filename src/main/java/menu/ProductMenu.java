package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import dataobjects.Product;

import java.io.IOException;

public class ProductMenu implements MenuItem {
    //Pontos: 3
    //Renan
    private static int productId;

    @Override
    public void displayMenu() throws NonExistentProductId, IOException, NotLoggedInException {
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

    public static Product findProduct(int productId) throws IOException, NotLoggedInException, NonExistentProductId {
        Product foundProduct = null;
        for (Product product : DataStoreManager.getInstance().getProducts()) {
            if (product.getId() == productId) {
                foundProduct = product;
                break;
            }
        }
        return  foundProduct;
    }

    public static void displayMenuWithProductId(int productId) throws NotLoggedInException, NonExistentProductId, IOException {
        ProductMenu.productId = productId;
        ProductMenu productMenu = new ProductMenu();
        MenuManager.getInstance().displaySelectedMenu(productMenu);
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.PRODUCT;
    }
}
