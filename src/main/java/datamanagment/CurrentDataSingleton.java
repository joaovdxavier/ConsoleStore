package datamanagment;

import dataobjects.Product;
import dataobjects.User;
import enums.DataTypes;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import menu.MenuManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CurrentDataSingleton {
    private User currentUser;
    private Product currentProduct;
    private int currentProductId;
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private HashMap<Integer, Integer> userBasket;
    private DataTypes dataTypeToGenerate;

    private static CurrentDataSingleton instance;

    private CurrentDataSingleton() throws IOException {
        currentUser = null;
        users = DataIOUtil.getUsers();
        products = DataIOUtil.getProducts();
        userBasket = new HashMap<>();
    }

    public static CurrentDataSingleton getInstance() throws IOException {
        if (instance == null) {
            instance = new CurrentDataSingleton();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<User> getUsers() throws IOException, NotLoggedInException, NonExistentProductId {
        if (users.isEmpty()) {
            dataTypeToGenerate = DataTypes.USER;
            MenuManager.getInstance().displaySelectedMenu(6);
        }
        return users;
    }

    public ArrayList<Product> getProducts() throws IOException, NotLoggedInException, NonExistentProductId {
        if (products.isEmpty()) {
            dataTypeToGenerate = DataTypes.PRODUCT;
            MenuManager.getInstance().displaySelectedMenu(6);
        }
        products.sort(Product::compareTo);
        return products;
    }

    public DataTypes getDataTypeToGenerate() {
        return dataTypeToGenerate;
    }

    public HashMap<Integer, Integer> getUserBasket() {
        return userBasket;
    }

    public int getCurrentProductId() {
        return currentProductId;
    }

    public Product getCurrentProduct() {
        return currentProduct;
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

    public void setCurrentProductId(int currentProductId) {
        this.currentProductId = currentProductId;
    }

    public void setDataTypeToGenerate(DataTypes dataTypeToGenerate) {
        this.dataTypeToGenerate = dataTypeToGenerate;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
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
