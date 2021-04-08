package exceptions;

public class EmptyUsersList extends Exception {
    private static String message = "Users list is empty.";
    @Override
    public String toString() {
        return message;
    }
}
