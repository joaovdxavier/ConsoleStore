package userpattern;

import enums.UserRole;

public class User extends AbstractUser {
    public User() {
    }

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.role = UserRole.USER;
        this.email = email;
        this.password = password;
        this.userID = AbstractUser.countID;
        AbstractUser.countID++;
    }


}
