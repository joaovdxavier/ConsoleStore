package menu;

import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;

public interface MenuItem {
    //Pontos: 1
    //João
    void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId;
    MenuNames getMenuName();
}
