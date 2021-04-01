package exceptions;

public class EmptyProductsList extends Exception {
    @Override
    public String toString() {
        return "Products list is empty.";
    }
}
