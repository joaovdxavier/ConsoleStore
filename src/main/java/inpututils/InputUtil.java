package inpututils;

import java.util.Scanner;

public class InputUtil {
    public static int getIntFromConsole() {
        String intValue = getStringFromConsole();
        int result = 0;
        try {
            result = Integer.parseInt(intValue);
        } catch (Exception exception) {
            System.out.println("Wrong interred value. Only integer allowed.");
        }
        return result;
    }

    public static String getStringFromConsole() {
        Scanner i = new Scanner(System.in);
        boolean out = false;
        String x = "";
        while (!out) {
            if (i.hasNextLine()) {
                x = i.nextLine();
                out = true;
            } else {
                System.out.println("Incorrect interred type. Try again.");
                i = new Scanner(System.in);
            }
        }
        return x;
    }
}
