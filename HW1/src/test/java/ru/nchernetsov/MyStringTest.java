package ru.nchernetsov;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStringTest {

    @Test
    public void emptyConstructorTest() {
        MyString string = new MyString();
        assertThat(string.getSymbols()).isEmpty();
        assertThat(string.getBytesLength()).isEqualTo(0);
    }

    @Test
    public void stringConstructorTest() {
        MyString string = new MyString("hello");
        assertThat(string.getSymbols()).containsExactly('h', 'e', 'l', 'l', 'o');
        assertThat(string.getLength()).isEqualTo(5);
        assertThat(string.getBytesLength()).isEqualTo(10);
    }

    @Test
    public void symbolConstructorTest() {
        MyString string = new MyString("a");
        assertThat(string.getSymbols()).containsExactly('a');
        assertThat(string.getLength()).isEqualTo(1);
        assertThat(string.getBytesLength()).isEqualTo(2);
    }

    @Test
    public void clearStringTest() {
        MyString string = new MyString("hello");
        assertThat(string.getSymbols()).containsExactly('h', 'e', 'l', 'l', 'o');
        assertThat(string.getLength()).isEqualTo(5);
        assertThat(string.getBytesLength()).isEqualTo(10);
        string.clear();
        assertThat(string.getSymbols()).isEmpty();
        assertThat(string.getLength()).isEqualTo(0);
    }
}
