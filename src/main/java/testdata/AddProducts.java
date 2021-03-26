package testdata;

import datamanagment.DataUtil;
import productpattern.Product;

import java.io.IOException;
import java.util.ArrayList;

public class AddProducts {
    private static String name = "Product #%s";
    private static String description = "Product #%s description";

    public static void addFewProducts(ArrayList<Product> products, int count) throws IOException {
        int i = Product.getCountID();
        int j = i + count;
        for (i = Product.getCountID(); i < j; i++) {
            Product newProduct = new Product();
            int id = newProduct.getProductID();
            newProduct.setName(String.format(name, id));
            newProduct.setDescription(String.format(description, id));
            newProduct.setPrice(id * 10);

            if (products == null) products = new ArrayList<>();
            DataUtil.writeProduct(newProduct);
        }
    }
}
