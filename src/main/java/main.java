import datamanagment.DataReadUtil;
import exceptions.NotLoggedInException;
import menu.FirstLaunchMenu;
import menu.StartMenu;
import productpattern.Product;
import userpattern.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static HashMap<Integer, Integer> userBasket = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) throws IOException, NotLoggedInException {

        products = DataReadUtil.getProducts();
        products.sort(Product::compareTo);
        users = DataReadUtil.getUsers();

        FirstLaunchMenu.DisplayFirstLaunchMenu(users, products);

        if (products.isEmpty()) {
            System.out.println("Seems like list if products is ");
        }

        StartMenu startMenu = new StartMenu(currentUser, users, products, userBasket);

        startMenu.displayStartMenu();

        System.out.println("Buy! See you later!");
    }
}
