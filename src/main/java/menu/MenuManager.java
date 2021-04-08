package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;

public class MenuManager {
    private static MenuManager instance;

    private MenuManager() {}

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    public void displaySelectedMenu(MenuItem menuItem) throws NotLoggedInException, NonExistentProductId, IOException {
        menuItem.displayMenu();
    }
}
