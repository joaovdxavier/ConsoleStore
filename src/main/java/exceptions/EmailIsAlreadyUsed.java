package main.java.exceptions;

public class EmailIsAlreadyUsed extends Exception {
    private static String message = "Email is already in use, choose another!";
    @Override
    public String toString() {
        return message;
    }
}
