package datamanagment;

import com.fasterxml.jackson.databind.ObjectMapper;
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

public class DataUtil {
    private static ArrayList<User> users;
    private static ArrayList<Product> products;

    public static void writeProduct(Product product) throws IOException {
        String productFileName = ".\\src\\Main\\resources\\products\\id" + product.getId() + ".json";
        writeFile(new File(productFileName), product);
    }

    public static void writeUser(User user) throws IOException {
        String userFileName = ".\\src\\Main\\resources\\users\\user" + user.getId() + ".json";
        writeFile(new File(userFileName), user);
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
        ArrayList<User> usersList = null;
        ArrayList<Product> productsList = null;
        ArrayList<Path> filesList = null;
        User currentUser;
        Product currentProduct;
        int maxId = Integer.MIN_VALUE;

        if (dataTypes == DataTypes.USER) {
            filesList = getFilesList(".\\src\\Main\\resources\\users");
            usersList = new ArrayList<>();
        } else if (dataTypes == DataTypes.PRODUCT) {
            filesList = getFilesList(".\\src\\Main\\resources\\products");
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

    private static void writeFile(File file, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, object);
    }

    private static ArrayList<Path> getFilesList(String directoryPath) throws IOException {
        ArrayList<Path> filesList = new ArrayList<>();
//        Path directory = Path.of(directoryPath);
        Path directory = Paths.get(directoryPath);

        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path path : files)
                filesList.add(path);
        }
        return filesList;
    }
}
