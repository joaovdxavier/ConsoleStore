package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private Map<Integer, MenuItem> menuItems;
    private static MenuManager instance;

    private MenuManager() {
        fillMenuItems();
    }

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    public void displaySelectedMenu(int menuId) throws IOException, NotLoggedInException, NonExistentProductId {
        MenuItem currentMenuItem = menuItems.get(menuId);
        currentMenuItem.displayMenu();
    }

    private void fillMenuItems(){
        MainMenu mainMenu = new MainMenu();
        BasketMenu basketMenu = new BasketMenu();
        DataGenerationMenu dataGenerationMenu = new DataGenerationMenu();
        LoginMenu loginMenu = new LoginMenu();
        ProductMenu productMenu = new ProductMenu();
        ProfileMenu profileMenu = new ProfileMenu();
        StoreMenu storeMenu = new StoreMenu();
        ProductAddingMenu productAddingMenu = new ProductAddingMenu();
        RegistrationMenu registrationMenu = new RegistrationMenu();

        menuItems = new HashMap<>();
        menuItems.put(mainMenu.getMenuID(), mainMenu);
        menuItems.put(basketMenu.getMenuID(), basketMenu);
        menuItems.put(dataGenerationMenu.getMenuID(), dataGenerationMenu);
        menuItems.put(loginMenu.getMenuID(), loginMenu);
        menuItems.put(productMenu.getMenuID(), productMenu);
        menuItems.put(profileMenu.getMenuID(), profileMenu);
        menuItems.put(storeMenu.getMenuID(), storeMenu);
        menuItems.put(storeMenu.getMenuID(), storeMenu);
        menuItems.put(productAddingMenu.getMenuID(), productAddingMenu);
        menuItems.put(registrationMenu.getMenuID(), registrationMenu);
    }
}
