package menu;

import exceptions.NotLoggedInException;
import productpattern.Product;
import userpattern.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Check {
    public static void checkLogin(User currentUser, ArrayList<User> currentUsers, ArrayList<Product>  currentProducts,
                                  HashMap<Integer, Integer> userBasket) throws NotLoggedInException {

        if (currentUser == null) {
            throw new NotLoggedInException();
        }
    }
}
