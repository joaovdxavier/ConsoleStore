package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NotLoggedInException;
import java.io.IOException;

public class ProfileMenu implements MenuItem {
    //Pontos: 1
    //João
    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public void displayMenu() throws IOException {
        System.out.println(DataStoreManager.getInstance().getCurrentUser());
    }

    @Override
    /*@ also
    @ ensures \result == MenuNames.PROFILE;
    @*/
    public MenuNames getMenuName() {
        return MenuNames.PROFILE;
    }
}
