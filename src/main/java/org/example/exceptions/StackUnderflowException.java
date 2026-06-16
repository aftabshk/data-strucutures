package org.example.exceptions;

public class StackUnderflowException extends Exception {

    public StackUnderflowException() {
        super("Stack underflow error!");
    }
}
