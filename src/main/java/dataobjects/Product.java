package main.java.dataobjects;

import main.java.com.eclipsesource.json.*;

public class Product implements Comparable<Product>, DataObject {
    private static /*@ spec_public non_null @*/ String productInformation = "Product name: %s; id: %s; price: %s; description: %s.";

    /*@ assignable this.countID, this.id;
    @ ensures this.id == this.countID; 
    @ ensures this.countID == \old(this.countID)+1; 
    @*/
    public Product() {
        this.countID++;
        this.id = countID;
    }

    /*@ assignable this.countID, this.name, this.price, this.description, this.id;
    @ ensures this.id == this.countID; 
    @ ensures this.name == name; 
    @ ensures this.price == price; 
    @ ensures this.description == description; 
    @ ensures this.countID == \old(this.countID)+1; 
    @*/
    public Product( /*@ non_null @*/ String name, double price, /*@ non_null @*/ String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.countID++;
        this.id = countID;
    }

    private static /*@ spec_public nullable @*/ int countID = 0;

    private /*@ spec_public nullable @*/ String name;

    private /*@ spec_public nullable @*/ double price;

    private /*@ spec_public nullable @*/ String description;

    private /*@ spec_public nullable @*/ int id;
    
    //@ public invariant 0 <= countID;
    //@ public invariant 0 <= this.id;
    
    /*@ also
    @ assignable \nothing;
    @ ensures \result == name; 
    @*/
    public /*@ pure @*/ String getName() {
        return name;
    }

    /*@ assignable \nothing;
    @ ensures \result == price; 
    @*/
    public /*@ pure @*/ double getPrice() {
        return price;
    }

    /*@ assignable \nothing;
    @ ensures \result == description; 
    @*/
    public /*@ pure @*/ String getDescription() {
        return description;
    }

    /*@ also
    @ assignable \nothing;
    @ ensures \result == id; 
    @*/
    public /*@ pure @*/ int getId() {
        return id;
    }

    /*@ assignable \nothing;
    @ ensures \result == countID; 
    @*/
    public static /*@ pure @*/ int getCountID() {
        return countID;
    }

    /*@ ensures Product.countID == countID;
    @*/
    public static void setCountID(int countID) {
        Product.countID = countID;
    }

    /*@ assignable this.id; 
    @ ensures this.id == id;
    @*/
    public void setId(int id) {
        this.id = id;
    }

    /*@ assignable this.name; 
    @ ensures this.name == name;
    @*/
    public void setName( /*@ non_null @*/ String name) {
        this.name = name;
    }

    /*@ assignable this.price; 
    @ ensures this.price == price;
    @*/
    public void setPrice(double price) {
        this.price = price;
    }

    /*@ assignable this.description; 
    @ ensures this.description == description;
    @*/
    public void setDescription( /*@ non_null @*/ String description) {
        this.description = description;
    }

    @Override
    public /*@ pure @*/ String toString() {
        return String.format(productInformation, name, id, price, description);
    }

    @Override
    public /*@ pure @*/ int compareTo( /*@ non_null @*/ Product o) {
        return this.id - o.id;
    }
    
    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public JsonObject serialize() {
    	return Json.object().add("countID", this.countID).add("Name", this.name).add("Price", this.price).add("Description", this.description).add("ID", this.id);
    }
}
