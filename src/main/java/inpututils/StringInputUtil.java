package inpututils;

import java.util.Scanner;

public class StringInputUtil {

    public static String get() {

        Scanner i = new Scanner(System.in);
        boolean out = false;
        String x = "";

        while (!out) {
            if (i.hasNextLine()) {
                x = i.nextLine();
                out = true;
            } else {
                System.out.println("Incorrect type. Only string allowed.");
                i = new Scanner(System.in);
            }
        }
        return x;
    }

}
