package com.smd.extraction;

public class PasswordUtils {

    // Method to encode password with an ASCII shift
    public static String encodePassword(String password, int shift) {
        StringBuilder shiftedPassword = new StringBuilder();

        for (char ch : password.toCharArray()) {
            shiftedPassword.append((char) (ch + shift));
        }

        return shiftedPassword.toString();
    }

    // Method to decode password with an ASCII shift
    public static String decodePassword(String encodedPassword, int shift) {
        StringBuilder originalPassword = new StringBuilder();

        for (char ch : encodedPassword.toCharArray()) {
            originalPassword.append((char) (ch - shift));
        }

        return originalPassword.toString();
    }
}
