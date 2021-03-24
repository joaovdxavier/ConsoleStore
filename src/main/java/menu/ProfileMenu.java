package menu;

import exceptions.NotLoggedInException;
import productpattern.Product;
import userpattern.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileMenu {
    public static void displayProfileMenu(User currentUser, ArrayList<User> currentUsers, ArrayList<Product> currentProducts,
                                          HashMap<Integer, Integer> userBasket) throws NotLoggedInException {

        Check.checkLogin(currentUser, currentUsers, currentProducts, userBasket);

        System.out.println("User name: " + currentUser.getName());
        System.out.println("User last name: " + currentUser.getLastName());
        System.out.println("User email: " + currentUser.getEmail());
        System.out.println("User password: " + currentUser.getPassword());
        System.out.println();
    }
}
