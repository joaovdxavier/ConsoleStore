package menu;

import datamanagment.DataStoreManager;
import dataobjects.User;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;

import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationMenu implements MenuItem {
    private static String email, password;
    private static ArrayList<User> users;

    @Override
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {
        User userToLogin = findUser();
        if (userToLogin != null) {
            System.out.println("You entered the correct email and password. \n" +
                    "Hello " + userToLogin.getName() + " " + userToLogin.getLastName());
            DataStoreManager.getInstance().setCurrentUser(userToLogin);
        } else {
            System.out.println("Incorrect email or password.");
        }
    }

    public static void displayMenuWithUserData(String email, String password, ArrayList<User> users)
            throws NotLoggedInException, NonExistentProductId, IOException {
        AuthenticationMenu.users = users;
        AuthenticationMenu.email = email;
        AuthenticationMenu.password = password;
        MenuManager.getInstance().displaySelectedMenu(MenuNames.AUTHENTICATION);
    }

    private static User findUser() {
        User foundUser = null;

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                if (checkPassword(user)) {
                    foundUser = user;
                }
                break;
            }
        }
        return foundUser;
    }

    private static boolean checkPassword(User user) {
        return user.getEmail().equals(password);
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.AUTHENTICATION;
    }
}
