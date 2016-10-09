package assignment06;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * Test cases for implementation of DoublyLinkedList.
 * 
 * @author Ho Lam Yip u1025709
 */
public class DoublyLinkedListTest
{
    DoublyLinkedList<Integer> testIntegerList = new DoublyLinkedList<Integer>();
    
    @Test
    public void testInitialSize ()
    {
        assertTrue(testIntegerList.size() == 0);
    }
    
    @Test
    public void testAddLast1 ()
    {
        testIntegerList.addLast(30);
        testIntegerList.addLast(20);
        testIntegerList.addLast(10);
        testIntegerList.addLast(45);
        testIntegerList.addLast(35);
        testIntegerList.addLast(25);
        testIntegerList.addLast(15);
        testIntegerList.addLast(5);
        
        assertTrue(testIntegerList.getLast() == 5);
    }
    @Test
    public void testAddLast2 ()
    {
        testIntegerList.addLast(30);
        testIntegerList.addLast(20);
        testIntegerList.addLast(10);
        testIntegerList.addLast(45);
        testIntegerList.addLast(35);
        testIntegerList.addLast(25);
        testIntegerList.addLast(15);
        testIntegerList.addLast(5);
        
        assertTrue(testIntegerList.getFirst() == 30);
    }
    
    @Test
    public void testAddFirst1 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.addFirst(20);
        testIntegerList.addFirst(10);
        testIntegerList.addFirst(45);
        testIntegerList.addFirst(35);
        testIntegerList.addFirst(25);
        testIntegerList.addFirst(15);
        testIntegerList.addFirst(5);
        
