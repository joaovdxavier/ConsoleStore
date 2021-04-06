package datamanagment;

import dataobjects.Product;
import dataobjects.User;
import enums.DataTypes;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import menu.DataGenerationMenu;
import java.io.IOException;
import java.util.ArrayList;

public class DataStoreManager {
    private User currentUser;
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private static DataStoreManager instance;

    private DataStoreManager() throws IOException {
        currentUser = null;
        users = DataIOUtil.getUsers();
        products = DataIOUtil.getProducts();
    }

    public static DataStoreManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DataStoreManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<User> getUsers() throws IOException, NotLoggedInException, NonExistentProductId {
        if (users.isEmpty()) {
            DataGenerationMenu.chooseType(DataTypes.USER);
        }
        return users;
    }

    public ArrayList<Product> getProducts() throws IOException, NotLoggedInException, NonExistentProductId {
        if (products.isEmpty()) {
            DataGenerationMenu.chooseType(DataTypes.PRODUCT);
        }
        products.sort(Product::compareTo);
        return products;
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

    public void addUser(User newUser) {
        if (users != null) {
            users.add(newUser);
        }
    }

    public void addProduct(Product newProduct) {
        if (products != null) {
            products.add(newProduct);
        }
    }
}
