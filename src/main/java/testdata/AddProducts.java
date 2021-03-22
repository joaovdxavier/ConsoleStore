package testdata;

import productpattern.Product;

import java.util.ArrayList;

public class AddProducts {
    public static void addFewProducts(ArrayList<Product> products){
        Product product1 = new Product("Product1", 1.1, "Product 1 description");
        Product product2 = new Product("Product2", 2.2, "Product 2 description");
        Product product3 = new Product("Product3", 3.3, "Product 3 description");
        Product product4 = new Product("Product4", 4.4, "Product 4 description");

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
    }
}
