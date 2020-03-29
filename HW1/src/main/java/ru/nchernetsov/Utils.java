package ru.nchernetsov;

import java.util.List;

public final class Utils {

    private Utils() {
    }

    public static char[] listToCharArray(List<Character> symbols) {
        int len = symbols.size();
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = symbols.get(i);
        }
        return chars;
    }
}
