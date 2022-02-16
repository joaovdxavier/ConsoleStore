package menu;

import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;

public interface MenuItem {
    //Pontos: 1
    //João
	
	/*@
	 @ signals_only IOException, NotLoggedInException, NonExistentProductId;
	 @*/
    void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId;
    MenuNames /*@ pure @*/ getMenuName();
}
