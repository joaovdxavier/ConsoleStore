import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import menu.MainMenu;
import menu.MenuManager;
import java.io.IOException;

public class Main {
    /*@ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public static /*@ pure @*/ void main(String[] args) throws NotLoggedInException, NonExistentProductId, IOException {
        MainMenu mainMenu = new MainMenu();
        MenuManager.getInstance().displaySelectedMenu(mainMenu);
    }
}
