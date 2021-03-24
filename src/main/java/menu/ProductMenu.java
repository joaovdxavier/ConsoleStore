package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.IntInputUtil;
import productpattern.Product;
import userpattern.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductMenu {
    public static void displayProductMenu(int productId, User currentUser, ArrayList<User> currentUsers,
                                          ArrayList<Product> currentProducts, HashMap<Integer, Integer> userBasket)
            throws NonExistentProductId, NotLoggedInException {

        Product currentProduct = null;

        for (Product product : currentProducts) {
            if (product.getProductID() == productId) {
                currentProduct = product;
                break;
            }
        }

        if (currentProduct == null) {
            throw new NonExistentProductId();
        } else {
            System.out.println("Product ID: " + currentProduct.getProductID());
            System.out.println("Product name: " + currentProduct.getName());
            System.out.println("Product description: " + currentProduct.getDescription());
            System.out.println("Product price: " + currentProduct.getPrice());
            System.out.println("Type 1 to add a product to the basket.");
            System.out.println("Type 2 to return to product list.");

            int paragraph;
            do {
                paragraph = IntInputUtil.get();
                if (paragraph == 1) {
                    System.out.println("How many of " +
                            currentProduct.getName() +
                            " do you wanna add to basket? (from 1 to 100000)");
                    int count;
                    do {
                        count = IntInputUtil.get();
                    } while (count < 1 || count > 100000);

                    if (userBasket.containsKey(count)) {
                        int previousCount = userBasket.get(currentProduct.getProductID());
                        int newCount = previousCount + count;
                        userBasket.put(currentProduct.getProductID(), newCount);
                    } else {
                        userBasket.put(currentProduct.getProductID(), count);
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
