package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;

import java.io.IOException;

public class StartMenu {

    public static void displayStartMenu(MenuData menuData) {
        int menuNumber;
        do {
            System.out.println(
                    "Choose one of the menu paragraphs:\n" +
                            "1. Store\n" +
                            "2. Basket\n" +
                            "3. Profile\n" +
                            "4. Login / registration\n" +
                            "5. Exit\n");

            menuNumber = InputUtil.getInt();

            try {
                if (menuNumber >= 1 && menuNumber <= 3) {
                    Check.checkLogin(menuData.getCurrentUser());
                    switch (menuNumber) {
                        case 1:
                            StoreMenu.displayStoreMenu(menuData);
                            break;
                        case 2:
                            BasketMenu.displayBasketMenu(menuData);
                            break;
                        case 3:
                            ProfileMenu.displayProfileMenu(menuData);
                            break;
                    }
                } else {
                    switch (menuNumber) {
                        case 4:
                            LoginMenu.displayLoginMenu(menuData);
                            break;
                        case 5:
                            return;
                    }
                }
            } catch (NotLoggedInException | IOException | NonExistentProductId exception) {
                exception.printStackTrace();
            }
        } while (true);
    }
}
