package userpattern;

import enums.UserRole;

public abstract class AbstractUser {
    protected static int countID = 0;
    protected String name;
    protected String lastName;
    protected UserRole role;
    protected String email;
    protected String password;
    protected int userID;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
