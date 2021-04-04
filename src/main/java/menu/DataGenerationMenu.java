package menu;

import datamanagment.CurrentDataSingleton;
import datamanagment.DataIOUtil;
import dataobjects.DataObject;
import enums.DataTypes;
import inpututils.InputUtil;
import dataobjects.Product;
import testdata.DataFactory;
import dataobjects.User;
import java.io.IOException;
import java.util.ArrayList;

public class DataGenerationMenu implements MenuItem {
    @Override
    public void displayMenu()  throws IOException {
        DataTypes typeToGenerate = CurrentDataSingleton.getInstance().getDataTypeToGenerate();
        System.out.println(
                "Seems like your " + typeToGenerate
                        + " list is empty, do you wanna generate some data?\n" +
                        "1. Yeas\n" +
                        "2. No\n");
        int paragraph = InputUtil.getInt();
        if (paragraph == 1) {
            System.out.println("How many of " + typeToGenerate + " do you wanna generate?");
            int count = InputUtil.getInt();
            DataFactory dataFactory = new DataFactory();
            dataFactory.generateData(typeToGenerate, count);
        }
    }

    @Override
    public int getMenuID() {
        return 6;
    }
}
