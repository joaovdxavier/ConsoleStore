package datamanagment;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataobjects.DataObject;
import enums.DataTypes;
import dataobjects.Product;
import dataobjects.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataIOUtil {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static String resourcesDir = ".\\src\\Main\\resources";
    private static String usersDir = ".\\src\\Main\\resources\\users";
    private static String productsDir = ".\\src\\Main\\resources\\products";


    public static void writeProduct(Product product) throws IOException {
        String productFileName = ".\\src\\Main\\resources\\products\\id" + product.getId() + ".json";
        writeFile(productsDir, new File(productFileName), product);
    }

    public static void writeUser(User user) throws IOException {
        String userFileName = ".\\src\\Main\\resources\\users\\user" + user.getId() + ".json";
        writeFile(usersDir, new File(userFileName), user);
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

        ObjectMapper mapper = new ObjectMapper();
        if (!filesList.isEmpty()) {
            for (Path path : filesList) {
                if (dataTypes == DataTypes.USER) {
                    currentUser = mapper.readValue(new File(path.toString()), User.class);
                    usersList.add(currentUser);
                    if (currentUser.getId() > maxId) maxId = currentUser.getId();
                } else if (dataTypes == DataTypes.PRODUCT) {
                    currentProduct = mapper.readValue(new File(path.toString()), Product.class);
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

    private static void writeFile(String dir, File file, Object object) throws IOException {
        createDir(dir);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, object);
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
