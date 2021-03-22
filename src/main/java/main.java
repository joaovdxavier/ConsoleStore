import menu.StartMenu;
import productpattern.Product;
import testdata.AddProducts;
import testdata.AddUsers;
import userpattern.AbstractUser;
import userpattern.Admin;
import userpattern.User;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ArrayList<AbstractUser> users = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        User currentUser;
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();

        AddUsers.addFewUsers(users);

        users.forEach(abstractUser -> {
            System.out.println(abstractUser.getName());
            System.out.println(abstractUser.getUserID());
        });

        AddProducts.addFewProducts(products);

        products.forEach(Product -> {
            System.out.println(Product.getName());
            System.out.println(Product.getProductID());
        });

    }
}
