package menu;

import exceptions.NotLoggedInException;
import dataobjects.User;

public class LoginCheck {
    //Pontos: 1
    //João
	/*@ public normal_behavior
	@ 		requires currentUser != null;
	@ 		assignable \nothing;
	@ 		ensures \nothing;
	@ also
	@ 	public exceptional_behavior
	@ 		requires currentUser == null;
	@ 		assignable \nothing;
	@ 		signals_only NotLoggedInException;
	@ 		signals (DeathException e)
	@ 		currentUser == null;
	@*/
    public static void checkLogin(User currentUser) throws NotLoggedInException {
        if (currentUser == null) {
            throw new NotLoggedInException();
        }
    }
}
