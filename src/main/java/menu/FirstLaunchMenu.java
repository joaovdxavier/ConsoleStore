package menu;

import inpututils.IntInputUtil;
import productpattern.Product;
import testdata.AddProducts;
import testdata.AddUsers;
import userpattern.User;

import java.io.IOException;
import java.util.ArrayList;

public class FirstLaunchMenu {
    public static void DisplayFirstLaunchMenu (ArrayList<User> users, ArrayList<Product> products) throws IOException {
        if (users.size() == 0) {
            System.out.println("""
                    Seems like your users list is empty, do you wanna generate some data?
                    1. Yeas
                    2. No
                    """);
            int variant = IntInputUtil.get();
            if (variant == 1) {
                System.out.println("How many users do you wanna generate?");
                int count = IntInputUtil.get();
                AddUsers.addFewUsers(users, count);
            }
        }

        if (products.size() == 0) {
            System.out.println("""
                    Seems like your products list is empty, do you wanna generate some data?
                    1. Yeas
                    2. No
                    """);
            int variant = IntInputUtil.get();
            if (variant == 1) {
                System.out.println("How many products do you wanna generate?");
                int count = IntInputUtil.get();
                AddProducts.addFewProducts(products, count);
            }
        }
    }
}
