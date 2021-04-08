package exceptions;

public class AlreadyLoggedIn extends Exception {
    private static String message = "User is already logged in!";
    @Override
    public String toString() {
        return message;
    }
}
