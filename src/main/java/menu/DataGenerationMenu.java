package main.java.menu;

import main.java.enums.DataTypes;
import main.java.enums.MenuNames;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import main.java.inpututils.InputUtil;
import main.java.testdata.DataFactory;
import java.io.IOException;

public class DataGenerationMenu implements MenuItem {
    private static /*@ spec_public nullable @*/ DataTypes dataTypeToGenerate = null;

    private static /*@ spec_public non_null @*/ String chooseString = "Seems like your %s list is empty, do you wanna generate some data?\n1. Yeas\n2. No";
    private static /*@ spec_public non_null @*/ String countChoose = "How many of %s do you wanna generate? (from 1 to 999)";

    @Override
    /*@ also 
    @ signals_only IOException;
    @*/
    public /*@ pure @*/ void displayMenu()  throws IOException {
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

       
    /*@ assignable dataTypeToGenerate; 
    @ ensures dataTypeToGenerate == dataType;
    @ signals_only NotLoggedInException, NonExistentProductId, IOException;
    @*/
    public static void chooseType( /*@ non_null @*/ DataTypes dataType) throws NotLoggedInException, NonExistentProductId, IOException {
        dataTypeToGenerate = dataType;
        DataGenerationMenu dataGenerationMenu = new DataGenerationMenu();
        MenuManager.getInstance().displaySelectedMenu(dataGenerationMenu);
    }

    @Override
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.DATA_GENERATION;
    }
}
