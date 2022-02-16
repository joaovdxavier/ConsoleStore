package main.java.menu;

import main.java.datamanagment.DataStoreManager;
import main.java.enums.MenuNames;
import main.java.exceptions.NotLoggedInException;
import java.io.IOException;

public class ProfileMenu implements MenuItem {
    @Override
    /*@ also
    @ assignable \nothing;
    @ signals_only IOException;
    @*/
    public void displayMenu() throws IOException {
        System.out.println(DataStoreManager.getInstance().getCurrentUser());
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public MenuNames getMenuName() {
        return MenuNames.PROFILE;
    }
}
