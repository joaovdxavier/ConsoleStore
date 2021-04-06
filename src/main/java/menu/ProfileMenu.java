package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NotLoggedInException;
import java.io.IOException;

public class ProfileMenu implements MenuItem {
    @Override
    public void displayMenu() throws NotLoggedInException, IOException {
        System.out.println(DataStoreManager.getInstance().getCurrentUser());
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.PROFILE;
    }
}
