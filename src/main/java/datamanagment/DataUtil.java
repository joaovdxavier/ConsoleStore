package datamanagment;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.ObjectType;
import menu.FirstLaunchMenu;
import productpattern.Product;
import userpattern.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DataUtil {
    private static ArrayList<User> users;
    private static ArrayList<Product> products;

    public static void writeProduct(Product product) throws IOException {
        String productFileName = ".\\src\\main\\resources\\products\\id" + product.getProductID() + ".json";
        writeFile(new File(productFileName), product);
    }

    public static void writeUser(User user) throws IOException {
        String userFileName = ".\\src\\main\\resources\\users\\user" + user.getUserID() + ".json";
        writeFile(new File(userFileName), user);
    }


    public static ArrayList<Product> getProducts() throws IOException {
        fillArrayList(ObjectType.PRODUCT);
        return products;
    }

    public static ArrayList<User> getUsers() throws IOException {
        fillArrayList(ObjectType.USER);
        return users;
    }

    private static void fillArrayList(ObjectType objectType) throws IOException {
        ArrayList<User> usersList = null;
        ArrayList<Product> productsList = null;
        ArrayList<Path> filesList = null;
        User currentUser;
        Product currentProduct;
        int maxId = Integer.MIN_VALUE;

        if (objectType == ObjectType.USER) {
            filesList = getFilesList(".\\src\\main\\resources\\users");
            usersList = new ArrayList<>();
        } else if (objectType == ObjectType.PRODUCT) {
            filesList = getFilesList(".\\src\\main\\resources\\products");
            productsList = new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        if (!filesList.isEmpty()) {
            for (Path path : filesList) {
                if (objectType == ObjectType.USER) {
                    currentUser = mapper.readValue(new File(path.toString()), User.class);
                    usersList.add(currentUser);
                    if (currentUser.getUserID() > maxId) maxId = currentUser.getUserID();
                } else if (objectType == ObjectType.PRODUCT) {
                    currentProduct = mapper.readValue(new File(path.toString()), Product.class);
                    productsList.add(currentProduct);
                    if (currentProduct.getProductID() > maxId) maxId = currentProduct.getProductID();
                }
            }
            if (objectType == ObjectType.USER) {
                User.setCountID(maxId);
            } else if (objectType == ObjectType.PRODUCT) {
              Product.setCountID(maxId);
            }
        } else {
            FirstLaunchMenu.DisplayFirstLaunchMenu(usersList, productsList);
        }

        users = usersList;
        products = productsList;
    }

    private static void writeFile(File file, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, object);
    }

    private static ArrayList<Path> getFilesList(String directoryPath) throws IOException {
        ArrayList<Path> filesList = new ArrayList<>();
        Path directory = Path.of(directoryPath);

        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path path : files)
                filesList.add(path);
        }
        return filesList;
    }
}