        assertTrue(testIntegerList.getFirst() == 5);
    }
    @Test
    public void testAddFirst2 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.addFirst(20);
        testIntegerList.addFirst(10);
        testIntegerList.addFirst(45);
        testIntegerList.addFirst(35);
        testIntegerList.addFirst(25);
        testIntegerList.addFirst(15);
        testIntegerList.addFirst(5);
        
        assertTrue(testIntegerList.getLast() == 30);
    }
    
    @Test
    public void testAdd1 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.add(1, 20);
        testIntegerList.add(1, 10);
        testIntegerList.add(0, 45);
        testIntegerList.add(3, 35);
        testIntegerList.add(3, 25);
        testIntegerList.add(0, 15);
        testIntegerList.add(1, 5);
        
        assertTrue(testIntegerList.get(2) == 45);
    }
    @Test
    public void testAdd2 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.addLast(20);
        testIntegerList.add(0, 10);
        testIntegerList.add(2, 5);
        testIntegerList.add(2, 25);
        
        assertTrue(testIntegerList.get(1) == 30);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void testAdd3 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.addLast(20);
        testIntegerList.add(0, 10);
        testIntegerList.add(2, 5);
        testIntegerList.add(2, 25);
        testIntegerList.add(221331, 25);
    }
    
    @Test
    public void testRemoveLast1 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.add(1, 20);
        testIntegerList.addFirst(45);
        testIntegerList.add(3, 35);
        testIntegerList.addFirst(100);
        testIntegerList.add(0, 15);
        testIntegerList.addLast(65);
        testIntegerList.add(1, 5);
        testIntegerList.removeLast();
        
        assertTrue(testIntegerList.getLast() == 35);
    }
    @Test
    public void testRemoveLast2 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.add(1, 20);
        testIntegerList.addFirst(45);
        testIntegerList.add(3, 35);
        testIntegerList.addFirst(100);
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        
        assertTrue(testIntegerList.get(2) == 30);
    }
    @Test (expected = NoSuchElementException.class)
    public void testRemoveLast3 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.add(1, 20);
        testIntegerList.addFirst(45);
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        testIntegerList.removeLast();
    }
    
    @Test
    public void testRemoveFirst1 ()
    {
        testIntegerList.addFirst(30);
        testIntegerList.add(0, 20);
        testIntegerList.addFirst(45);
        testIntegerList.addFirst(60);
        testIntegerList.add(3, 70);
        testIntegerList.add(2, 15);
        testIntegerList.add(3, 35);
        testIntegerList.addFirst(100);
        testIntegerList.removeFirst();
        testIntegerList.removeFirst();
        testIntegerList.removeFirst();
        
        assertTrue(testIntegerList.getFirst() == 15);
    }
    @Test
    public void testRemoveFirst2 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addFirst(30);
        testIntegerList.removeLast();
        testIntegerList.addFirst(45);
        testIntegerList.addLast(60);
        testIntegerList.add(3, 70);
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        testIntegerList.addLast(90);
        testIntegerList.add(2, 15);
        testIntegerList.add(3, 35);
        testIntegerList.addFirst(100);
        testIntegerList.removeFirst();
        testIntegerList.removeLast();
        
        assertTrue(testIntegerList.get(1) == 30);
    }
    @Test (expected = NoSuchElementException.class)
    public void testRemoveFirst3 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addFirst(30);
        testIntegerList.remove(0);
        testIntegerList.removeLast();
        testIntegerList.removeFirst();
    }
    
    @Test
    public void testRemove1 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addFirst(30);
        testIntegerList.remove(0);
        testIntegerList.removeLast();
        testIntegerList.addFirst(45);
        testIntegerList.addLast(60);
        testIntegerList.add(0, 70);
        testIntegerList.remove(0);
        testIntegerList.remove(1);
        
        assertTrue(testIntegerList.size() == 1);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemove2 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addFirst(30);
        testIntegerList.remove(0);
        testIntegerList.removeLast();
        testIntegerList.addFirst(45);
        testIntegerList.addLast(60);
        testIntegerList.add(0, 70);
        testIntegerList.remove(0);
        testIntegerList.remove(1);
        testIntegerList.remove(1);
        testIntegerList.remove(1);
        testIntegerList.remove(1);
        testIntegerList.remove(1);
        testIntegerList.remove(1);
        testIntegerList.remove(1);
    }
    
    @Test
    public void testIndexOf1 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addLast(55);
        testIntegerList.add(1, 65);
        testIntegerList.addFirst(45);
        testIntegerList.addLast(15);
        
        assertTrue(testIntegerList.indexOf(65) == 2);
    }
    @Test
    public void testIndexOf2 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addLast(85);
        testIntegerList.add(1, 75);
        testIntegerList.addFirst(45);
        testIntegerList.addLast(85);
        testIntegerList.addFirst(25);
        testIntegerList.addFirst(5);
        
        assertTrue(testIntegerList.indexOf(85) == 3);
    }
    @Test
    public void testIndexOf3 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addLast(85);
        testIntegerList.add(1, 75);
        testIntegerList.addFirst(45);
        testIntegerList.addLast(85);
        testIntegerList.addFirst(25);
        testIntegerList.addFirst(5);
        
        assertTrue(testIntegerList.indexOf(105) == -1);
    }
    @Test
    public void testIndexOf4 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addLast(85);
        testIntegerList.add(1, 75);
        testIntegerList.addFirst(45);
        testIntegerList.addLast(85);
        testIntegerList.addFirst(25);
        testIntegerList.addFirst(5);
        testIntegerList.add(2, 105);
        testIntegerList.add(1, 35);
        testIntegerList.add(1, 95);
        
        assertTrue(testIntegerList.indexOf(85) == 6);
    }
    
    @Test
    public void testLastIndexOf1 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addLast(85);
        testIntegerList.add(1, 75);
        testIntegerList.addFirst(45);
        testIntegerList.addLast(85);
        testIntegerList.addFirst(25);
        testIntegerList.addFirst(5);
        testIntegerList.add(2, 105);
        testIntegerList.add(1, 35);
        testIntegerList.add(1, 95);
        
        assertTrue(testIntegerList.lastIndexOf(85) == 9);
    }
    @Test
    public void testLastIndexOf2 ()
    {
        testIntegerList.addLast(85);
        
        assertTrue(testIntegerList.lastIndexOf(5) == -1);
    }
    @Test
    public void testLastIndexOf3 ()
    {
        testIntegerList.addFirst(185);
        testIntegerList.addLast(85);
        testIntegerList.addLast(35);
        testIntegerList.add(1, 45);
        testIntegerList.addFirst(65);
        testIntegerList.addFirst(205);
        testIntegerList.add(1, 185);
        testIntegerList.addFirst(185);
        testIntegerList.add(1, 185);
        testIntegerList.addLast(65);
        testIntegerList.add(5, 35);
        testIntegerList.addLast(115);
        
        assertTrue(testIntegerList.lastIndexOf(185) == 6);
    }
    
    @Test
    public void testSize ()
    {
        testIntegerList.addFirst(185);
        testIntegerList.addLast(85);
        testIntegerList.addLast(35);
        testIntegerList.add(1, 45);
        testIntegerList.remove(2);
        testIntegerList.addFirst(65);
        testIntegerList.addFirst(205);
        testIntegerList.remove(2);
        testIntegerList.add(1, 185);
        testIntegerList.addFirst(185);
        testIntegerList.add(1, 185);
        testIntegerList.remove(4);
        testIntegerList.remove(5);
        testIntegerList.addLast(65);
        testIntegerList.add(5, 35);
        testIntegerList.addLast(115);
        testIntegerList.remove(1);
        testIntegerList.removeLast();
        
        assertTrue(testIntegerList.size() == 6);
    }
    
    @Test
    public void testIsEmpty ()
    {
        testIntegerList.addLast(35);
        testIntegerList.add(1, 45);
        testIntegerList.remove(1);
        testIntegerList.addFirst(65);
        testIntegerList.addFirst(205);
        testIntegerList.remove(2);
        testIntegerList.add(1, 185);
        testIntegerList.addFirst(185);
        testIntegerList.add(1, 185);
        testIntegerList.remove(3);
        testIntegerList.remove(2);
        testIntegerList.addLast(65);
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        testIntegerList.add(1, 35);
        testIntegerList.addLast(115);
        testIntegerList.remove(1);
        testIntegerList.removeLast();
        testIntegerList.removeFirst();
        
        assertTrue(testIntegerList.isEmpty());
    }
    
    @Test
    public void testClear ()
    {
        testIntegerList.addLast(35);
        testIntegerList.add(1, 45);
        testIntegerList.remove(1);
        testIntegerList.addFirst(65);
        testIntegerList.addFirst(205);
        testIntegerList.remove(2);
        testIntegerList.add(1, 185);
        testIntegerList.addFirst(185);
        testIntegerList.add(1, 185);
        testIntegerList.addLast(65);
        testIntegerList.add(1, 35);
        testIntegerList.addLast(115);
        testIntegerList.clear();
        testIntegerList.addLast(55);
        
        assertTrue(testIntegerList.size() == 1);
    }
    
    @Test
    public void testToArray1 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addFirst(30);
        testIntegerList.add(1, 25);
        testIntegerList.addFirst(65);
        
        Object testIntegerArray[] = new Object[4];
        testIntegerArray[0] = 65;
        testIntegerArray[1] = 30;
        testIntegerArray[2] = 25;
        testIntegerArray[3] = 85;
        
        assertTrue(testIntegerList.toArray()[0] == testIntegerArray[0] && 
                testIntegerList.toArray()[1] == testIntegerArray[1] && 
                testIntegerList.toArray()[2] == testIntegerArray[2] && 
                testIntegerList.toArray()[3] == testIntegerArray[3]);
    }
    @Test
    public void testToArray2 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addFirst(30);
        testIntegerList.removeLast();
        testIntegerList.addFirst(45);
        testIntegerList.addLast(60);
        testIntegerList.add(3, 70);
        testIntegerList.removeLast();
        testIntegerList.removeLast();
        testIntegerList.addLast(90);
        testIntegerList.add(2, 15);
        testIntegerList.add(3, 35);
        testIntegerList.addFirst(100);
        testIntegerList.removeFirst();
        testIntegerList.removeLast();
        
        Object testIntegerArray[] = new Object[4];
        testIntegerArray[0] = 45;
        testIntegerArray[1] = 30;
        testIntegerArray[2] = 15;
        testIntegerArray[3] = 35;
        
        assertTrue(testIntegerList.toArray()[0] == testIntegerArray[0] && 
                testIntegerList.toArray()[1] == testIntegerArray[1] && 
                testIntegerList.toArray()[2] == testIntegerArray[2] && 
                testIntegerList.toArray()[3] == testIntegerArray[3]);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void testToArray3 ()
    {
        testIntegerList.addLast(85);
        testIntegerList.addFirst(30);
        testIntegerList.add(1, 25);
        testIntegerList.addFirst(65);
        testIntegerList.clear();
        
        Object testIntegerArray[] = new Object[4];
        testIntegerArray[0] = 5;
        testIntegerArray[1] = 35;
        testIntegerArray[2] = 5;
        testIntegerArray[3] = 0;
        
        assertTrue(testIntegerList.toArray()[0] == testIntegerArray[0] && 
                testIntegerList.toArray()[1] == testIntegerArray[1] && 
                testIntegerList.toArray()[2] == testIntegerArray[2] && 
                testIntegerList.toArray()[3] == testIntegerArray[3]);
    }
}
