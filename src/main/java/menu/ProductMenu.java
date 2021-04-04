package menu;

import datamanagment.CurrentDataSingleton;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import dataobjects.Product;
import java.io.IOException;

public class ProductMenu implements MenuItem {
    @Override
    public void displayMenu() throws NonExistentProductId, IOException, NotLoggedInException {
        Product currentProduct = null;
        int productId = CurrentDataSingleton.getInstance().getCurrentProductId();

        for (Product product : CurrentDataSingleton.getInstance().getProducts()) {
            if (product.getId() == productId) {
                currentProduct = product;
                break;
            }
        }

        if (currentProduct == null) {
            throw new NonExistentProductId();
        } else {
            System.out.println("Product ID: " + currentProduct.getId());
            System.out.println("Product name: " + currentProduct.getName());
            System.out.println("Product description: " + currentProduct.getDescription());
            System.out.println("Product price: " + currentProduct.getPrice());
            System.out.println("Type 1 to add a product to the basket.");
            System.out.println("Type 2 to return to menu.");

            int paragraph;
            do {
                paragraph = InputUtil.getInt();
                if (paragraph == 1) {
                    CurrentDataSingleton.getInstance().setCurrentProduct(currentProduct);
                    MenuManager.getInstance().displaySelectedMenu(7);
                }
            } while (paragraph != 1 && paragraph != 2);
        }
    }

    @Override
    public int getMenuID() {
        return 5;
    }
}
