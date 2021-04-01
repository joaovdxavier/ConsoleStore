package menu;

import exceptions.NonExistentProductId;
import inpututils.InputUtil;
import dataobjects.Product;

public class ProductMenu {
    public static void displayProductMenu(MenuData menuData, int productId) throws NonExistentProductId {

        Product currentProduct = null;

        for (Product product : menuData.getProducts()) {
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
            System.out.println("Type 2 to return to product list.");

            int paragraph;
            do {
                paragraph = InputUtil.getInt();
                if (paragraph == 1) {
                    System.out.println("How many of " +
                            currentProduct.getName() +
                            " do you wanna add to basket? (from 1 to 100000)");
                    int count;
                    do {
                        count = InputUtil.getInt();
                    } while (count < 1 || count > 100000);

                    if (menuData.getUserBasket().containsKey(productId)) {
                        int previousCount = menuData.getUserBasket().get(currentProduct.getId());
                        int newCount = previousCount + count;
                        menuData.getUserBasket().put(currentProduct.getId(), newCount);
                    } else {
                        menuData.getUserBasket().put(currentProduct.getId(), count);
                    }

                    System.out.println("Product was added successfully!");
                    return;
                } else if (paragraph == 2) {
                    return;
                }
            } while (paragraph != 1 & paragraph != 2);
        }
    }
}
