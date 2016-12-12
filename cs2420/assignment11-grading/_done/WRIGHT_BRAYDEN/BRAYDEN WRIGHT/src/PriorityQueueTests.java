package assignment11;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Brayden Wright (u0895942)
 */
public class PriorityQueueTests {
    private PriorityQueue<Integer> intQueue;
    private PriorityQueue<String> strQueue;

    @Before
    public void setup() {
        intQueue = new PriorityQueue<Integer>();
        strQueue = new PriorityQueue<String>();
        
        strQueue.add("some");
        strQueue.add("uncreative");
        strQueue.add("words");
        strQueue.add("are");
        strQueue.add("being");
        strQueue.add("added");
        strQueue.add("to");
        strQueue.add("the");
        strQueue.add("priority");
        strQueue.add("queue");
    }

    @Test
    public void QueueSizeTest() {
        intQueue.add(1);
        assertEquals(1, intQueue.size());

        for (int i = 2; i <= 20; i++) {
            intQueue.add(new Random().nextInt(100));
        }
        assertEquals(20, intQueue.size());
//        intQueue.generateDotFile("queuedots/queueSizeTest.dot");
    }

    @Test
    public void QueueFindMinTest() {
        for (int i = 20; i >= 0; i--) {
            intQueue.add(i);
        }
        assertEquals(0, (int) intQueue.findMin());
    }

    @Test
    public void QueueAddTest() {
        for (int i = 1; i < 20; i++) {
            intQueue.add(new Random().nextInt(100));
        }
        intQueue.add(-1);
        assertEquals(-1, (int) intQueue.findMin());
    }

    @Test
    public void QueueDeleteMintest() {
        for (int i = 1; i < 20; i++) {
            intQueue.add(new Random().nextInt(100));
        }
        intQueue.add(-1);
        intQueue.add(-2);
        assertEquals(-2, (int) intQueue.deleteMin());
        assertEquals(-1, (int) intQueue.findMin());
    }

    @Test(expected = NoSuchElementException.class)
    public void EmptyQueueFindMinTest() {
        intQueue.findMin();
    }

    @Test(expected = NoSuchElementException.class)
    public void EmptyQueueDeleteMinTest() {
        intQueue.deleteMin();
    }

    @Test
    public void SmallQueueFindMinTest() {
        intQueue.add(3);
        assertEquals(3, (int) intQueue.findMin());
    }

    @Test
    public void SmallQueueDeleteMinTest() {
        intQueue.add(3);
        assertEquals(3, (int) intQueue.deleteMin());
        assertEquals(0, intQueue.size());
    }

    @Test
    public void StringQueueFindMinTest() {
        assertEquals("added", strQueue.findMin());
    }

    @Test
    public void StringQueueDeleteMinTest() {
        assertEquals("added", strQueue.deleteMin());
        assertEquals("are", strQueue.findMin());
    }
}
