package exceptions;

public class EmptyProductsList extends Exception {
    private static String message = "Products list is empty.";
    @Override
    public String toString() {
        return message;
    }
}
