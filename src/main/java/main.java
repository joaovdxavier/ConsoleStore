import datamanagment.DataUtil;
import exceptions.NotLoggedInException;
import menu.FirstLaunchMenu;
import menu.MenuData;
import productpattern.Product;
import userpattern.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static HashMap<Integer, Integer> userBasket = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) throws IOException, NotLoggedInException {
        products = DataUtil.getProducts();
        users = DataUtil.getUsers();
        if (!products.isEmpty()) products.sort(Product::compareTo);

        MenuData menuData = new MenuData(currentUser, users, products, userBasket);
        menuData.launchStartMenu();

        System.out.println("Buy! See you later, Space Cowboy!");
    }
}
