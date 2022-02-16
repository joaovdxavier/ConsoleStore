package main.java.menu;

import main.java.exceptions.NotLoggedInException;
import main.java.dataobjects.User;

public class LoginCheck {
	/*@ public normal_behavior
	@ 		requires currentUser != null;
	@ 		assignable \nothing;
	@ also
	@ 	public exceptional_behavior
	@ 		requires currentUser == null;
	@ 		assignable \nothing;
	@ 		signals_only NotLoggedInException;
	@ 		signals (NotLoggedInException e)
	@ 		currentUser == null;
	@*/
    public static void checkLogin(User currentUser) throws NotLoggedInException {
        if (currentUser == null) {
            throw new NotLoggedInException();
        }
    }
}
