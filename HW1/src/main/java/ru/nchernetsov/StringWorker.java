package ru.nchernetsov;

import java.util.List;

/**
 * Класс работы со строками, который также может возвращать как массив символов либо всю строку, либо её часть
 */
public class StringWorker {

    /**
     * Вернуть как массив символов всю строку
     *
     * @param string строка
     * @return массив символов
     */
    public char[] getStringSymbols(MyString string) {
        int length = string.getLength();
        List<Character> symbols = string.getSymbols();
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            Character symbol = symbols.get(i);
            chars[i] = symbol;
        }
        return chars;
    }

    /**
     * Вернуть как массив символов часть строки
     *
     * @param string строка
     * @param from   левая граница диапазона (включая)
     * @param to     правая граница диапазона (исключая)
     * @return массив символов строки в указанном диапазоне
     */
    public char[] getSubstringSymbols(MyString string, int from, int to) {
        int length = string.getLength();
        checkDiapason(length, from, to);
        List<Character> symbols = string.getSymbols();
        char[] chars = new char[to - from];
        for (int i = from; i < to; i++) {
            Character symbol = symbols.get(i);
            chars[i - from] = symbol;
        }
        return chars;
    }

    private void checkDiapason(int length, int from, int to) {
        if (from >= length) {
            throw new IllegalArgumentException("from >= length");
        } else if (to <= 0) {
            throw new IllegalArgumentException("to <= 0");
        } else if (from > to) {
            throw new IllegalArgumentException("from > to");
        }
    }

}
