package menu;

import datamanagment.DataUtil;
import dataobjects.Product;
import dataobjects.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuData {
    private User currentUser;
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private HashMap<Integer, Integer> userBasket;

    public MenuData(User currentUser, ArrayList<User> users, ArrayList<Product> products, HashMap<Integer, Integer> userBasket) throws IOException {
        if (products == null || users == null || products.isEmpty() || users.isEmpty()) {
            DataGenerationMenu.DisplayFirstLaunchMenu(users, products);
            products = DataUtil.getProducts();
            users = DataUtil.getUsers();
        }
        this.currentUser = currentUser;
        this.users = users;
        this.products = products;
        this.userBasket = userBasket;
    }

    public void launchStartMenu() {
        StartMenu.displayStartMenu(this);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setUserBasket(HashMap<Integer, Integer> userBasket) {
        this.userBasket = userBasket;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public HashMap<Integer, Integer> getUserBasket() {
        return userBasket;
    }
}
