package ru.nchernetsov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StringStackTest {

    private StringStack stack;

    @BeforeEach
    public void setUp() {
        stack = new StringStack(5);
    }

    @Test
    public void negativeSizeShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> stack = new StringStack(-1));
    }

    @Test
    public void pushTest() {
        assertTrue(stack.isEmpty());
        stack.push(new MyString("hello"));
        stack.push(new MyString("world"));
        assertFalse(stack.isEmpty());
    }

    @Test
    public void popNotEmptyTest() {
        stack.push(new MyString("world"));
        MyString top = stack.pop();
        assertThat(top.getSymbols()).containsExactly('w', 'o', 'r', 'l', 'd');
        assertTrue(stack.isEmpty());
    }

    @Test
    public void popEmptyTest() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    public void peekNotEmptyTest() {
        stack.push(new MyString("world"));
        MyString top = stack.peek();
        assertThat(top.getSymbols()).containsExactly('w', 'o', 'r', 'l', 'd');
        assertFalse(stack.isEmpty());
    }

    @Test
    public void peekEmptyTest() {
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    public void getElementsTest() {
        stack.push(new MyString("hello"));
        stack.push(new MyString("world"));
        List<MyString> elements = stack.getElements();
        assertThat(elements).hasSize(2);
        assertThat(elements.get(0).getSymbols()).containsExactly('h', 'e', 'l', 'l', 'o');
        assertThat(elements.get(1).getSymbols()).containsExactly('w', 'o', 'r', 'l', 'd');
    }
}
