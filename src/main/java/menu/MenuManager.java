package menu;

import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;

public class MenuManager {
    private static MenuManager instance;
    private MainMenu mainMenu;
    private BasketMenu basketMenu;
    private DataGenerationMenu dataGenerationMenu;
    private LoginMenu loginMenu;
    private ProductMenu productMenu;
    private ProfileMenu profileMenu;
    private StoreMenu storeMenu;
    private ProductAddingMenu productAddingMenu;
    private RegistrationMenu registrationMenu;
    private AuthenticationMenu authenticationMenu;

    private MenuManager() {}

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    public void displaySelectedMenu(MenuNames menuName) throws NotLoggedInException, NonExistentProductId, IOException {
        MenuItem menuItemToDisplay;
        switch (menuName) {
            case MAIN:
                if (mainMenu == null) mainMenu = new MainMenu();
                menuItemToDisplay = mainMenu;
                break;
            case STORE:
                if (storeMenu == null) storeMenu = new StoreMenu();
                menuItemToDisplay = storeMenu;
                break;
            case BASKET:
                if (basketMenu == null) basketMenu = new BasketMenu();
                menuItemToDisplay = basketMenu;
                break;
            case PROFILE:
                if (profileMenu == null) profileMenu = new ProfileMenu();
                menuItemToDisplay = profileMenu;
                break;
            case LOGIN:
                if (loginMenu == null) loginMenu = new LoginMenu();
                menuItemToDisplay = loginMenu;
                break;
            case PRODUCT_ADDING:
                if (productAddingMenu == null) productAddingMenu = new ProductAddingMenu();
                menuItemToDisplay = productAddingMenu;
                break;
            case REGISTRATION:
                if (registrationMenu == null) registrationMenu = new RegistrationMenu();
                menuItemToDisplay = registrationMenu;
                break;
            case PRODUCT:
                if (productMenu == null) productMenu = new ProductMenu();
                menuItemToDisplay = productMenu;
                break;
            case DATA_GENERATION:
                if (dataGenerationMenu == null) dataGenerationMenu = new DataGenerationMenu();
                menuItemToDisplay = dataGenerationMenu;
                break;
            case AUTHENTICATION:
                if (authenticationMenu == null) authenticationMenu = new AuthenticationMenu();
                menuItemToDisplay = authenticationMenu;
                break;
            default:
                menuItemToDisplay = null;
        }
        menuItemToDisplay.displayMenu();
    }
}
