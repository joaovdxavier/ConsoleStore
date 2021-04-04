package menu;

import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import java.io.IOException;

public interface MenuItem {
    void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId;
    int getMenuID();
}
