package org.example.data_structures;

import org.example.exceptions.QueueUnderflowException;

import java.util.Arrays;

public class Queue extends AbstractQueue {

    public void add(Integer element) {
        if (isFull()) {
            data = Arrays.copyOf(data, this.data.length + 11);
        }

        this.data[++tail] = element;
    }

    public Integer remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        Integer result = this.data[0];

        for (int i = 0; i < tail; i++) {
            this.data[i] = this.data[i + 1];
        }

        this.tail--;
        return result;
    }

    public Boolean isEmpty() {
        return this.tail.equals(-1);
    }

    public Boolean isFull() {
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
