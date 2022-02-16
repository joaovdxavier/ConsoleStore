package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;

public class MenuManager {
    private static /*@ spec_public @*/ MenuManager instance;

    private /*@ pure @*/ MenuManager() {}

    
    /*@ assignable instance;
    @ ensures \result == instance; 
    @*/
    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    /*@ signals_only NotLoggedInException, NonExistentProductId, IOException;
    @*/
    public /*@ pure @*/ void displaySelectedMenu(/*@ non_null @*/MenuItem menuItem) throws NotLoggedInException, NonExistentProductId, IOException {
        menuItem.displayMenu();
    }
}
