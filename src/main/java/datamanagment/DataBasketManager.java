package datamanagment;

import dataobjects.Product;

import java.util.HashMap;

public class DataBasketManager {
    //Pontos: 2
    //Lucas
    private static DataBasketManager instance;
    private HashMap<Integer, Integer> userBasket;

    private DataBasketManager() {
        userBasket = new HashMap<>();
    }

    public static DataBasketManager getInstance() {
        if (instance == null) {
            instance = new DataBasketManager();
        }
        return instance;
    }

    public HashMap<Integer, Integer> getUserBasket() {
        return userBasket;
    }

    public void setUserBasket(HashMap<Integer, Integer> userBasket) {
        this.userBasket = userBasket;
    }

    public boolean addProductToBasket(Product productToAdd, int count) {
        boolean wasAdded = false;
        if (userBasket.containsKey(productToAdd.getId())) {
            int previousCount = userBasket.get(productToAdd.getId());
            int newCount = previousCount + count;
            if (newCount >= 1 && newCount < 100000) {
                userBasket.put(productToAdd.getId(), newCount);
                wasAdded = true;
            }
        } else {
            if (count >= 1 && count < 100000) {
                userBasket.put(productToAdd.getId(), count);
                wasAdded = true;
            }
        }
        return wasAdded;
    }
}
