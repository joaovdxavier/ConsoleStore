package exceptions;

public class NonExistentProductId extends Exception {
    private static String message = "The product with the given ID does not exist.";
    public String toString() {
        return message;
    }
}
