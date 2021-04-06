package dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Comparable<Product>, DataObject {

    public Product() {
        this.countID++;
        this.id = countID;
    }

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.countID++;
        this.id = countID;
    }



    @JsonProperty("countID")
    private static int countID = 0;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Price")
    private double price;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("ID")
    private int id;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public static int getCountID() {
        return countID;
    }

    public static void setCountID(int countID) {
        Product.countID = countID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product " +
                "name: '" + name + '\n' +
                "price: " + price + '\n' +
                "description: '" + description + '\n' +
                "id: " + id;
    }

    @Override
    public int compareTo(Product o) {
        return this.id - o.id;
    }
}
