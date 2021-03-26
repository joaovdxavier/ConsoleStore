package inpututils;

import java.util.Scanner;

public class InputUtil {

    public static int getInt() {
        Scanner i = new Scanner(System.in);
        boolean out = false;
        int x = 0;
        while (!out) {
            if (i.hasNextInt()) {
                x = i.nextInt();
                out = true;
            } else {
                System.out.println("Incorrect type. Only integer allowed. Try again.");
                i = new Scanner(System.in);
            }
        }
        return x;
    }

    public static String getString() {
        Scanner i = new Scanner(System.in);
        boolean out = false;
        String x = "";
        while (!out) {
            if (i.hasNextLine()) {
                x = i.nextLine();
                out = true;
            } else {
                System.out.println("Incorrect type. Only string allowed. Try again.");
                i = new Scanner(System.in);
            }
        }
        return x;
    }
}
