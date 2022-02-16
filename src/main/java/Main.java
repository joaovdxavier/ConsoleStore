import dataobjects.User;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import menu.MainMenu;
import menu.MenuManager;

import java.io.IOException;

public class Main {
    /*@ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public static /*@ pure @*/ void main(String[] args) throws NotLoggedInException, NonExistentProductId, IOException {
        //User usuario = new User();
        //System.out.println("Imprimiu");
        
    	MainMenu mainMenu = new MainMenu();
        MenuManager.getInstance().displaySelectedMenu(mainMenu);
    }
}
