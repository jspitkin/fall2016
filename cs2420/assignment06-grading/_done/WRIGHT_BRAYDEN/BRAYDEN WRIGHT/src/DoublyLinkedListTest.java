package assignment06;

import static org.junit.Assert.*;
import org.junit.*;

import java.util.Iterator;

/**
 * @author Brayden Wright (u0895942)
 */
public class DoublyLinkedListTest {
    private static final int SIZE = 100_000;  // Keep this above 10_000, some of the tests rely on large numbers
    private DoublyLinkedList<Integer> sampleList;
    private Integer[] sampleArray;

    @Before
    public void setUp() throws Exception {
        sampleList = new DoublyLinkedList<Integer>();
        sampleArray = new Integer[SIZE];

        // Adds integers 0..SIZE - 1
        for (int count = 0; count < SIZE; count++) {
            sampleList.addLast(count);
            sampleArray[count] = count;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void AddIndexOutOfBoundExceptionTest() {
        sampleList.add(SIZE + 1, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void GetIndexOutOfBoundExceptionTest() {
        sampleList.get(sampleList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void RemoveIndexOutOfBoundExceptionTest() {
        sampleList.remove(SIZE);
    }

    @Test(expected = IllegalStateException.class)
    public void IteratorRemoveBeforeNextTest() {
        Iterator iter = sampleList.iterator();
        iter.remove();
    }

    @Test(expected = IllegalStateException.class)
    public void IteratorDoubleRemoveTest() {
        Iterator iter = sampleList.iterator();
        iter.next();
        iter.remove();
        iter.remove();
    }

    @Test
    public void ToArrayTest() {
        assertEquals(sampleArray, sampleList.toArray());
    }

    @Test
    public void CorrectSizeTest() {
        assertEquals(SIZE, sampleList.size());
    }

    @Test
    public void ClearDoublyLinkedList() {
        sampleList.clear();
        assertEquals(0, sampleList.size());
    }

    @Test
    public void IsEmptyTest() {
        assertTrue(new DoublyLinkedList<Float>().isEmpty());
    }

    @Test
    public void AddAtIndex() {
        int index = (SIZE/2);
        sampleList.add(index, 18);
        assertEquals(18, (int) sampleList.get(index));
    }

    @Test
    public void AddAtLastIndex() {
        sampleList.add(SIZE, 4);
        assertEquals(4, (int) sampleList.getLast());
    }

    @Test
    public void AddFirstToEmptyList() {
        DoublyLinkedList<String> stringList = new DoublyLinkedList<String>();
        stringList.addFirst("First");
        assertEquals("First", stringList.getFirst());
    }

    @Test
    public void AddLastToEmptyList() {
        DoublyLinkedList<String> stringList = new DoublyLinkedList<String>();
        stringList.addLast("Last");
        assertEquals("Last", stringList.getLast());
    }

    @Test
    public void GetElementAtIndex() {
        int index = SIZE/3;

        // This is valid since data = index for every node in sampleList
        assertEquals(index, (int) sampleList.get(index));
    }

    @Test
    public void RemoveFirstTest() {
        int removed = sampleList.removeFirst();
        assertEquals(0, removed);
        if (SIZE > 1) assertEquals(removed + 1, (int) sampleList.getFirst());
    }

    @Test
    public void RemoveLastTest() {
        int removed = sampleList.removeLast();
        assertEquals(SIZE - 1, removed);
        if (SIZE > 1) assertEquals(removed - 1, (int) sampleList.getLast());
    }

    @Test
    public void RemoveAtIndex() {
        int index = SIZE/2;
        int removed = sampleList.remove(index);
        assertEquals(index, removed);
        if (index + 1 < SIZE) assertEquals(removed + 1, (int) sampleList.get(index));
    }

    @Test
    public void IndexOfTests() {
        int index = sampleList.indexOf(3);
        assertEquals(3, (int) sampleList.get(index));

        index = sampleList.indexOf(SIZE);
        assertEquals(-1, index);
    }

    @Test
    public void LastIndexOfTests() {
        int index = sampleList.lastIndexOf(501);
        assertEquals(501, (int) sampleList.get(index));

        index = sampleList.lastIndexOf(SIZE);
        assertEquals(-1, index);
    }

    @Test
    public void AddAtFirstIndexTest() {
        int index = sampleList.indexOf(100);
        sampleList.add(index, 300);

        assertEquals(100, sampleList.indexOf(300));
        assertEquals(301, sampleList.lastIndexOf(300));
    }

    @Test
    public void AddAtLastIndexTest() {
        int index = sampleList.lastIndexOf(7_500);
        sampleList.add(index, 3);

        assertEquals(3, sampleList.indexOf(3));
        assertEquals(7_500, sampleList.lastIndexOf(3));
    }

    @Test
    public void SomeNullTests() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addFirst(null);
        list.addLast(null);
        list.add(1, null);

        assertEquals(3, list.size());
        assertNull(list.get(1));
    }

    @Test
    public void ClearListUsingIterator() {
        Iterator<Integer> iter = sampleList.iterator();

        assertEquals(SIZE, sampleList.size());

        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }

        assertEquals(0, sampleList.size());
    }
}
