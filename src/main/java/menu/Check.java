package menu;

import exceptions.NotLoggedInException;
import userpattern.User;

public class Check {
    public static void checkLogin(User currentUser) throws NotLoggedInException {

        if (currentUser == null) {
            throw new NotLoggedInException();
        }
    }
}
