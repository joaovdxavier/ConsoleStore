package main.java.menu;

import main.java.enums.MenuNames;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import java.io.IOException;

public interface MenuItem {
	/*@
	 @ signals_only IOException, NotLoggedInException, NonExistentProductId;
	 @*/
    void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId;
    MenuNames getMenuName();
}
