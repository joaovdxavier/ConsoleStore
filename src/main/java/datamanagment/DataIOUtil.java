package datamanagment;

import enums.DataTypes;
import enums.UserRoles;
import dataobjects.Product;
import dataobjects.User;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.eclipsesource.json.*;

public class DataIOUtil {
    //Pontos: 5
    //João
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static String resourcesDir = "./src/main/resources";
    private static String usersDir = "./src/main/resources/users";
    private static String productsDir = "./src/main/resources/products";
    private static String productFileName = "./src/main/resources/products/id%s.json";
    private static String userFileName = "./src/main/resources/users/user%s.json";


    public static void writeProduct(Product product) throws IOException {
        String productFileName = String.format(DataIOUtil.productFileName, product.getId());
        writeFile(productsDir, new File(productFileName), product.serialize());
    }

    public static void writeUser(User user) throws IOException {
        String userFileName = String.format(DataIOUtil.userFileName, user.getId());
        writeFile(usersDir, new File(userFileName), user.serialize());
    }

    public static ArrayList<Product> getProducts() throws IOException {
        fillArrayList(DataTypes.PRODUCT);
        return products;
    }

    public static ArrayList<User> getUsers() throws IOException {
        fillArrayList(DataTypes.USER);
        return users;
    }

    private static void fillArrayList(DataTypes dataTypes) throws IOException {
        ArrayList<User> usersList = new ArrayList<>();
        ArrayList<Product> productsList = new ArrayList<>();
        ArrayList<Path> filesList = new ArrayList<>();
        User currentUser;
        Product currentProduct;
        int maxId = Integer.MIN_VALUE;

        if (dataTypes == DataTypes.USER) {
            filesList = getFilesList(usersDir);
            usersList = new ArrayList<>();
        } else if (dataTypes == DataTypes.PRODUCT) {
            filesList = getFilesList(productsDir);
            productsList = new ArrayList<>();
        }

        if (!filesList.isEmpty()) {
            for (Path path : filesList) {
                if (dataTypes == DataTypes.USER) {
                	String s = new String(Files.readAllBytes(path));
                	JsonObject object = Json.parse(s).asObject();
                    currentUser = new User(object.get("Name").asString(), object.get("Last Name").asString(), UserRoles.valueOf(object.get("Role").asString()), object.get("Email").asString(), object.get("Password").asString());
                    usersList.add(currentUser);
                    if (currentUser.getId() > maxId) maxId = currentUser.getId();
                } else if (dataTypes == DataTypes.PRODUCT) {
                	String s = new String(Files.readAllBytes(path));
                	JsonObject object = Json.parse(s).asObject();
                    currentProduct = new Product(object.get("Name").asString(), object.get("Price").asDouble(), object.get("Description").asString());
                    productsList.add(currentProduct);
                    if (currentProduct.getId() > maxId) maxId = currentProduct.getId();
                }
            }
            if (dataTypes == DataTypes.USER) {
                User.setCountID(maxId);
                users = usersList;
            } else if (dataTypes == DataTypes.PRODUCT) {
                Product.setCountID(maxId);
                products = productsList;
            }
        }
    }

    private static void writeFile(String dir, File file, JsonObject object) throws IOException {
        createDir(dir);
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(object.toString());
        }
    }

    private static ArrayList<Path> getFilesList(String directoryPath) throws IOException {
        createDir(resourcesDir);
        createDir(usersDir);
        createDir(productsDir);

        ArrayList<Path> filesList = new ArrayList<>();
        Path directory = Paths.get(directoryPath);

        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path path : files)
                filesList.add(path);
        }
        return filesList;
    }

    public static boolean dirExists(String dir) {
        return (Files.isDirectory(Paths.get(dir)) || Files.exists(Paths.get(dir)));
    }

    public static void createDir(String dir) throws IOException {
        Path path = Paths.get(dir);
        if (!dirExists(dir)) {
            Files.createDirectory(path);
        }
    }
}
