package exceptions;

public class EpmtyUsersList extends Exception {
    @Override
    public String toString() {
        return "Users list is empty.";
    }
}
