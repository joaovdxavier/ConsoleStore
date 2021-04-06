package menu;

import datamanagment.DataBasketManager;
import datamanagment.DataStoreManager;
import datamanagment.DataIOUtil;
import dataobjects.User;
import enums.MenuNames;
import enums.UserRoles;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import java.io.IOException;

public class RegistrationMenu implements MenuItem {
    @Override
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {
        int menuParagraph;
        System.out.println("Do you wanna create a new account?");
        System.out.println("1. Create an account");
        System.out.println("2. Back to the Main menu");

        do {
            menuParagraph = InputUtil.getIntFromConsole();
        } while (menuParagraph != 1 && menuParagraph != 2);

        if (menuParagraph == 1) {
            System.out.println("Creating a new user.");
            System.out.println("Enter your email: ");
            String email = InputUtil.getStringFromConsole();
            System.out.println("Enter your name: ");
            String name = InputUtil.getStringFromConsole();
            System.out.println("Enter your last name: ");
            String lastName = InputUtil.getStringFromConsole();
            int role;
            System.out.println("Choose your role: \n" +
                    "1. User \n" +
                    "2. Admin \n");
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

    @Override
    public MenuNames getMenuName() {
        return MenuNames.REGISTRATION;
    }
}
