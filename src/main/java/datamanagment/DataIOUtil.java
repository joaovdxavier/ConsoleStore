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
	private static /*@ spec_public nullable @*/ ArrayList<User> users = new ArrayList<>();
    private static /*@ spec_public nullable @*/ ArrayList<Product> products = new ArrayList<>();
    private static /*@ spec_public non_null @*/ String resourcesDir = "./src/main/resources";
    private static /*@ spec_public non_null @*/ String usersDir = "./src/main/resources/users";
    private static /*@ spec_public non_null @*/ String productsDir = "./src/main/resources/products";
    private static /*@ spec_public non_null @*/ String productFileName = "./src/main/resources/products/id%s.json";
    private static /*@ spec_public non_null @*/ String userFileName = "./src/main/resources/users/user%s.json";

    
    /* @ requires product != null;
    @ signals_only IOException;
    */
    public /*@ pure @*/ static void writeProduct(Product product) throws IOException {
        String productFileName = String.format(DataIOUtil.productFileName, product.getId());
        writeFile(productsDir, new File(productFileName), product.serialize());
    }
    
    /* @ requires user != null;
    @ signals_only IOException;
    */
    public /*@ pure @*/ static void writeUser(User user) throws IOException {
        String userFileName = String.format(DataIOUtil.userFileName, user.getId());
        writeFile(usersDir, new File(userFileName), user.serialize());
    }
    
    /* @ ensures \result == products;
    @ signals_only IOException;
    */
    public /*@ pure @*/ static ArrayList<Product> getProducts() throws IOException {
        fillArrayList(DataTypes.PRODUCT);
        return products;
    }
    
    /* @ ensures \result == users;
    @ signals_only IOException;
    */
    public /*@ pure @*/ static ArrayList<User> getUsers() throws IOException {
        fillArrayList(DataTypes.USER);
        return users;
    }
    
    /* @ requires dataTypes != null && dataTypes == DataTypes.USER;
    @ assignable users;
    @ ensures users == usersList ;
    @ ensures products == \old(products);
    @ also
    @ requires dataTypes != null && dataTypes == DataTypes.PRODUCT;
    @ assignable products;
    @ ensures products == productsList;
    @ ensures users == \old(users);
    @ signals_only IOException;
    */
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
    
    /*
    @ requires dir != null & file != null && object != null;
    @ signals_only IOException;
    */
    private /*@ pure @*/ static void writeFile(String dir, File file, JsonObject object) throws IOException {
        createDir(dir);
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(object.toString());
        }
    }
    
    
    /*@ requires directoryPath != null;
    @ signals_only IOException;
    @ ensures \result == filesList;
   */
    private /*@ pure @*/ static ArrayList<Path> getFilesList(String directoryPath) throws IOException {
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
    
    /* @ requires dir != null; 
     @ ensures \result == true || \result == false; 
     @ */
    public /*@ pure @*/ static boolean dirExists(String dir) {
        return (Files.isDirectory(Paths.get(dir)) || Files.exists(Paths.get(dir)));
    }
    
    /*@ requires dir != null;
    @ signals_only IOException;
    @ ensures (dirExists(dir) == false) ==> Files.createDirectory(path); 
    @*/
    public /*@ pure @*/ static void createDir(String dir) throws IOException {
        Path path = Paths.get(dir);
        if (!dirExists(dir)) {
            Files.createDirectory(path);
        }
    }
}
