package org.example.data_structures;

import org.example.exceptions.QueueUnderflowException;

import java.util.Arrays;

public class Deque extends Queue {

    public void addFirst(Integer element) {
        if (isFull()) {
            data = Arrays.copyOf(data, this.data.length + 11);
        }

        for (int i = tail; i >= 0; i--) {
            this.data[i + 1] = this.data[i];
        }

        this.data[0] = element;
        ++tail;
    }

    public Integer removeLast() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        return this.data[tail--];
    }

    public static void main(String[] args) throws Exception {
        Deque deque = new Deque();

        for (int i = 0; i< 10; i++) {
            deque.add(i * 10);
        }
        deque.addFirst(-1);
        deque.removeLast();
        deque.removeLast();
        System.out.println(deque);
    }
}
