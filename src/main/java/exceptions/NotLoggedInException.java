package exceptions;

public class NotLoggedInException extends Exception {
    public String toString() {
        return "You can't watch this until you login.";
    }
}
