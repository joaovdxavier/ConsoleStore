package menu;

import datamanagment.DataBasketManager;
import datamanagment.DataStoreManager;
import datamanagment.DataIOUtil;
import dataobjects.User;
import enums.MenuNames;
import enums.UserRoles;
import exceptions.EmailIsAlreadyUsed;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import java.io.IOException;
import java.util.ArrayList;

public class RegistrationMenu implements MenuItem {
    //Pontos: 3
    //João
    @Override
    /*@ also
    @ assignable \nothing;
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
    
    /*@ requires email != null 
    @ assignable \nothing;
    @ ensures \result == availability;
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
    @ ensures \result == MenuNames.REGISTRATION;
    @*/
    public MenuNames getMenuName() {
        return MenuNames.REGISTRATION;
    }
}
