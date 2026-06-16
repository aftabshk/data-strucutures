package org.example.data_structures;

public class LinkedList<T> {

    private Node<T> head;

    private Node<T> tail;

    public void appendFirst(T data) {
        Node<T> node = new Node<>(data, head);
        this.head = node;
    }

    public void appendLast(T data) {
        Node<T> node = new Node<>(data, null);
        this.tail.setNext(node);
        this.tail = node;
    }

    public Boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();

        Node<T> node = this.head;
        while (node.getNext() != null) {
            result.append(node.getData()).append(" -> ");
            node = node.getNext();
        }

        result.append(node.getData());
        return result.toString();
    }

    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        private void setNext(Node<T> node) {
            this.next = node;
        }

        private Node<T> getNext() {
            return this.next;
        }

        private T getData() {
            return this.data;
        }
    }
}
