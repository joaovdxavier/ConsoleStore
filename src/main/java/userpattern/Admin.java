package userpattern;

import enums.UserRole;

public class Admin extends AbstractUser {

    public Admin() {
    }

    public Admin(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.role = UserRole.ADMIN;
        this.email = email;
        this.password = password;
        this.userID = AbstractUser.countID;
        AbstractUser.countID++;
    }


}
