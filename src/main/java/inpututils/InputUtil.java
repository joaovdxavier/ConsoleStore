package main.java.inpututils;

import java.util.Scanner;

public class InputUtil {
    private /*@ spec_public non_null @*/ static String wrongValueError = "Wrong interred value. Only integer allowed.";
    private /*@ spec_public non_null @*/ static String wrongTypeError = "Incorrect interred type. Try again.";
    
    /*@ signals_only Exception;
     @*/
    public /*@ pure @*/ static int getIntFromConsole() {
        String intValue = getStringFromConsole();
        int result = 0;
        try {
            result = Integer.parseInt(intValue);
        } catch (Exception exception) {
            System.out.println(wrongValueError);
        }
        return result;
    }
    
    /*@ signals_only Exception;
    @*/
    public /*@ pure @*/ static String getStringFromConsole() {
        Scanner i = new Scanner(System.in);
        boolean out = false;
        String x = "";
        while (!out) {
            if (i.hasNextLine()) {
                x = i.nextLine();
                out = true;
            } else {
                System.out.println(wrongTypeError);
                i = new Scanner(System.in);
            }
        }
        return x;
    }
}
