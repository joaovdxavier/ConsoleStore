import datamanagment.CurrentDataSingleton;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import menu.MenuManager;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, NotLoggedInException, NonExistentProductId {
        CurrentDataSingleton dataSingleton = CurrentDataSingleton.getInstance();
        MenuManager menuManager = MenuManager.getInstance();
        menuManager.displaySelectedMenu(0);
        System.out.println("Buy! See you later, Space Cowboy!");
    }
}
