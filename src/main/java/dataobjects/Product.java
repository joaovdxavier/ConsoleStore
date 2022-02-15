package dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Comparable<Product>, DataObject {
    //Pontos: 1
    //Renan
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

    @JsonProperty("countID")
    private static /*@ spec_public non_null @*/ int countID = 0;

    @JsonProperty("Name")
    private /*@ spec_public nullable @*/ String name;

    @JsonProperty("Price")
    private /*@ spec_public nullable @*/ double price;

    @JsonProperty("Description")
    private /*@ spec_public nullable @*/ String description;

    @JsonProperty("ID")
    private /*@ spec_public nullable @*/ int id;
    
    //@ public invariant 0 <= countID;

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

    /*@ assignable Product.countID; 
    @ ensures Product.countID == countID;
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
}
