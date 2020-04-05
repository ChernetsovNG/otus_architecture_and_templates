package ru.nchernetsov;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringWorkerTest {

    private StringWorker stringWorker;

    @Before
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFromBiggerThanStringLength() {
        stringWorker.getSubstringSymbols(new MyString("hello"), 10, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfToLowerThanZero() {
        stringWorker.getSubstringSymbols(new MyString("hello"), 2, -7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFromBiggerThanTo() {
        stringWorker.getSubstringSymbols(new MyString("hello"), 3, 1);
    }
}
