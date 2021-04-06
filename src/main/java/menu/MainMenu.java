package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import java.io.IOException;

public class MainMenu implements MenuItem {
    @Override
    public void displayMenu() {
        int menuNumber;
        boolean exit = false;
        do {
            System.out.println(
                    "Choose one of the menu paragraphs:\n" +
                            "1. Store\n" +
                            "2. Basket\n" +
                            "3. Profile\n" +
                            "4. Login\n" +
                            "5. Registration\n" +
                            "6. Exit\n");

            menuNumber = InputUtil.getIntFromConsole();

            try {
                if (menuNumber >= 1 && menuNumber <= 3) {
                    LoginCheck.checkLogin(DataStoreManager.getInstance().getCurrentUser());
                    switch (menuNumber){
                        case 1:
                            MenuManager.getInstance().displaySelectedMenu(MenuNames.STORE);
                            break;
                        case 2:
                            MenuManager.getInstance().displaySelectedMenu(MenuNames.BASKET);
                            break;
                        case 3:
                            MenuManager.getInstance().displaySelectedMenu(MenuNames.PROFILE);
                            break;
                    }
                } else {
                    switch (menuNumber) {
                        case 4:
                            MenuManager.getInstance().displaySelectedMenu(MenuNames.LOGIN);
                            break;
                        case 5:
                            MenuManager.getInstance().displaySelectedMenu(MenuNames.REGISTRATION);
                            break;
                        case 6:
                            System.out.println("Buy! See you later, Space Cowboy!");
                            exit = true;
                            break;
                    }
                }
            } catch (NotLoggedInException | IOException | NonExistentProductId exception) {
                exception.printStackTrace();
            }
        } while (!exit);
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.MAIN;
    }
}
