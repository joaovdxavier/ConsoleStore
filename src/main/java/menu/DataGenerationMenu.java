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

    private static String chooseString = "Seems like your %s list is empty, do you wanna generate some data?\n1. Yeas\n2. No";
    private static String countChoose = "How many of %s do you wanna generate? (from 1 to 999)";

    @Override
    public void displayMenu()  throws IOException {
        if (dataTypeToGenerate != null) {
            System.out.println(String.format(chooseString, dataTypeToGenerate));
            int paragraph = InputUtil.getIntFromConsole();
            if (paragraph == 1) {
                System.out.println(String.format(countChoose, dataTypeToGenerate));
                int count = 0;
                do {
                    count = InputUtil.getIntFromConsole();
                } while (count < 1 || count > 999);
                DataFactory dataFactory = new DataFactory();
                dataFactory.generateData(dataTypeToGenerate, count);
            }
        } else {
            System.out.println("The type of data to generate was not selected");
        }
    }

    public static void chooseType(DataTypes dataType) throws NotLoggedInException, NonExistentProductId, IOException {
        dataTypeToGenerate = dataType;
        DataGenerationMenu dataGenerationMenu = new DataGenerationMenu();
        MenuManager.getInstance().displaySelectedMenu(dataGenerationMenu);
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.DATA_GENERATION;
    }
}
