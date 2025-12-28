package com.passwordgen.service;

import java.util.Random;

public class PasswordService {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS    = "0123456789";
    private static final String SPECIAL    = "!@#$%&*()_+-=[]{}|;':,.<>?";

    public static String generatePassword(
            int length,
            boolean includeUpper,
            boolean includeNumbers,
            boolean includeSpecial) {

        if (length < 8 || length > 50) {
            throw new IllegalArgumentException("Password length must be between 8 and 50");
        }

        StringBuilder pool = new StringBuilder(CHAR_LOWER);
        Random rnd = new Random();
        StringBuilder password = new StringBuilder();

        if (includeUpper) {
            pool.append(CHAR_UPPER);
            password.append(CHAR_UPPER.charAt(rnd.nextInt(CHAR_UPPER.length())));
        }

        if (includeNumbers) {
            pool.append(NUMBERS);
            password.append(NUMBERS.charAt(rnd.nextInt(NUMBERS.length())));
        }

        if (includeSpecial) {
            pool.append(SPECIAL);
            password.append(SPECIAL.charAt(rnd.nextInt(SPECIAL.length())));
        }

        if (pool.length() == 0) {
            throw new IllegalArgumentException("At least one character type must be selected");
        }

        while (password.length() < length) {
            password.append(pool.charAt(rnd.nextInt(pool.length())));
        }

        // Shuffle
        char[] arr = password.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int j = rnd.nextInt(arr.length);
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return new String(arr);
    }
}
