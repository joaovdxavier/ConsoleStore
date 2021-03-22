package userpattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.UserRole;

public class User {

    public User(){

    }

    public User(String name, String lastName, UserRole role, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        User.countID++;
        this.userID = User.countID;
    }

    private static int countID = 0;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Last Name")
    private String lastName;

    @JsonProperty("Role")
    private UserRole role;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Id")
    private int userID;

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

    public static void setCountID(int countID) {
        User.countID = countID;
    }
}
