package ru.nchernetsov;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс "Строка"
 */
public class MyString {

    /**
     * указатель хранит адрес динамически выделенной памяти для размещения символов строки
     */
    private List<Character> symbols;

    /**
     * хранит длину строки в байтах
     */
    private long bytesLength;

    /**
     * конструктор без параметров
     */
    public MyString() {
        symbols = new ArrayList<>();
        bytesLength = 0;
    }

    /**
     * конструктор, принимающий в качестве параметра строковый литерал
     *
     * @param string строковый литерал
     */
    public MyString(String string) {
        char[] chars = string.toCharArray();
        int len = chars.length;
        symbols = new ArrayList<>(len);
        for (char character : chars) {
            symbols.add(character);
        }
        bytesLength = len * Character.BYTES;
    }

    /**
     * конструктор, принимающий в качестве параметра символ
     *
     * @param character символ
     */
    public MyString(char character) {
        symbols = new ArrayList<>(1);
        symbols.add(character);
        bytesLength = Character.BYTES;
    }

    /**
     * Метод получения длины строки (в символах)
     *
     * @return количество символов в строке
     */
    public long length() {
        return bytesLength / Character.BYTES;
    }

    /**
     * метод получения длины строки (в байтах)
     *
     * @return длина строки в байтах
     */
    public long bytesLength() {
        return bytesLength;
    }

    /**
     * метод очистки строки (делает строку пустой)
     */
    public void clear() {
        symbols.clear();
        bytesLength = 0;
    }

    /**
     * деструктор
     */
    public void destructor() {
        symbols = new ArrayList<>();
        bytesLength = 0;
    }

    @Override
    public String toString() {
        return "MyString{symbols=" + symbols + "}";
    }
}
