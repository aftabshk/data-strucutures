package org.example.exceptions;

public class QueueUnderflowException extends Exception {

    public QueueUnderflowException() {
        super("Queue underflow error!");
    }
}
