package menu;

import exceptions.NotLoggedInException;
import dataobjects.User;

public class LoginCheck {
    //Pontos: 1
    //João
    /*
        currentUser
        current == null
        NotLoggedInException

        currentUser
        current != null
        @Nothing
     */
    public static void checkLogin(User currentUser) throws NotLoggedInException {
        if (currentUser == null) {
            throw new NotLoggedInException();
        }
    }
}
