package ru.nchernetsov;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StringStack {

    private List<MyString> stack;

    /**
     * Инициализация определяет начальное число элементов
     *
     * @param size начальное количество элементов
     */
    public StringStack(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("stack size < 0");
        }
        stack = new ArrayList<>(size);
    }

    /**
     * Помещение элемента в стек
     *
     * @param string элемент
     */
    public void push(MyString string) {
        stack.add(string);
    }

    /**
     * Удаление элемента из стека
     *
     * @return элемент с вершины стека
     */
    public MyString pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        int size = stack.size();
        return stack.remove(size - 1);
    }

    /**
     * Получение верхнего элемента стека без его удаления
     *
     * @return элемент с вершины стека
     */
    public MyString peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        int size = stack.size();
        return stack.get(size - 1);
    }

    /**
     * Пуст ли стек?
     *
     * @return true - если стек пуст, false - в противном случае
     */
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    /**
     * Вывод элементов стека
     *
     * @return список элементов стека (снизу вверх, т.е. элемент на вершине стека - в конце списка)
     */
    public List<MyString> getElements() {
        return new ArrayList<>(stack);
    }
}
