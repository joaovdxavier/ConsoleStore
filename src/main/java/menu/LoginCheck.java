package menu;

import exceptions.NotLoggedInException;
import dataobjects.User;

public class LoginCheck {
    public static void checkLogin(User currentUser) throws NotLoggedInException {
        if (currentUser == null) {
            throw new NotLoggedInException();
        }
    }
}
