package org.example.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CircularQueueTest {

    @Test
    public void shouldAddNumbersToTheQueue() throws Exception {
        CircularQueue q = new CircularQueue();

        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);

        Assertions.assertAll(
                () -> Assertions.assertEquals(4, q.dequeue()),
                () -> Assertions.assertEquals(5, q.dequeue()),
                () -> Assertions.assertEquals(6, q.dequeue())
        );
    }

    @Test
    public void shouldRemoveElementsFromQueue1() throws Exception {
        CircularQueue q = new CircularQueue();

        q.enqueue(2);
        q.enqueue(3);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, q.dequeue()),
                () -> Assertions.assertEquals(3, q.dequeue())
        );
    }

    @Test
    public void shouldRemoveElementsFromQueue2() throws Exception {
        CircularQueue q = new CircularQueue();

        for (int i = 0; i < 10; i++) {
            q.enqueue(i * 100);
        }

        q.dequeue();
        q.dequeue();

        q.enqueue(1000);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, q.dequeue()),
                () -> Assertions.assertEquals(300, q.dequeue())
        );
    }

    @Test
    public void shouldReturnTrueForEmptyQueue() {
        CircularQueue q = new CircularQueue();

        Assertions.assertTrue(q.isEmpty());
    }

    @Test
    public void shouldReturnFalseForNonEmptyQueue() throws Exception {
        CircularQueue q = new CircularQueue();

        q.enqueue(2);
        q.enqueue(3);

        Assertions.assertFalse(q.isEmpty());
    }

    @Test
    public void shouldReturnTrueForEmptyQueueAfterRemovalOfTwoElements() throws Exception {
        CircularQueue q = new CircularQueue();

        q.enqueue(2);
        q.enqueue(3);

        q.dequeue();
        q.dequeue();

        Assertions.assertTrue(q.isEmpty());
    }

//    @Test
//    public void shouldAddElementsToFrontOfArrayIfTheyAreVacant() throws Exception {
//        CircularQueue q = new CircularQueue();
//
//        for (int i = 0; i < 10; i++) {
//            q.enqueue(i * 100);
//        }
//
//        q.dequeue();
//        q.enqueue(1000);
//        q.dequeue();
//        q.enqueue(102);
//        q.dequeue();
//        q.enqueue(103);
//        q.dequeue();
//        System.out.println(q);
//    }
}