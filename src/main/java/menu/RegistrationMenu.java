package menu;

import datamanagment.CurrentDataSingleton;
import datamanagment.DataIOUtil;
import dataobjects.User;
import enums.UserRole;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import java.io.IOException;

public class RegistrationMenu implements MenuItem {
    @Override
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {

        int menuParagraph;
        System.out.println("There are no users with entered email: ");
        System.out.println("Do you wanna create a new account?");
        System.out.println("1. Create an account");
        System.out.println("2. Back to the Main menu");

        do {
            menuParagraph = InputUtil.getInt();
        } while (menuParagraph != 1 && menuParagraph != 2);

        if (menuParagraph == 1) {
            System.out.println("Creating a new user.");
            System.out.println("Enter your email: ");
            String email = InputUtil.getString();
            System.out.println("Enter your name: ");
            String name = InputUtil.getString();
            System.out.println("Enter your last name: ");
            String lastName = InputUtil.getString();
            int role;
            System.out.println("Choose your role: \n" +
                    "1. User \n" +
                    "2. Admin \n");
            do {
                role = InputUtil.getInt();
            } while (role != 1 && role != 2);
            UserRole userRole = role == 1 ? UserRole.USER : UserRole.ADMIN;
            System.out.println("Enter your password: ");
            String password = InputUtil.getString();

            User newUser = new User(name, lastName, userRole, email, password);
            CurrentDataSingleton.getInstance().getUsers().add(newUser);
            DataIOUtil.writeUser(newUser);
            CurrentDataSingleton.getInstance().getUserBasket().clear();
            System.out.println("Your account have been successfully created and stored in our base! ");
        }
    }

    @Override
    public int getMenuID() {
        return 8;
    }
}
