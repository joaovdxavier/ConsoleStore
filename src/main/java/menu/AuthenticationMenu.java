package menu;

import datamanagment.DataStoreManager;
import dataobjects.User;
import enums.MenuNames;
import exceptions.AlreadyLoggedIn;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationMenu implements MenuItem {
    //Pontos: 3
    //Lucas
    private static String greetingsMessage = "You entered the correct email and password. \nHello %s %s !";
    private static String errorMassage = "Incorrect email or password.";

    private static String email, password;
    private static ArrayList<User> users;

    @Override
    public void displayMenu() throws IOException {
        try {
            User currentUser = DataStoreManager.getInstance().getCurrentUser();
            if (currentUser != null && currentUser.getEmail().equalsIgnoreCase(email)) {
                throw new AlreadyLoggedIn();
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }

        User userToLogin = findUser();
        if (userToLogin != null) {
            System.out.println(String.format(greetingsMessage, userToLogin.getName(),userToLogin.getLastName()));
            DataStoreManager.getInstance().setCurrentUser(userToLogin);
        } else {
            System.out.println(errorMassage);
        }
    }

    public static void displayMenuWithUserData(String email, String password, ArrayList<User> users)
            throws NotLoggedInException, NonExistentProductId, IOException {
        AuthenticationMenu.users = users;
        AuthenticationMenu.email = email;
        AuthenticationMenu.password = password;
        AuthenticationMenu authenticationMenu = new AuthenticationMenu();
        MenuManager.getInstance().displaySelectedMenu(authenticationMenu);
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
        return user.getPassword().equals(password);
    }

    @Override
    public MenuNames getMenuName() {
        return MenuNames.AUTHENTICATION;
    }
}
