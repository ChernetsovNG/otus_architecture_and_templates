package ru.nchernetsov;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringStackTest {

    private StringStack stack;

    @Before
    public void setUp() {
        stack = new StringStack(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeSizeShouldThrowException() {
        stack = new StringStack(-1);
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

    @Test(expected = NoSuchElementException.class)
    public void popEmptyTest() {
        stack.pop();
    }

    @Test
    public void peekNotEmptyTest() {
        stack.push(new MyString("world"));
        MyString top = stack.peek();
        assertThat(top.getSymbols()).containsExactly('w', 'o', 'r', 'l', 'd');
        assertFalse(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void peekEmptyTest() {
        stack.peek();
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
