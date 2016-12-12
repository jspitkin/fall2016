package assignment11;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for implementation of PriorityQueue.
 * 
 * @author Ho Lam Yip u1025709
 */
public class PriorityQueueTest
{
    PriorityQueue<Integer> testPriorityQueue = new PriorityQueue<Integer>();
    
    @Test
    public void testInitialSize ()
    {
        assertEquals(testPriorityQueue.size(), 0);
    }
    
    @Test
    public void testAdd ()
    {
        testPriorityQueue.add(149);
        testPriorityQueue.add(8);
        testPriorityQueue.add(15);
        testPriorityQueue.add(37);
        
        testPriorityQueue.deleteMin();
        
        testPriorityQueue.add(11);
        testPriorityQueue.add(14);
        testPriorityQueue.add(109);
        testPriorityQueue.add(33);
        testPriorityQueue.add(4);
        testPriorityQueue.add(66);
        testPriorityQueue.add(78);
        testPriorityQueue.add(3);
        testPriorityQueue.add(80);
        
        testPriorityQueue.deleteMin();
        testPriorityQueue.deleteMin();
        
        assertEquals(testPriorityQueue.size(), 10);
    }
    
    @Test
    public void testClear ()
    {
        testPriorityQueue.add(5);
        testPriorityQueue.add(46);
        testPriorityQueue.add(73);
        testPriorityQueue.add(9);
        testPriorityQueue.add(1);
        testPriorityQueue.add(4);
        
        testPriorityQueue.clear();

        testPriorityQueue.add(42);
        
        assertEquals(testPriorityQueue.size(), 1);
    }
    
    @Test
    public void testFindMin1 ()
    {
        testPriorityQueue.add(149);
        testPriorityQueue.add(8);
        testPriorityQueue.add(15);
        testPriorityQueue.add(37);
        
        testPriorityQueue.deleteMin();
        
        testPriorityQueue.add(11);
        testPriorityQueue.add(14);
        testPriorityQueue.add(109);
        testPriorityQueue.add(33);
        testPriorityQueue.add(4);
        testPriorityQueue.add(66);
        testPriorityQueue.add(78);
        testPriorityQueue.add(3);
        testPriorityQueue.add(80);
        
        testPriorityQueue.deleteMin();
        testPriorityQueue.deleteMin();
        
        assertEquals((int) testPriorityQueue.findMin(), 11);
    }
    @Test
    public void testFindMin2 ()
    {
        testPriorityQueue.add(149);
        testPriorityQueue.add(101);
        testPriorityQueue.add(151);
        testPriorityQueue.add(307);
        
        testPriorityQueue.deleteMin();
        
        testPriorityQueue.add(98);
        testPriorityQueue.add(37);
        testPriorityQueue.add(333);
        
        testPriorityQueue.deleteMin();
        
        testPriorityQueue.add(300);
        testPriorityQueue.add(475);
        testPriorityQueue.add(660);
        
        testPriorityQueue.deleteMin();
        
        testPriorityQueue.add(768);
        testPriorityQueue.add(198);
        
        assertEquals((int) testPriorityQueue.findMin(), 149);
    }
}
