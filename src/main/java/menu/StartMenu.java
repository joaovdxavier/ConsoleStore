package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.IntInputUtil;
import productpattern.Product;
import userpattern.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StartMenu {
    private static User currentUser;
    private static ArrayList<User> users;
    private static ArrayList<Product> products;
    private static HashMap<Integer, Integer> userBasket;

    public StartMenu(User currentUSer, ArrayList<User> users, ArrayList<Product> products, HashMap<Integer, Integer> userBasket) {
        this.currentUser = currentUSer;
        this.users = users;
        this.products = products;
        this.userBasket = userBasket;
    }

    public void displayStartMenu()
            throws NotLoggedInException {

        int menuNumber;
        do {
            System.out.println("""
                    Choose one of the menu paragraphs:
                    1. Store
                    2. Basket
                    3. Profile
                    4. Login / registration
                    5. Exit
                    """);
            menuNumber = IntInputUtil.get();

            try {
                switch (menuNumber) {
                    case 1:
                        StoreMenu.displayStoreMenu(currentUser, users, products, userBasket);
                        break;
                    case 2:
                        BasketMenu.displayBasketMenu(currentUser, users, products, userBasket);
                        break;
                    case 3:
                        ProfileMenu.displayProfileMenu(currentUser, users, products, userBasket);
                        break;
                    case 4:
                        LoginMenu.displayLoginMenu(currentUser, users, products, userBasket);
                        break;
                    case 5:
                        return;
                }
            } catch (NotLoggedInException | IOException | NonExistentProductId exception) {
                exception.printStackTrace();
            }
        } while (true);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static HashMap<Integer, Integer> getUserBasket() {
        return userBasket;
    }

    public static void setCurrentUser(User currentUser) {
        StartMenu.currentUser = currentUser;
    }

    public static void setUsers(ArrayList<User> users) {
        StartMenu.users = users;
    }

    public static void setProducts(ArrayList<Product> products) {
        StartMenu.products = products;
    }

    public static void setUserBasket(HashMap<Integer, Integer> userBasket) {
        StartMenu.userBasket = userBasket;
    }
}
