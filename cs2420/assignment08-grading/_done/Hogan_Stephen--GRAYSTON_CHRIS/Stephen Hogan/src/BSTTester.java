package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


/**
 * JUnit testing class for Binary Search Tree. 
 * 
 * @author Stephen Hogan u0813633 and Chris Grayston u0906710
 *
 */
public class BSTTester {

    private static final int ITER_COUNT = 100;
    
    private BinarySearchTree<Integer> BST1;
    private BinarySearchTree<Integer> BST2;
    private BinarySearchTree<Integer> BST3;
    private BinarySearchTree<String> BST4;
    private BinarySearchTree<Integer> BST5;
    
    private Random rand = new Random();
    
    private ArrayList<Integer> alInt;
    
    private Collection<Integer> collInt;
    
    @Before
    public void setUp() throws Exception {
        BST1 = new BinarySearchTree<Integer>();
        BST2 = new BinarySearchTree<Integer>();
        BST3 = new BinarySearchTree<Integer>();
        BST4 = new BinarySearchTree<String>();
        BST5 = new BinarySearchTree<Integer>();
        
        collInt = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++)
            collInt.add(i);
        
        
        alInt = new ArrayList<Integer>(20);
        
        
        BST1.add(25);
        for(int i = 0; i < 20; i++){
            alInt.add(i);
            BST1.add(rand.nextInt(50));
        }
        
        
        BST1.add(100);
        
        BST2.add(25);
        BST2.add(30);
        BST2.add(29);
        BST2.add(37);
        BST2.add(33);
        BST2.add(31);
        BST2.add(10);
        BST2.add(11);
        
        for(int i = 0; i < 50; i++)
            BST5.add(i);
        
        
    }

    @Test
    public void testRemoveCase3NoChildrenAfterSuccessor() {
        BST2.writeDot("testBSTbefore.DOT");
        BST2.remove(30);
        BST2.writeDot("testBSTafter.DOT");
    }
    
    @Test
    public void testRemoveCase3With1ChildAfterSuccessor() {
        BST2.add(32);
        BST2.writeDot("testBSTbefore.DOT");
        BST2.remove(30);
        BST2.writeDot("testBSTafter.DOT");
    }


    @Test
    public void testContains() {
        assertTrue(BST2.contains(30));
    }
    
    /*
    * add(T item) Tests
    */
    @Test
    public void addIntTest() {
        BST3.add(69);
        assertTrue(BST3.contains(69));
        
        assertFalse(BST3.contains(000));
    }
    
    @Test
    public void addStringTest() {
        BST4.add("First");
        BST4.add("Entered");
        
        assertTrue(BST4.contains("First"));    
        assertFalse(BST4.contains("fIRST"));
    }
    
    
    @Test (expected=NullPointerException.class)
    public void addNullTest() {
        BST4.add(null);
        BST4.add("Entered");

    }
    
    /*
    * addAll(Collection<>) Tests
    */
    @Test
    public void addAllTest() {
        BST3.addAll(collInt);
        
        assertTrue(BST3.contains(0));
        assertTrue(BST3.contains(3));
    }
    
    @Test
    public void addAllAlreadyContainedTest() {
        BST5.addAll(collInt);
        
        assertTrue(BST5.contains(0));
        assertTrue(BST5.contains(3));
    }
    
    @Test (expected=NullPointerException.class)
    public void addAllNullItem() {
        collInt.add(null);
        
        BST5.addAll(collInt);
        
        assertTrue(BST5.contains(0));
        assertTrue(BST5.contains(3));
    }
    
    /*
    * clear() Tests
    */
    @Test
    public void clearTest() {
        BST3.add(1);
        assertEquals(1, BST3.size());
        
        BST3.clear();
        assertEquals(0, BST3.size());
    }
    
    @Test
    public void clearEmptyTest() {
        assertEquals(0, BST3.size());
        
        BST3.clear();
        assertEquals(0, BST3.size());
    }
    
    /*
    * contains(T) tests
    */
    @Test
    public void containsIntTest() {
        assertTrue(BST2.contains(30));
        
        assertFalse(BST2.contains(-39));
    }
    
    @Test
    public void containsStringTest() {
        BST4.add("String");
        assertTrue(BST4.contains("String"));
        
        assertFalse(BST4.contains("Int"));
    }
    
    /*
    * containsAll(Collection) Tests
    */
    
    /*
    * first() Tests
    */
    @Test (expected=NoSuchElementException.class)
    public void firstNoSuchElementExceptionTest() {
        BST4.first();
    }
    
    @Test
    public void firstTest() {
        BST4.add("String");
        assertEquals("String", BST4.first());
        
        BST4.add("String1");
        BST4.add("String2");
        BST4.add("String3");
        assertEquals("String", BST4.first());    
    }
    
    /*
    * isEmpty() Tests
    */
    @Test
    public void isEmptyTest() {
        assertFalse(BST2.isEmpty());
    }
    
    @Test
    public void addOnIsEmptyTest() {
        assertTrue(BST3.isEmpty());
        
        BST3.add(1);
        assertFalse(BST3.isEmpty());
    }
    
    @Test
    public void addOnIsEmptyWithDeletionTest() {
        BST3.add(1);
        BST3.clear();
        
        assertTrue(BST3.isEmpty());        
    }
    
    /*
    * last() Tests 
    */
    @Test
    public void lastBST1Test() {
        assertEquals(new Integer(100), BST1.last());
    }
    
    @Test (expected=NoSuchElementException.class)
    public void lastNoSuchElementExceptionTest() {
        BST4.last();
    }
    
    /*
    * remove(T)
    */
    @Test
    public void removeSingleTest() {
        BST4.add("Last");
        BST4.remove("Last");
        assertFalse(BST4.contains("Last"));
    }
    
    @Test
    public void removeFalseFromBiggerListTest() {
        assertFalse(BST2.remove(999));
    }
    
    @Test
    public void removeTrueBiggerListTest() {
        assertTrue(BST2.remove(new Integer(30)));
    }
    
    @Test (expected=NullPointerException.class)
    public void removeNullPointer() {
        BST5.remove(null);    
    }
    
    
    /*
    * removeAll()
    */
    @Test
    public void removeAllTest() {
        ArrayList<Integer> test = new ArrayList<Integer>();
        
        for(int i = 0; i < 50; i++)
            test.add(i);

        assertTrue(BST5.removeAll(test));
        
        assertTrue(BST5.isEmpty());
        
    }
    
    @Test
    public void removeAllSelectiveNumbersTest() {
        assertTrue(BST5.removeAll(collInt));
        
        assertFalse(BST5.contains(0));
        assertFalse(BST5.contains(3));
    }
    
    @Test (expected=NullPointerException.class)
    public void removeAllNullPointer() {
        collInt.add(null);
        BST5.removeAll(collInt);    
    }
    
    
    
    /*
    * size() Tests
    */
    @Test 
    public void sizeEmptyTest() {
        assertEquals(0, BST3.size());
    }
    
    @Test 
    public void sizeLargeTest() {    
        assertEquals(50, BST5.size());
        
        BST5.remove(26);
        assertEquals(49, BST5.size());
        
        BST5.remove(26);
        assertEquals(49, BST5.size());
        
        assertFalse(BST5.contains(26));
    } 
    
    /*
    * toArrayList() Tests
    */    
    @Test
    public void toArrayListEmptyTest() {
        assertEquals("[null]", BST3.toArrayList().toString());
        
    }
    
    @Test 
    public void toArrayListStringTest() {
        BST4.add("First");
        BST4.add("zzzzRight");
        BST4.add("aaaaLeft");
        BST4.add("aaaaLeft");
        assertEquals("[First, aaaaLeft, zzzzRight]", BST4.toArrayList().toString());
    }
    
    @Test
    public void toArrayListIntegerTest() {
        BST3.add(-100);
        BST3.add(0);
        BST3.add(101);
        assertEquals("[-100, 0, 101]", BST3.toArrayList().toString());
        
        
    }
    
    /*
     * Testing code for contains in sorted order
     *  - add N items in sorted order then record time required to invoke contains
     *  - add N items in random order then record tiem required
     *  - x-axis range is 1000 - 10000 step size is 100
     *  - y-axis range is running times found per size of N 
     */
