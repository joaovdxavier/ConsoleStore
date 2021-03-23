import datamanagment.DataReadUtil;
import datamanagment.DataWriteUtil;
import exceptions.NotLoggedInException;
import menu.StartMenu;
import productpattern.Product;
import testdata.AddProducts;
import testdata.AddUsers;
import userpattern.User;

import java.io.IOException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws IOException, NotLoggedInException {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        User currentUser = null;
        products = DataReadUtil.getProducts();
        users = DataReadUtil.getUsers();
        StartMenu.displayStartMenu(currentUser, users, products);


//        AddProducts.addFewProducts(products);
//        products.forEach(Product -> {
//            try {
//                DataWriteUtil.writeProduct(Product);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//
//
//        AddUsers.addFewUsers(users);
//        users.forEach(User -> {
//            try {
//                DataWriteUtil.writeUsers(User);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });

        System.out.println("Buy! See you later!");
    }
}
