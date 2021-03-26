package menu;

import exceptions.NotLoggedInException;


public class ProfileMenu {
    public static void displayProfileMenu(MenuData menuData) throws NotLoggedInException {
        System.out.println("User name: " + menuData.getCurrentUser().getName());
        System.out.println("User last name: " + menuData.getCurrentUser().getLastName());
        System.out.println("User email: " + menuData.getCurrentUser().getEmail());
        System.out.println("User password: " + menuData.getCurrentUser().getPassword());
        System.out.println();
    }
}