//    @Test
    public void timingContainsSortedOrder() {
        // Step through
        for (int stepSize = 1000; stepSize <= 10000; stepSize = stepSize + 1000) { 
            
            //  set current size
            int size = stepSize;

            // Do the experiment multiple times, and average out the results
            BinarySearchTree<Integer> containsTest = new BinarySearchTree<Integer>();
            
            // populate tree
            for(int i = 0; i < stepSize; i++) 
                containsTest.add(i);
                
            long totalTime = 0;
            for (int iter = ITER_COUNT; iter >= 0; --iter) {
                // Start time
                long start = System.nanoTime();

                containsTest.contains(stepSize);

                // Stop time
                long stop = System.nanoTime();
                totalTime += stop - start;
            }
            double averageTime = totalTime / (double) ITER_COUNT;
            System.out.println(size + "\t" + averageTime);
        }

        System.out.println("Timing done: Contains Sorted");
    }
    
    /*
     * Testing code for contains in sorted order
     *  - add N items in sorted order then record time required to invoke contains
     *  - add N items in random order then record tiem required
     *  - x-axis range is 1000 - 10000 step size is 100
     *  - y-axis range is running times found per size of N 
     */
//    @Test
    public void timingContainsUnsortedOrder() {
        // Step through
        for (int stepSize = 1000; stepSize <= 10000; stepSize = stepSize + 1000) { 
            
            //  set current size
            int size = stepSize;

            // Do the experiment multiple times, and average out the results
            BinarySearchTree<Integer> containsTest = new BinarySearchTree<Integer>();
            
            // populate tree with random numbers between 0 and stepSize - 1
            while(containsTest.size() < stepSize) {
                containsTest.add(rand.nextInt(stepSize) - 1);

            }
            
            //System.out.println(containsTest.size());
            
            
                
            long totalTime = 0;
            for (int iter = 0; iter < ITER_COUNT; ++iter) {
                // Start time
                long start = System.nanoTime();

                containsTest.contains(stepSize);

                // Stop time
                long stop = System.nanoTime();
                totalTime += stop - start;
            }
            double averageTime = totalTime / (double) ITER_COUNT;
            System.out.println(size + "\t" + averageTime);
        }

        System.out.println("Timing done: Contains Unsorted");
    
    }
    
}