import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import menu.MenuManager;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NotLoggedInException, NonExistentProductId {
        MenuManager.getInstance().displaySelectedMenu(MenuNames.MAIN);
    }
}
