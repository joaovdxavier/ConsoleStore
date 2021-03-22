import datamanagment.DataReadUtil;
import datamanagment.DataWriteUtil;
import menu.StartMenu;
import productpattern.Product;
import testdata.AddProducts;
import testdata.AddUsers;
import userpattern.User;

import java.io.IOException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        User currentUser;
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();

        products = DataReadUtil.getProducts();
        AddProducts.addFewProducts(products);
        products.forEach(Product -> {
            try {
                DataWriteUtil.writeProduct(Product);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        users = DataReadUtil.getUsers();
        AddUsers.addFewUsers(users);
        users.forEach(User -> {
            try {
                DataWriteUtil.writeUsers(User);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


    }
}
