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
    private /*@ spec_public non_null @*/ static String greetingsMessage = "You entered the correct email and password. \nHello %s %s !";
    private /*@ spec_public non_null @*/ static String errorMassage = "Incorrect email or password.";

    private /*@ spec_public nullable @*/ static String email, password;
    private /*@ spec_public nullable @*/ static ArrayList<User> users;

    @Override
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

    /*@ assignable AuthenticationMenu.users, AuthenticationMenu.email, AuthenticationMenu.password;
    @ ensures AuthenticationMenu.users == users; 
    @ ensures AuthenticationMenu.email == email; 
    @ ensures AuthenticationMenu.password == password;
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
    @ ensures
    @ (\forall int i; 0 <= i && i < users.size();
    @         users[i].getEmail().equalsIgnoreCase(email) && checkPassword(users[i]) == users[i]) ==> \result;
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
    @ ensures (user.getPassword() == password) ==> \result;
    @*/
    private /*@ pure @*/ static boolean checkPassword(/*@ non_null @*/ User user) {
        return user.getPassword().equals(password);
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @ ensures \result == MenuNames.AUTHENTICATION;
    @*/
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.AUTHENTICATION;
    }
}
