package org.example.exceptions;

public class QueueOverflowException extends Exception {

    public QueueOverflowException() {
        super("Queue overflow error!");
    }
}
