package menu;

import datamanagment.CurrentDataSingleton;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import dataobjects.User;
import java.io.IOException;
import java.util.ArrayList;

public class LoginMenu implements MenuItem {
    @Override
    public void displayMenu()
            throws NotLoggedInException, IOException, NonExistentProductId {
        String email;
        boolean userWasFound = false;
        ArrayList<User> users = CurrentDataSingleton.getInstance().getUsers();
        System.out.println("Login menu");
        System.out.println("Enter email:");
        email = InputUtil.getString();

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                passwordCheck(user);
                userWasFound = true;
            }
        }

        if (!userWasFound) {
            MenuManager.getInstance().displaySelectedMenu(8);
        }
    }

    private void passwordCheck(User user) throws IOException {
        System.out.println("A user with the given email was found: "
                + user.getName() + " " + user.getLastName());
        System.out.println("Enter password: ");
        String password = InputUtil.getString();
        if (user.getPassword().equals(password)) {
            System.out.println("You entered the correct password. \n" +
                    "Hello " + user.getName() + " " + user.getLastName());
            CurrentDataSingleton.getInstance().setCurrentUser(user);
        } else {
            System.out.println("Wrong password.");
            return;
        }
    }

    @Override
    public int getMenuID() {
        return 4;
    }
}
