package menu;

import datamanagment.DataUtil;
import dataobjects.DataObject;
import enums.DataTypes;
import inpututils.InputUtil;
import dataobjects.Product;
import testdata.DataFactory;
import dataobjects.User;
import java.io.IOException;
import java.util.ArrayList;

public class DataGenerationMenu {
    public static void DisplayFirstLaunchMenu(ArrayList<User> users, ArrayList<Product> products) throws IOException {
        if (users == null || users.isEmpty()) {
            generateData(DataTypes.USER);
            users = DataUtil.getUsers();
        }
        if (products == null || products.isEmpty()) {
            generateData(DataTypes.PRODUCT);
            products = DataUtil.getProducts();
        }
    }

    public static ArrayList<DataObject> generateData(DataTypes dataType) throws IOException {
        ArrayList<DataObject> dataObjectArrayList = null;
        System.out.println(
                "Seems like your " + dataType + " list is empty, do you wanna generate some data?\n" +
                        "1. Yeas\n" +
                        "2. No\n");
        int paragraph = InputUtil.getInt();
        if (paragraph == 1) {
            System.out.println("How many of " + dataType + " do you wanna generate?");
            int count = InputUtil.getInt();
            DataFactory dataFactory = new DataFactory();
            dataObjectArrayList = dataFactory.generateData(dataType, count);
        }
        return dataObjectArrayList;
    }
}
