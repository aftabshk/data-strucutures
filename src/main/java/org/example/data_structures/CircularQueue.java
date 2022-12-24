package org.example.data_structures;

import org.example.exceptions.QueueOverflowException;
import org.example.exceptions.QueueUnderflowException;

public class CircularQueue {
    private final Integer[] data = new Integer[10];
    private Integer tail = -1;
    private Integer head = -1;

    public void enqueue(Integer element) throws Exception {
        if (this.isFull()) {
            throw new QueueOverflowException();
        }
        if (this.isEmpty()) {
            this.head = 0;
        }

        this.tail = (this.tail + 1) % this.data.length;
        this.data[tail] = element;

        /*** This is to implement an increasing version of circular queue
         if (this.isFull()) {
         data = Arrays.copyOf(data, head + this.data.length + 10);
         for (int i = 0; i < this.head; i++) {
         this.add(this.data[i]);
         }
         } */
    }

    public Integer dequeue() throws QueueUnderflowException {
        if (this.isEmpty()) {
            throw new QueueUnderflowException();
        }

        Integer removedElement = this.data[head];
        if (this.head.equals(this.tail)) {
            this.head = -1;
            this.tail = -1;
        } else {
            this.head = (this.head + 1) % this.data.length;
        }

        return removedElement;
    }

    public boolean isEmpty() {
        return this.head.equals(-1) && this.tail.equals(-1);
    }

    public boolean isFull() {
        return ((this.tail + 1) % this.data.length) == this.head;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder("[");
        int i = this.head;

        while (i != this.tail) {
            result.append(this.data[i]).append(", ");
            i = (i + 1) % this.data.length;
        }

        result.append(this.data[i]).append("]");
        return result.toString();
    }
}
