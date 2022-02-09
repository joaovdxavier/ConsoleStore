package dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.UserRoles;

public class User implements DataObject {
    //Pontos: 1
    //Renan
    private static String userInformation = "User name: %s; lastname: %s; role: %s; email: %s; password: ***; id: %s";

    public User() {
        User.countID++;
        this.id = User.countID;
    }

    public User(String name, String lastName, UserRoles role, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        User.countID++;
        this.id = User.countID;
    }

    private static int countID = 0;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Last Name")
    private String lastName;

    @JsonProperty("Role")
    private UserRoles role;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Id")
    private int id;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRoles getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public static int getCountID() {
        return countID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(UserRoles role) {
        this.role = role;
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

    @Override
    public String toString() {
        return String.format(userInformation, name, lastName, role, email, password, id);
    }
}
