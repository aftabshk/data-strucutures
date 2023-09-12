package org.example;

import org.example.data_structures.Queue;
import org.example.exceptions.QueueUnderflowException;

public class Main {
    public static void main(String[] args) throws QueueUnderflowException {
        Queue<String> names = new Queue<String>();

        names.add("anya");
        names.add("subya");
        names.add("afya");

        System.out.println(names);
    }
}