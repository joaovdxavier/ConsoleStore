package menu;

import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import dataobjects.User;
import java.io.IOException;
import java.util.ArrayList;

public class LoginMenu implements MenuItem {
    //Pontos: 1
    //Lucas
    @Override
    /*@ also
    @ assignable \nothing;
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
