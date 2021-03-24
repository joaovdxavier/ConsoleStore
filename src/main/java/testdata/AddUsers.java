package testdata;

import datamanagment.DataWriteUtil;
import enums.UserRole;
import userpattern.User;

import java.io.IOException;
import java.util.ArrayList;

public class AddUsers {
    private static String userName = "UserName%s";
    private static String userLastName = "UserLastName%s";
    private static UserRole userRole = UserRole.USER;
    private static String userEmail = "user%s@mail.com";

    private static String adminName = "AdminName%s";
    private static String adminLastName = "AdminLastName%s";
    private static UserRole adminRole = UserRole.ADMIN;
    private static String adminEmail = "admin%s@mail.com";

    private static String password = "User%spassword";

    public static void addFewUsers (ArrayList<User> users, int count) throws IOException {
        int i = User.getCountID();
        int j = i + count;
        for (i = User.getCountID(); i < j; i++) {
            User newUser = new User();
            int id = newUser.getUserID();
            if (i % 2 == 0) {
                newUser.setName(String.format(userName, id));
                newUser.setLastName(String.format(userLastName, id));
                newUser.setRole(userRole);
                newUser.setEmail(String.format(userEmail, id));
            } else {
                newUser.setName(String.format(adminName, id));
                newUser.setLastName(String.format(adminLastName, id));
                newUser.setRole(adminRole);
                newUser.setEmail(String.format(adminEmail, id));
            }

            newUser.setPassword(String.format(password, id));

            users.add(newUser);
            DataWriteUtil.writeUser(newUser);
        }
    }
}
