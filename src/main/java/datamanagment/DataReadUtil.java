package datamanagment;

import com.fasterxml.jackson.databind.ObjectMapper;
import productpattern.Product;
import userpattern.User;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataReadUtil {

    public static ArrayList<Path> getFilesList(String directoryPath) throws IOException {
        ArrayList<Path> filesList = new ArrayList<>();
        Path directory = Path.of(directoryPath);

        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
            for (Path path : files)
               filesList.add(path);
        }

        return filesList;
    }

    public static ArrayList<Product> getProducts() throws IOException {
        ArrayList<Product> productsList = new ArrayList<>();
        ArrayList<Path> filesList = getFilesList(".\\src\\main\\resources\\products");
        ObjectMapper mapper = new ObjectMapper();

        if (filesList.size() > 0) {
            for (Path path : filesList) {
                String fileName = path.toString();
                productsList.add(mapper.readValue(new File(path.toString()), Product.class));
            }

            int maxId = Integer.MIN_VALUE;
            for (Product product : productsList) {
                if (product.getProductID() > maxId) {
                    maxId = product.getProductID();
                }
            }
            Product.setCountID(maxId);
        }
        return productsList;
    }

    public static ArrayList<User> getUsers() throws IOException {
        ArrayList<User> usersList = new ArrayList<>();
        ArrayList<Path> filesList = getFilesList(".\\src\\main\\resources\\users");
        ObjectMapper mapper = new ObjectMapper();

        if (filesList.size() > 0) {
            for (Path path : filesList) {
                String fileName = path.toString();
                usersList.add(mapper.readValue(new File(path.toString()), User.class));
            }

            int maxId = Integer.MIN_VALUE;
            for (User user : usersList) {
                if (user.getUserID() > maxId) {
                    maxId = user.getUserID();
                }
            }
            User.setCountID(maxId);
        }
        return usersList;
    }

}
