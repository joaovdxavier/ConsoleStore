package main.java.testdata;

import main.java.datamanagment.DataStoreManager;
import main.java.datamanagment.DataIOUtil;
import main.java.dataobjects.Product;
import main.java.dataobjects.User;
import main.java.enums.DataTypes;
import main.java.enums.UserRoles;
import java.io.IOException;

public class DataFactory {
    private static /*@ spec_public non_null @*/ String name = "Product #%s";
    private static /*@ spec_public non_null @*/ String description = "Product #%s description";

    private static /*@ spec_public non_null @*/ String userName = "UserName%s";
    private static /*@ spec_public non_null @*/ String userLastName = "UserLastName%s";
    private static /*@ spec_public non_null @*/ UserRoles userRoles = UserRoles.USER;
    private static /*@ spec_public non_null @*/ String userEmail = "user%s@mail.com";

    private static /*@ spec_public non_null @*/ String adminName = "AdminName%s";
    private static /*@ spec_public non_null @*/ String adminLastName = "AdminLastName%s";
    private static /*@ spec_public non_null @*/ UserRoles adminRole = UserRoles.ADMIN;
    private static /*@ spec_public non_null @*/ String adminEmail = "admin%s@mail.com";

    private static /*@ spec_public non_null @*/ String password = "User%spassword";

    /*@ signals_only IOException;
    @*/
    public /*@ pure @*/ void generateData( /*@ non_null @*/ DataTypes dataTypes, int count) throws IOException {
        for (int i = 0; i < count; i++) {
           generateDataObject(dataTypes);
        }
    }

    /*@ signals_only IOException;
    @*/
    public /*@ pure @*/ void generateDataObject( /*@ non_null @*/ DataTypes dataTypes) throws IOException {
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
