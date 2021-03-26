package menu;

import inpututils.InputUtil;
import productpattern.Product;
import testdata.AddProducts;
import testdata.AddUsers;
import userpattern.User;
import java.io.IOException;
import java.util.ArrayList;

public class FirstLaunchMenu {
    public static void DisplayFirstLaunchMenu (ArrayList<User> users, ArrayList<Product> products) throws IOException {
        if (users == null || users.isEmpty()) {
            System.out.println(
                    "Seems like your users list is empty, do you wanna generate some data?\n" +
                    "1. Yeas\n" +
                    "2. No\n");
            int variant = InputUtil.getInt();
            if (variant == 1) {
                System.out.println("How many users do you wanna generate?");
                int count = InputUtil.getInt();
                AddUsers.addFewUsers(users, count);
            }
        }

        if (products == null || products.isEmpty()) {
            System.out.println(
                    "Seems like your products list is empty, do you wanna generate some data?\n" +
                    "1. Yeas\n" +
                    "2. No\n");
            int variant = InputUtil.getInt();
            if (variant == 1) {
                System.out.println("How many products do you wanna generate?");
                int count = InputUtil.getInt();
                AddProducts.addFewProducts(products, count);
            }
        }
    }
}
