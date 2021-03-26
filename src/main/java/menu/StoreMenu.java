package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.IntInputUtil;
import productpattern.Product;
import userpattern.User;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreMenu {
    public static void displayStoreMenu(User currentUser, ArrayList<User> currentUsers, ArrayList<Product> currentProducts,
                                        HashMap<Integer, Integer> userBasket) throws NotLoggedInException, NonExistentProductId {

        Check.checkLogin(currentUser, currentUsers, currentProducts, userBasket);

        System.out.println("Product list.");
        for (Product product : currentProducts) {
            System.out.print("Product id: " + product.getProductID());
            System.out.println(". Product name: " + product.getName());

        }

        System.out.println("Type id of product to watch and add it to basket.\n" +
                " Type 0 to return to main menu.\n");

        int variant = IntInputUtil.get();

        if (variant < 0 || variant > (currentProducts.size())) {
            throw new NonExistentProductId();
        }
    }
}
