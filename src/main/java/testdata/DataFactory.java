package testdata;

import datamanagment.DataStoreManager;
import datamanagment.DataIOUtil;
import dataobjects.Product;
import dataobjects.User;
import enums.DataTypes;
import enums.UserRoles;
import java.io.IOException;

public class DataFactory {
    //Pontos: 3
    //Renan
    private static String name = "Product #%s";
    private static String description = "Product #%s description";

    private static String userName = "UserName%s";
    private static String userLastName = "UserLastName%s";
    private static UserRoles userRoles = UserRoles.USER;
    private static String userEmail = "user%s@mail.com";

    private static String adminName = "AdminName%s";
    private static String adminLastName = "AdminLastName%s";
    private static UserRoles adminRole = UserRoles.ADMIN;
    private static String adminEmail = "admin%s@mail.com";

    private static String password = "User%spassword";

    public void generateData(DataTypes dataTypes, int count) throws IOException {
        for (int i = 0; i < count; i++) {
           generateDataObject(dataTypes);
        }
    }

    public void generateDataObject(DataTypes dataTypes) throws IOException {
        switch (dataTypes) {
            case PRODUCT:
                Product newProduct = new Product();
                int productId = newProduct.getId();
                newProduct.setName(String.format(name, productId));
                newProduct.setDescription(String.format(description, productId));
                newProduct.setPrice(productId * 10);
                DataIOUtil.writeProduct(newProduct);
                DataStoreManager.getInstance().addProduct(newProduct);
                break;
            case USER:
                User newUser = new User();
                int userId = newUser.getId();
                if (userId % 2 == 0) {
                    newUser.setName(String.format(userName, userId));
                    newUser.setLastName(String.format(userLastName, userId));
                    newUser.setRole(userRoles);
                    newUser.setEmail(String.format(userEmail, userId));
                } else {
                    newUser.setName(String.format(adminName, userId));
                    newUser.setLastName(String.format(adminLastName, userId));
                    newUser.setRole(adminRole);
                    newUser.setEmail(String.format(adminEmail, userId));
                }
                newUser.setPassword(String.format(password, userId));
                DataIOUtil.writeUser(newUser);
                DataStoreManager.getInstance().addUser(newUser);
                break;
        }
    }
}
