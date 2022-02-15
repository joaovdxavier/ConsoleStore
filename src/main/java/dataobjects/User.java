package dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.UserRoles;

public class User implements DataObject {
    //Pontos: 1
    //Renan
    private static /*@ spec_public non_null @*/ String userInformation = "User name: %s; lastname: %s; role: %s; email: %s; password: ***; id: %s";

    /*@ assignable User.countID, this.id;
    @ ensures this.id == User.countID; 
    @ ensures User.countID == \old(User.countID)+1; 
    @*/
    public User() {
        User.countID++;
        this.id = User.countID;
    }

    /*@ assignable this.countID, this.name, this.lastName, this.role, this.id, this.email, this.password;
    @ ensures this.id == this.countID; 
    @ ensures this.name == name; 
    @ ensures this.role == role; 
    @ ensures this.email == email;
    @ ensures this.password == password; 
    @ ensures this.lastName == lastName; 
    @ ensures this.countID == \old(this.countID)+1; 
    @*/
    public User( /*@ non_null @*/ String name, /*@ non_null @*/ String lastName, /*@ non_null @*/ UserRoles role, /*@ non_null @*/ String email, /*@ non_null @*/ String password) {
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        User.countID++;
        this.id = User.countID;
    }

    private static /*@ spec_public non_null @*/ int countID = 0;

    @JsonProperty("Name")
    private /*@ spec_public nullable @*/ String name;

    @JsonProperty("Last Name")
    private /*@ spec_public nullable @*/ String lastName;

    @JsonProperty("Role")
    private /*@ spec_public nullable @*/ UserRoles role;

    @JsonProperty("Email")
    private /*@ spec_public nullable @*/ String email;

    @JsonProperty("Password")
    private /*@ spec_public nullable @*/ String password;

    @JsonProperty("Id")
    private /*@ spec_public nullable @*/ int id;

    //@ public invariant 0 <= countID;
    
    /*@ also
    @ assignable \nothing;
    @ ensures \result == name; 
    @*/
    public /*@ pure @*/ String getName() {
        return name;
    }

    /*@ assignable \nothing;
    @ ensures \result == lastName; 
    @*/
    public /*@ pure @*/ String getLastName() {
        return lastName;
    }

    /*@ assignable \nothing;
    @ ensures \result == role; 
    @*/
    public /*@ pure @*/ UserRoles getRole() {
        return role;
    }

    /*@ assignable \nothing;
    @ ensures \result == email; 
    @*/
    public /*@ pure @*/ String getEmail() {
        return email;
    }

    /*@ assignable \nothing;
    @ ensures \result == password; 
    @*/
    public /*@ pure @*/ String getPassword() {
        return password;
    }

    /*@ also
    @ assignable \nothing;
    @ ensures \result == id; 
    @*/
    public /*@ pure @*/ int getId() {
        return id;
    }

    /*@ assignable \nothing;
    @ ensures \result == countID; 
    @*/
    public static /*@ pure @*/ int getCountID() {
        return countID;
    }

    /*@ assignable this.name; 
    @ ensures this.name == name;
    @*/
    public void setName( /*@ non_null @*/ String name) {
        this.name = name;
    }

    /*@ assignable this.lastName; 
    @ ensures this.lastName == lastName;
    @*/
    public void setLastName( /*@ non_null @*/ String lastName) {
        this.lastName = lastName;
    }

    /*@ assignable this.role; 
    @ ensures this.role == role;
    @*/
    public void setRole( /*@ non_null @*/ UserRoles role) {
        this.role = role;
    }

    /*@ assignable this.email; 
    @ ensures this.email == email;
    @*/
    public void setEmail( /*@ non_null @*/ String email) {
        this.email = email;
    }

    /*@ assignable this.password; 
    @ ensures this.password == password;
    @*/
    public void setPassword( /*@ non_null @*/ String password) {
        this.password = password;
    }

    /*@ assignable User.countID; 
    @ ensures User.countID == countID;
    @*/
    public static void setCountID(int countID) {
        User.countID = countID;
    }

    @Override
    public /*@ pure @*/ String toString() {
        return String.format(userInformation, name, lastName, role, email, password, id);
    }
}
