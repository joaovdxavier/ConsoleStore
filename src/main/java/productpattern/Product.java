package productpattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Comparable<Product> {

    public Product() {
        this.countID++;
        this.productID = countID;
    }

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.countID++;
        this.productID = countID;
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
    private int productID;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getProductID() {
        return productID;
    }

    public static int getCountID() {
        return countID;
    }

    public static void setCountID(int countID) {
        Product.countID = countID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
    public int compareTo(Product o) {
        return this.productID - o.productID;
    }
}
