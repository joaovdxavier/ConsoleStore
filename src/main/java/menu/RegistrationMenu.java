package main.java.menu;

import main.java.datamanagment.DataBasketManager;
import main.java.datamanagment.DataStoreManager;
import main.java.datamanagment.DataIOUtil;
import main.java.dataobjects.User;
import main.java.enums.MenuNames;
import main.java.enums.UserRoles;
import main.java.exceptions.EmailIsAlreadyUsed;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import main.java.inpututils.InputUtil;
import java.io.IOException;
import java.util.ArrayList;

public class RegistrationMenu implements MenuItem {
    @Override
    /*@ also
    @ assignable \nothing;
    @ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {
        int menuParagraph;
        System.out.println("Do you wanna create a new account?\n1. Create an account\n2. Back to the Main menu");

        do {
            menuParagraph = InputUtil.getIntFromConsole();
        } while (menuParagraph != 1 && menuParagraph != 2);

        if (menuParagraph == 1) {
            System.out.println("Creating a new user.\nEnter your email:");
            String email = InputUtil.getStringFromConsole();
             if (!CheckEmailAvailability(email)) {
                 return;
             }
            System.out.println("Enter your name: ");
            String name = InputUtil.getStringFromConsole();
            System.out.println("Enter your last name: ");
            String lastName = InputUtil.getStringFromConsole();
            int role;
            System.out.println("Choose your role: \n1. User \n2. Admin \n");
            do {
                role = InputUtil.getIntFromConsole();
            } while (role != 1 && role != 2);
            UserRoles userRoles = role == 1 ? UserRoles.USER : UserRoles.ADMIN;
            System.out.println("Enter your password: ");
            String password = InputUtil.getStringFromConsole();

            User newUser = new User(name, lastName, userRoles, email, password);
            DataStoreManager.getInstance().addUser(newUser);
            DataIOUtil.writeUser(newUser);
            DataBasketManager.getInstance().getUserBasket().clear();
            System.out.println("Your account have been successfully created and stored in our base! ");
        }
    }
    
    /*@ requires email != null; 
    @ assignable \nothing;
    @ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    private static boolean CheckEmailAvailability(String email) throws IOException, NotLoggedInException, NonExistentProductId {
        boolean availability = true;
        ArrayList<User> users = DataStoreManager.getInstance().getUsers();
        try {
            if (users != null) {
                for (User user: users) {
                    if (user.getEmail().equalsIgnoreCase(email)) {
                        availability = false;
                        throw new EmailIsAlreadyUsed();
                    }
                }
            }
        } catch (EmailIsAlreadyUsed emailIsAlreadyUsed) {
            emailIsAlreadyUsed.printStackTrace();
        }
        return availability;
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public MenuNames getMenuName() {
        return MenuNames.REGISTRATION;
    }
}
