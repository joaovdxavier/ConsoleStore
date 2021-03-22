package testdata;

import enums.UserRole;
import userpattern.User;

import java.util.ArrayList;

public class AddUsers {
    public static void addFewUsers (ArrayList<User> users){
        User user1 = new User("UserName1", "UserLastname1", UserRole.USER, "User1@mail.com", "User1password");
        User user2 = new User("UserName2", "UserLastname2", UserRole.USER, "User2@mail.com", "User2password");
        User user3 = new User("UserName3", "UserLastname3", UserRole.USER, "User3@mail.com", "User3password");
        User admin1 = new User("AdminName1", "Admin1Lastname1", UserRole.USER, "Admin1@mail.com", "Admin1password");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(admin1);

    }
}
