package inpututils;

import java.util.Scanner;

public class IntInputUtil {

    public static int get() {

        Scanner i = new Scanner(System.in);
        boolean out = false;
        int x = 0;

        while (!out) {
            if (i.hasNextInt()) {
                x = i.nextInt();
                out = true;
            } else {
                System.out.println("Incorrect type. Only integer allowed.");
                i = new Scanner(System.in);
            }
        }
        return x;
    }

}
