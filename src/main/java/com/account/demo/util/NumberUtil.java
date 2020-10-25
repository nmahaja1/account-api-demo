package com.account.demo.util;

public class NumberUtil {
    public static boolean isNumeric(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException ne) {
            return false;
        }
    }
}
