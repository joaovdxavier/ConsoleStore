package menu;

import exceptions.NotLoggedInException;
import inpututils.IntInputUtil;
import productpattern.Product;
import userpattern.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BasketMenu {
    static double countPrice = 0;
    public static void displayBasketMenu(User currentUser, ArrayList<User> currentUsers, ArrayList<Product> currentProducts,
                                         HashMap<Integer, Integer> userBasket) throws NotLoggedInException {
        Check.checkLogin(currentUser, currentUsers, currentProducts, userBasket);



        System.out.println("Your basket: ");

        if (userBasket.isEmpty()) {
            System.out.println("Your basket is empty.");
        } else {
            userBasket.forEach((id,count) -> {
                currentProducts.forEach(product -> {
                    if (product.getProductID() == id) {
                        System.out.println("Product name: " + product.getName() + " Count: " + count);
                        countPrice += product.getPrice() * count;
                    }
                });
            });
            System.out.println("Total cost of items in the basket: " + countPrice);
        }

        int paragraph;
        do {
            System.out.println("""
                    1. Type 1 to clear basket.
                    2. Type 2 to return to main menu""");
            paragraph = IntInputUtil.get();
            if (paragraph == 1) {
                userBasket.clear();
                System.out.println("Basket was successfully cleared! ");
                return;
            } else if(paragraph == 2) {
                return;
            }
        } while (paragraph != 1 & paragraph != 2);
    }
}
