package main.java;

import main.java.dataobjects.User;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import main.java.menu.MainMenu;
import main.java.menu.MenuManager;

import java.io.IOException;

public class Main {
    /*@ signals_only IOException, NotLoggedInException, NonExistentProductId;
    @*/
    public static /*@ pure @*/ void main(String[] args) throws NotLoggedInException, NonExistentProductId, IOException {
    	MainMenu mainMenu = new MainMenu();
        MenuManager.getInstance().displaySelectedMenu(mainMenu);
    }
}
