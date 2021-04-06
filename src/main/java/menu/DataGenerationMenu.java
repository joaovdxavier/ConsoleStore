package menu;

import enums.DataTypes;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import testdata.DataFactory;
import java.io.IOException;

public class DataGenerationMenu implements MenuItem {
    private static DataTypes dataTypeToGenerate = null;

    @Override
    public void displayMenu()  throws IOException {
        if (dataTypeToGenerate != null) {
            System.out.println(
                    "Seems like your " + dataTypeToGenerate
                            + " list is empty, do you wanna generate some data?\n" +
                            "1. Yeas\n" +
                            "2. No\n");
            int paragraph = InputUtil.getIntFromConsole();
            if (paragraph == 1) {
                System.out.println("How many of " + dataTypeToGenerate + " do you wanna generate?");
                int count = InputUtil.getIntFromConsole();
                DataFactory dataFactory = new DataFactory();
                dataFactory.generateData(dataTypeToGenerate, count);
            }
        } else {
            System.out.println("The type of data to generate was not selected");
        }
    }

    public static void chooseType(DataTypes dataType) throws NotLoggedInException, NonExistentProductId, IOException {
        dataTypeToGenerate = dataType;
        MenuManager.getInstance().displaySelectedMenu(MenuNames.DATA_GENERATION);
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.DATA_GENERATION;
    }
}
