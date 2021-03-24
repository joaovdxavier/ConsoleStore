package menu;

import datamanagment.DataWriteUtil;
import enums.UserRole;
import exceptions.NotLoggedInException;
import inpututils.IntInputUtil;
import inpututils.StringInputUtil;
import productpattern.Product;
import userpattern.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginMenu {
    public static void displayLoginMenu(User currentUser, ArrayList<User> currentUsers, ArrayList<Product> currentProducts,
                                        HashMap<Integer, Integer> userBasket)
            throws NotLoggedInException, IOException {
        String email;
        boolean userWasFound = false;
        System.out.println("Login menu");
        System.out.println("Enter email:\n");
        email = StringInputUtil.get();
        for (User user : currentUsers) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("A user with the given email was found: "
                        + user.getName() + " " + user.getLastName());

                System.out.println("Enter password: ");
                String password = StringInputUtil.get();
                if (user.getPassword().equals(password)) {
                    System.out.println("You entered the correct password. \n" +
                            "Hello " + user.getName() + " " + user.getLastName());
                    StartMenu.setCurrentUser(user);
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
                menuParagraph = IntInputUtil.get();
            } while (menuParagraph != 1 && menuParagraph != 2);

            if (menuParagraph == 1) {
                System.out.println("Creating a new user.");
                System.out.println("Your email: " + email);
                System.out.println("Enter your name: ");
                String name = StringInputUtil.get();
                System.out.println("Enter your last name: ");
                String lastName = StringInputUtil.get();
                int role;
                do {
                    System.out.println("Choose your role: \n" +
                            "1. User \n" +
                            "2. Admin \n");
                    role = IntInputUtil.get();
                } while (role != 1 && role != 2);
                UserRole userRole = role == 1 ? UserRole.USER : UserRole.ADMIN;
                System.out.println("Enter your password: ");
                String password = StringInputUtil.get();

                User newUser = new User(name, lastName, userRole, email, password);
                currentUsers.add(newUser);
                DataWriteUtil.writeUser(newUser);
                userBasket.clear();
                System.out.println("Your account have been successfully created and stored in our base! ");
            }
        }
    }
}
