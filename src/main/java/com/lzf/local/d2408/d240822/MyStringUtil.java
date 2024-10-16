package com.lzf.local.d2408.d240822;

/**
 * @author leizf
 * @since 2024-08-22 18:36
 */
public class MyStringUtil {

    public static String toLength3(String originalString) {
        if (originalString == null || originalString.length() >= 3) {
            return originalString;
        } else {
            return toLength3(originalString + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println(toLength3("S"));
        System.out.println(toLength3("EN"));
        System.out.println(toLength3("ZHT"));
    }
}
