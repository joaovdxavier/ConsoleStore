package testdata;

import datamanagment.DataUtil;
import dataobjects.DataObject;
import dataobjects.Product;
import dataobjects.User;
import enums.DataTypes;
import enums.UserRole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class DataFactory {
    private static String name = "Product #%s";
    private static String description = "Product #%s description";

    private static String userName = "UserName%s";
    private static String userLastName = "UserLastName%s";
    private static UserRole userRole = UserRole.USER;
    private static String userEmail = "user%s@mail.com";

    private static String adminName = "AdminName%s";
    private static String adminLastName = "AdminLastName%s";
    private static UserRole adminRole = UserRole.ADMIN;
    private static String adminEmail = "admin%s@mail.com";

    private static String password = "User%spassword";

    public ArrayList<DataObject> generateData(DataTypes dataType, int count) throws IOException {
        ArrayList<DataObject> dataObjects = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            dataObjects.add(getDataObject(dataType));
        }

        return dataObjects;
    }

    public DataObject getDataObject(DataTypes dataType) throws IOException {
        DataObject newDataObject ;

        switch (dataType) {
            case PRODUCT:
                Product newProduct = new Product();
                int productId = newProduct.getId();
                newProduct.setName(String.format(name, productId));
                newProduct.setDescription(String.format(description, productId));
                newProduct.setPrice(productId * 10);
                DataUtil.writeProduct(newProduct);
                newDataObject = newProduct;
                break;
            default:
                User newUser = new User();
                int userId = newUser.getId();
                if (userId % 2 == 0) {
                    newUser.setName(String.format(userName, userId));
                    newUser.setLastName(String.format(userLastName, userId));
                    newUser.setRole(userRole);
                    newUser.setEmail(String.format(userEmail, userId));
                } else {
                    newUser.setName(String.format(adminName, userId));
                    newUser.setLastName(String.format(adminLastName, userId));
                    newUser.setRole(adminRole);
                    newUser.setEmail(String.format(adminEmail, userId));
                }
                newUser.setPassword(String.format(password, userId));
                DataUtil.writeUser(newUser);
                newDataObject = newUser;
                break;
        }
        return newDataObject;
    }
}
