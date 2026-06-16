package org.example.data_structures;

import org.example.exceptions.QueueUnderflowException;
import org.example.exceptions.StackUnderflowException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Stack<T> {

    T[] data = (T[]) Array.newInstance(Object.class, 10);
    Integer tail = -1;

    public void push(T element) {
        if (isFull()) {
            data = Arrays.copyOf(data, this.data.length + 11);
        }

        this.data[++tail] = element;
    }

    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        }

        T result = this.data[tail];

        this.tail--;
        return result;
    }

    public T peek() {
        return this.data[tail];
    }

    public Boolean isEmpty() {
        return this.tail.equals(-1);
    }

    private Boolean isFull() {
        return this.size() == data.length;
    }

    public Integer size() {
        return this.tail + 1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder("[");
        int i = 0;

        while (i < this.tail) {
            result.append(this.data[i]).append(", ");
            i++;
        }

        result.append(this.data[i]).append("]");
        return result.toString();
    }
}
