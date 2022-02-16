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
    private /*@ spec_public non_null @*/ static String greetingsMessage = "You entered the correct email and password. \nHello %s %s !";
    private /*@ spec_public non_null @*/ static String errorMassage = "Incorrect email or password.";

    private /*@ spec_public nullable @*/ static String email, password;
    private /*@ spec_public nullable @*/ static ArrayList<User> users;

    @Override
    /*@ also
    @ signals_only IOException;
    @*/
    public /*@ pure @*/ void displayMenu() throws IOException {
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

    /*@ assignable \everything;
    @ ensures AuthenticationMenu.users == users; 
    @ ensures AuthenticationMenu.email == email; 
    @ ensures AuthenticationMenu.password == password;
    @ signals_only NotLoggedInException;
    @*/
    public static void displayMenuWithUserData(String email, String password, ArrayList<User> users)
            throws NotLoggedInException, NonExistentProductId, IOException {
        AuthenticationMenu.users = users;
        AuthenticationMenu.email = email;
        AuthenticationMenu.password = password;
        AuthenticationMenu authenticationMenu = new AuthenticationMenu();
        MenuManager.getInstance().displaySelectedMenu(authenticationMenu);
    }

    /*@ assignable \nothing;
    @*/
    private /*@ pure @*/ static User findUser() {
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

    /*@ requires user.getPassword() != null;
    @*/
    private /*@ pure @*/ static boolean checkPassword(/*@ non_null @*/ User user) {
        return user.getPassword().equals(password);
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.AUTHENTICATION;
    }
}
