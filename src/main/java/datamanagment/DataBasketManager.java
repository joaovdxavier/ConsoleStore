package datamanagment;

import dataobjects.Product;

import java.util.HashMap;

public class DataBasketManager {
    //Pontos: 2
    //Lucas
    private /*@ spec_public nullable @*/ static DataBasketManager instance;
    private /*@ spec_public nullable @*/ HashMap<Integer, Integer> userBasket;

    /*@ assignable userBasket;
    @ ensures userBasket != null; 
    @*/
    private DataBasketManager() {
        userBasket = new HashMap<>();
    }

    /*@ assignable DataBasketManager.instance;
    @ ensures \result == DataBasketManager.instance;
    @*/
    public static DataBasketManager getInstance() {
        if (instance == null) {
            instance = new DataBasketManager();
        }
        return instance;
    }

    /*@ assignable \nothing;
    @ ensures \result == userBasket;
    @*/
    public /*@ pure @*/ HashMap<Integer, Integer> getUserBasket() {
        return userBasket;
    }

    /*@ assignable userBasket;
    @ ensures this.userBasket == userBasket;
    @*/
    public void setUserBasket(HashMap<Integer, Integer> userBasket) {
        this.userBasket = userBasket;
    }

    /*@   requires userBasket.containsKey(productToAdd.getId());
    @   assignable userBasket;
    @   ensures (count >= 1 && count < 100000) ==> \result;
    @   ensures Integer.parseInt(userBasket.get(productToAdd.getId())) == \old(Integer.parseInt(userBasket.get(productToAdd.getId()))) + count;
    @ also
    @   assignable userBasket;
    @   ensures (count >= 1 && count < 100000) ==> \result;
    @*/
    public boolean addProductToBasket(/*@ non_null @*/ Product productToAdd, int count) {
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
