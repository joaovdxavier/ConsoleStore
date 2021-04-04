package menu;

import datamanagment.CurrentDataSingleton;
import exceptions.NotLoggedInException;
import java.io.IOException;

public class ProfileMenu implements MenuItem {
    @Override
    public void displayMenu() throws NotLoggedInException, IOException {
        System.out.println("User name: " + CurrentDataSingleton.getInstance().getCurrentUser().getName());
        System.out.println("User last name: " + CurrentDataSingleton.getInstance().getCurrentUser().getLastName());
        System.out.println("User email: " + CurrentDataSingleton.getInstance().getCurrentUser().getEmail());
        System.out.println("User password: " + CurrentDataSingleton.getInstance().getCurrentUser().getPassword());
        System.out.println();
    }

    @Override
    public int getMenuID() {
        return 3;
    }
}
