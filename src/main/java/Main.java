import datamanagment.DataUtil;
import menu.DataGenerationMenu;
import menu.MenuData;
import dataobjects.Product;
import dataobjects.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static HashMap<Integer, Integer> userBasket = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) throws IOException {
        products = DataUtil.getProducts();
        users = DataUtil.getUsers();

        MenuData menuData = new MenuData(currentUser, users, products, userBasket);
        menuData.launchStartMenu();

        System.out.println("Buy! See you later, Space Cowboy!");
    }
}
