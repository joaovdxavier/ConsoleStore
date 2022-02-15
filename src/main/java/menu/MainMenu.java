package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import java.io.IOException;

public class MainMenu implements MenuItem {
    //Pontos: 3
    //Lucas
    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public /*@ pure @*/ void displayMenu() {
        int menuNumber;
        boolean exit = false;
        do {
            System.out.println(
                    "Choose one of the menu paragraphs:\n1. Store\n2. Basket\n3. Profile\n4. Login\n5. Registration\n6. Exit\n");

            menuNumber = InputUtil.getIntFromConsole();

            try {
                if (menuNumber >= 1 && menuNumber <= 3) {
                    LoginCheck.checkLogin(DataStoreManager.getInstance().getCurrentUser());
                    switch (menuNumber){
                        case 1:
                            StoreMenu storeMenu = new StoreMenu();
                            MenuManager.getInstance().displaySelectedMenu(storeMenu);
                            break;
                        case 2:
                            BasketMenu basketMenu = new BasketMenu();
                            MenuManager.getInstance().displaySelectedMenu(basketMenu);
                            break;
                        case 3:
                            ProfileMenu profileMenu = new ProfileMenu();
                            MenuManager.getInstance().displaySelectedMenu(profileMenu);
                            break;
                    }
                } else {
                    switch (menuNumber) {
                        case 4:
                            LoginMenu loginMenu = new LoginMenu();
                            MenuManager.getInstance().displaySelectedMenu(loginMenu);
                            break;
                        case 5:
                            RegistrationMenu registrationMenu = new RegistrationMenu();
                            MenuManager.getInstance().displaySelectedMenu(registrationMenu);
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
    /*@ also
    @ assignable \nothing;
    @ ensures \result == MenuNames.MAIN;
    @*/
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.MAIN;
    }
}
