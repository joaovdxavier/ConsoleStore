package testdata;

import userpattern.AbstractUser;
import userpattern.Admin;
import userpattern.User;

import java.util.ArrayList;

public class AddUsers {
    public static void addFewUsers (ArrayList<AbstractUser> users){
        User user1 = new User("UserName1", "UserLastname1", "User1@mail.com", "User1password");
        Admin admin1 = new Admin("AdminName1", "AdminLastname1", "Admin1@mail.com", "Admin1password");
        User user2 = new User("UserName2", "UserLastname2", "User2@mail.com", "User2password");
        Admin admin2 = new Admin("AdminName2", "AdminLastname2", "Admin2@mail.com", "Admin2password");
        User user3 = new User("UserName3", "UserLastname3", "User3@mail.com", "User3password");
        Admin admin3 = new Admin("AdminName3", "AdminLastname3", "Admin3@mail.com", "Admin3password");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(admin1);
        users.add(admin2);
        users.add(admin3);
    }
}
