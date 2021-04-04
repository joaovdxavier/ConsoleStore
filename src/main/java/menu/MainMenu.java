package menu;

import datamanagment.CurrentDataSingleton;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;

import java.io.IOException;

public class MainMenu implements MenuItem {
    @Override
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {
        int menuNumber;
        boolean exit = false;
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
                    LoginCheck.checkLogin(CurrentDataSingleton.getInstance().getCurrentUser());
                    MenuManager.getInstance().displaySelectedMenu(menuNumber);
                } else {
                    switch (menuNumber) {
                        case 4:
                            MenuManager.getInstance().displaySelectedMenu(menuNumber);
                            break;
                        case 5:
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
    public int getMenuID() {
        return 0;
    }
}
