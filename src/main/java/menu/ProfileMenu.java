package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NotLoggedInException;
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
