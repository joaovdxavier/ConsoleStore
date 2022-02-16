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
    private /*@ spec_public nullable @*/ User currentUser;
    private /*@ spec_public nullable @*/ ArrayList<User> users;
    private /*@ spec_public nullable @*/ ArrayList<Product> products;
    private /*@ spec_public nullable @*/ static DataStoreManager instance;

    /*@ assignable currentUser, users, products;
    @ ensures currentUser == null && users != null && products != null; 
    @ signals_only IOException;
    @*/
    private DataStoreManager() throws IOException {
        currentUser = null;
        users = DataIOUtil.getUsers();
        products = DataIOUtil.getProducts();
    }

    /*@ assignable instance;
    @ ensures instance != null;
    @ ensures \result == DataStoreManager.instance;
    @ signals_only IOException;
    @*/
    public static DataStoreManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DataStoreManager();
        }
        return instance;
    }

    //@ ensures \result == currentUser;
    public /*@ pure @*/ User getCurrentUser() {
        return currentUser;
    }

    /*@ assignable \nothing;
    @ ensures \result == users;
    @ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public ArrayList<User> getUsers() throws IOException, NotLoggedInException, NonExistentProductId {
        if (users.isEmpty()) {
            DataGenerationMenu.chooseType(DataTypes.USER);
        }
        return users;
    }

    /*@ assignable \nothing;
    @ ensures \result == products;
    @ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public ArrayList<Product> getProducts() throws IOException, NotLoggedInException, NonExistentProductId {
        if (products.isEmpty()) {
            DataGenerationMenu.chooseType(DataTypes.PRODUCT); //assignable estará em Menu/DataGenerationMenu
        }
        products.sort(Product::compareTo); //será descrito em dataobjects/Product
        return products;
    }

    /*@ assignable this.currentUser;
    @ ensures this.currentUser == currentUser;
    @*/
    public void setCurrentUser(/*@ nullable @*/ User currentUser) {
        this.currentUser = currentUser;
    }

    /*@ assignable this.users;
    @ ensures this.users == users;
    @*/
    public void setUsers(/*@ nullable @*/ ArrayList<User> users) {
        this.users = users;
    }

    /*@ assignable this.products;
    @ ensures this.products == products;
    @*/
    public void setProducts(/*@ nullable @*/ ArrayList<Product> products) {
        this.products = products;
    }

    /*@ assignable users;
    @ ensures (users != null) ==> users.size() > \old(users.size());
    @*/
    public void addUser(/*@ non_null @*/ User newUser) {
        if (users != null) {
            users.add(newUser);
        }
    }

    /*@ assignable products;
    @ ensures (products != null) ==> products.size() > \old(products.size());
    @*/
    public void addProduct(/*@ non_null @*/ Product newProduct) {
        if (products != null) {
            products.add(newProduct);
        }
    }
}
