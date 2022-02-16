package main.java.exceptions;

public class NotLoggedInException extends Exception {
    private static String message = "You can't watch this until you login.";
    public String toString() {
        return message;
    }
}
