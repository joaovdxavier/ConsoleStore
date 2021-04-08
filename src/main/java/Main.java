import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import menu.MainMenu;
import menu.MenuManager;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NotLoggedInException, NonExistentProductId, IOException {
        MainMenu mainMenu = new MainMenu();
        MenuManager.getInstance().displaySelectedMenu(mainMenu);
    }
}
