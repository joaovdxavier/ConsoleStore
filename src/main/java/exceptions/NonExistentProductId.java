package exceptions;

public class NonExistentProductId extends Exception {
    public String toString() {
        return "The product with the given ID does not exist.";
    }
}
