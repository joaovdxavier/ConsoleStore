package main.java.menu;

import main.java.datamanagment.DataStoreManager;
import main.java.enums.MenuNames;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import main.java.inpututils.InputUtil;
import main.java.dataobjects.User;
import java.io.IOException;
import java.util.ArrayList;

public class LoginMenu implements MenuItem {
    @Override
    /*@ also
    @ assignable \nothing;
    @ signals_only NotLoggedInException, IOException, NonExistentProductId;
    @*/
    public /*@ pure @*/ void displayMenu() throws NotLoggedInException, IOException, NonExistentProductId {
        String email, password;
        ArrayList<User> users = DataStoreManager.getInstance().getUsers();
        System.out.println("Login menu");
        System.out.println("Enter email:");
        email = InputUtil.getStringFromConsole();
        System.out.println("Enter password:");
        password = InputUtil.getStringFromConsole();

        AuthenticationMenu.displayMenuWithUserData(email, password, users);
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.LOGIN;
    }
}
