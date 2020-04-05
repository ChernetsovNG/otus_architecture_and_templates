package ru.nchernetsov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringWorkerTest {

    private StringWorker stringWorker;

    @BeforeEach
    public void setUp() {
        stringWorker = new StringWorker();
    }

    @Test
    public void getStringSymbolsTest() {
        MyString string = new MyString("hello");
        char[] symbols = stringWorker.getStringSymbols(string);
        assertThat(symbols).containsExactly('h', 'e', 'l', 'l', 'o');
        assertThat(symbols).hasSize(5);
    }

    @Test
    public void getSubstringSymbolsTest() {
        MyString string = new MyString("hello");
        char[] symbols = stringWorker.getSubstringSymbols(string, 2, 5);
        assertThat(symbols).containsExactly('l', 'l', 'o');
        assertThat(symbols).hasSize(3);
    }

    @Test
    public void shouldThrowExceptionIfFromBiggerThanStringLength() {
        assertThrows(IllegalArgumentException.class, () ->
                stringWorker.getSubstringSymbols(new MyString("hello"), 10, 5));
    }

    @Test
    public void shouldThrowExceptionIfToLowerThanZero() {
        assertThrows(IllegalArgumentException.class, () ->
                stringWorker.getSubstringSymbols(new MyString("hello"), 2, -7));
    }

    @Test
    public void shouldThrowExceptionIfFromBiggerThanTo() {
        assertThrows(IllegalArgumentException.class, () ->
                stringWorker.getSubstringSymbols(new MyString("hello"), 3, 1));
    }
}
