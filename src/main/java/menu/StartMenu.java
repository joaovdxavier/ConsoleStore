package menu;

import datamanagment.DataWriteUtil;
import enums.UserRole;
import exceptions.NotLoggedInException;
import inpututils.IntInputUtil;
import inpututils.StringInputUtil;
import productpattern.Product;
import userpattern.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class StartMenu {
    private static User currentUser;
    private static ArrayList<User> currentUsers;
    private static ArrayList<Product> currentProducts;


    public static void displayStartMenu(User user, ArrayList<User> users, ArrayList<Product> products) throws NotLoggedInException {
        currentUser = user;
        currentUsers = users;
        currentProducts = products;
        int menuNumber;
        do {
            System.out.println("\nChoose one of the menu paragraphs:\n" +
                    "1. Store\n" +
                    "2. Basket\n" +
                    "3. Profile\n" +
                    "4. Login / registration\n" +
                    "5. Exit\n");
            menuNumber = IntInputUtil.get();

            try {
                switch (menuNumber) {
                    case 1:
                        displayStore();
                        break;
                    case 2:
                        displayBasket();
                        break;
                    case 3:
                        displayProfile();
                        break;
                    case 4:
                        displayLogin();
                        break;
                }
            } catch (NotLoggedInException | IOException exception) {
                exception.printStackTrace();
            }

        } while (menuNumber != 5);

    }

    public static void displayStore() throws NotLoggedInException {
        checkLogin();

        displayStartMenu(currentUser, currentUsers, currentProducts);

    }

    public static void displayBasket() throws NotLoggedInException {
        checkLogin();

        displayStartMenu(currentUser, currentUsers, currentProducts);
    }

    public static void displayProfile() throws NotLoggedInException {
        checkLogin();

        displayStartMenu(currentUser, currentUsers, currentProducts);
    }

    public static void displayLogin() throws NotLoggedInException, IOException {
        String email;
        boolean userWasFound = false;
        System.out.println("Login menu");
        System.out.println("Enter email:\n");
        email = StringInputUtil.get();
        for (User user : currentUsers) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("A user with the given email was found: "
                        + user.getName() + " " + user.getLastName());
                for (int i = 2; i >= 0; i--) {
                    System.out.println("Enter password: ");
                    String password = StringInputUtil.get();
                    if (user.getPassword().equals(password)) {
                        System.out.println("You entered the correct password. \n" +
                                "Hello " + user.getName() + " " + user.getLastName());
                        currentUser = user;
                        userWasFound = true;
                        break;
                    } else {
                        System.out.println("Wrong password. Attempts left: " + i);
                    }
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
                System.out.println("Your account have been successfully created and stored in our base! ");
            }
        }
        displayStartMenu(currentUser, currentUsers, currentProducts);
    }

    public static void checkLogin() throws NotLoggedInException {
        if (currentUser == null) {
            throw new NotLoggedInException();
        }
        displayStartMenu(currentUser, currentUsers, currentProducts);
    }
}
