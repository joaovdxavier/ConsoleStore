package menu;

import datamanagment.DataUtil;
import enums.UserRole;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import userpattern.User;
import java.io.IOException;

public class LoginMenu {
    public static void displayLoginMenu(MenuData menuData)
            throws NotLoggedInException, IOException {
        String email;
        boolean userWasFound = false;
        System.out.println("Login menu");
        System.out.println("Enter email:");
        email = InputUtil.getString();
        for (User user : menuData.getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("A user with the given email was found: "
                        + user.getName() + " " + user.getLastName());
                System.out.println("Enter password: ");
                String password = InputUtil.getString();
                if (user.getPassword().equals(password)) {
                    System.out.println("You entered the correct password. \n" +
                            "Hello " + user.getName() + " " + user.getLastName());
                    menuData.setCurrentUser(user);
                    userWasFound = true;
                    break;
                } else {
                    System.out.println("Wrong password.");
                    return;
                }
            }
        }

        if (!userWasFound) {
            int menuParagraph;
            do {
                System.out.println("There are no users with this email: " + email);
                System.out.println("Do you wanna create an account with this email?");
                System.out.println("1. Create an account");
                System.out.println("2. Back to the main menu");
                menuParagraph = InputUtil.getInt();
            } while (menuParagraph != 1 && menuParagraph != 2);

            if (menuParagraph == 1) {
                System.out.println("Creating a new user.");
                System.out.println("Your email: " + email);
                System.out.println("Enter your name: ");
                String name = InputUtil.getString();
                System.out.println("Enter your last name: ");
                String lastName = InputUtil.getString();
                int role;
                do {
                    System.out.println("Choose your role: \n" +
                            "1. User \n" +
                            "2. Admin \n");
                    role = InputUtil.getInt();
                } while (role != 1 && role != 2);
                UserRole userRole = role == 1 ? UserRole.USER : UserRole.ADMIN;
                System.out.println("Enter your password: ");
                String password = InputUtil.getString();

                User newUser = new User(name, lastName, userRole, email, password);
                menuData.getUsers().add(newUser);
                DataUtil.writeUser(newUser);
                menuData.getUserBasket().clear();
                System.out.println("Your account have been successfully created and stored in our base! ");
            }
        }
    }
}
