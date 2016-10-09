package assignment06;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * DoublyLinkedListTester.java -- JUnit Test Suite for DoublyLinkedList and its methods.
 * 
 * @author Rebekah Peterson u0871657
 */
public class DoublyLinkedListTester {

    DoublyLinkedList<String> stringList;
    DoublyLinkedList<Integer> threeIntegerList, largeIntegerList;
    DoublyLinkedList<Character> mediumCharacterList;

    @Before
    public void setUp() throws Exception {
	stringList = new DoublyLinkedList<String>();
	threeIntegerList = new DoublyLinkedList<Integer>();
	mediumCharacterList = new DoublyLinkedList<Character>();
	largeIntegerList = new DoublyLinkedList<Integer>();

	threeIntegerList.addFirst(new Integer(3));
	threeIntegerList.addFirst(new Integer(3));
	threeIntegerList.addFirst(new Integer(3));

	for (int i = 1; i < 1001; i++) {
	    largeIntegerList.addLast(i);
	}

	for (int i = 1; i < 21; i++) {
	    char ch = (char) ('a' + i);
	    mediumCharacterList.addLast(ch);
	}
    }

    //addFirst(), addLast(), and add()
    @Test
    public void addFirstTestEmpty() {
	stringList.addFirst("yay");
	assertEquals(1, stringList.size());
	assertArrayEquals(new Object[] {"yay"}, stringList.toArray());
    }
    
    @Test
    public void addFirstTestMediumList() {
	Character chara = new Character('a');
	mediumCharacterList.addFirst(chara);
	assertEquals(21, mediumCharacterList.size());
	
	Object[] expected = new Object[21];
	for (int i = 0; i < 21; i++) {
	    char ch = (char) ('a' + i);
	    expected[i] = ch;
	}
	assertArrayEquals(expected, mediumCharacterList.toArray());
    }
    
    @Test
    public void addLastTestEmpty() {
	stringList.addLast("yay");
	assertEquals(1, stringList.size());
	assertArrayEquals(new Object[] {"yay"}, stringList.toArray());
    }
    
    @Test
    public void addLastTestMediumList() {
	Character chara = new Character('v');
	mediumCharacterList.addLast(chara);
	assertEquals(21, mediumCharacterList.size());
	
	Object[] expected = new Object[21];
	for (int i = 1; i <= 21; i++) {
	    char ch = (char) ('a' + i);
	    expected[i - 1] = ch;
	}
	assertArrayEquals(expected, mediumCharacterList.toArray());
    }
    
    @Test
    public void addTest() {
	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
	list.addFirst(new Integer(5));
	list.addFirst(new Integer(4));
	list.addFirst(new Integer(3));
	list.addFirst(new Integer(2));
	list.addFirst(new Integer(1));
	
	// tests adding to front half
	list.add(1, new Integer(5));
	list.add(1, new Integer(4));
	list.add(1, new Integer(3));
	list.add(1, new Integer(2));
	list.add(1, new Integer(1));

	Object[] expected = new Object[] { 1, 1, 2, 3, 4, 5, 2, 3, 4, 5 };
	assertArrayEquals(expected, list.toArray());
	
	// tests adding to back half
	list.add(6, new Integer(6));
	list.add(7, new Integer(7));
	list.add(8, new Integer(8));
	list.add(9, new Integer(9));
	list.add(10, new Integer(10));
	
	expected = new Object[] { 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 4, 5 };
	assertArrayEquals(expected, list.toArray());
    }

    //getFirst(), getLast(), and get()
    @Test (expected = NoSuchElementException.class)
    public void getFirstTestEmpty() {
	stringList.getFirst();
    }
    
    @Test
    public void getFirstTestOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertTrue(str.equals(stringList.getFirst()));
	assertTrue(!stringList.isEmpty());
    }
    
    @Test
    public void getFirstTestTwo() {
	String str = "CS is cool";
	stringList.addFirst(str);
	String str2 = "nope";
	stringList.addFirst(str2);
	assertTrue(str2.equals(stringList.getFirst()));
    }
    
    @Test
    public void getFirstTestMedium() {
	Character ch = new Character('b');
	assertTrue(ch.equals(mediumCharacterList.getFirst()));
    }
    
    @Test (expected = NoSuchElementException.class)
    public void getLastTestEmpty() {
	stringList.getLast();
    }
    
    @Test
    public void getLastTestOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertTrue(str.equals(stringList.getLast()));
	assertTrue(!stringList.isEmpty());
    }
    
    @Test
    public void getLastTestTwo() {
	String str = "CS is cool";
	stringList.addFirst(str);
	String str2 = "nope";
	stringList.addFirst(str2);
	assertTrue(str.equals(stringList.getLast()));
    }
    
    @Test
    public void getLastTestMedium() {
	Character ch = new Character('u');
	assertTrue(ch.equals(mediumCharacterList.getLast()));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getTestEmpty() {
	stringList.get(0);
    }
    
    @Test
    public void getTestOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertTrue(str.equals(stringList.get(0)));
	assertTrue(!stringList.isEmpty());
    }
    
    @Test
    public void getTestTwo() {
	String str = "CS is cool";
	stringList.addFirst(str);
	String str2 = "nope";
	stringList.addFirst(str2);
	assertTrue(str2.equals(stringList.get(0)));
    }
    
    @Test
    public void getTestMedium() {
	Character ch = new Character('e');
	assertTrue(ch.equals(mediumCharacterList.get(3)));
    }
    
    @Test (expected = IndexOutOfBoundsException.class)
    public void getTestException() {
	mediumCharacterList.get(20);
    }
    
    @Test
    public void getTestFromFrontHalf() {
	Integer integer = new Integer(403);
	assertTrue(integer.equals(largeIntegerList.get(402)));
    }
    
    @Test
    public void getTestFromBackHalf() {
	Integer integer = new Integer(721);
	assertTrue(integer.equals(largeIntegerList.get(720)));
    }

    //removeFirst(), removeLast(), and remove()
    @Test (expected = NoSuchElementException.class)
    public void removeFirstTestEmpty() {
	stringList.removeFirst();
    }
    
    @Test
    public void removeFirstTestOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertTrue(str.equals(stringList.removeFirst()));
	assertTrue(stringList.isEmpty());
    }
    
    @Test
    public void removeFirstTestTwo() {
	String str = "CS is cool";
	stringList.addFirst(str);
	String str2 = "nope";
	stringList.addFirst(str2);
	assertTrue(str2.equals(stringList.removeFirst()));
    }
    
    @Test
    public void removeFirstTestMedium() {
	Character ch = new Character('b');
	assertTrue(ch.equals(mediumCharacterList.removeFirst()));
    }
    
    @Test (expected = NoSuchElementException.class)
    public void removeLastTestEmpty() {
	stringList.removeLast();
    }
    
    @Test
    public void removeLastTestOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertTrue(str.equals(stringList.removeLast()));
	assertTrue(stringList.isEmpty());
    }
    
    @Test
    public void removeLastTestTwo() {
	String str = "CS is cool";
	stringList.addFirst(str);
	String str2 = "nope";
	stringList.addFirst(str2);
	assertTrue(str.equals(stringList.removeLast()));
    }
    
    @Test
    public void removeLastTestMedium() {
	Character ch = new Character('u');
	assertTrue(ch.equals(mediumCharacterList.removeLast()));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void removeTestEmpty() {
	stringList.remove(0);
    }
    
    @Test
    public void removeTestOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertTrue(str.equals(stringList.remove(0)));
	assertTrue(stringList.isEmpty());
    }
    
    @Test
    public void removeTestTwo() {
	String str = "CS is cool";
	stringList.addFirst(str);
	String str2 = "nope";
	stringList.addFirst(str2);
	assertTrue(str2.equals(stringList.remove(0)));
    }
    
    @Test
    public void removeTestMedium() {
	Character ch = new Character('e');
	assertTrue(ch.equals(mediumCharacterList.remove(3)));
    }
    
    @Test (expected = IndexOutOfBoundsException.class)
    public void removeTestException() {
	mediumCharacterList.remove(20);
    }
    
    @Test
    public void removeTestFromFrontHalf() {
	Integer integer = new Integer(403);
	assertTrue(integer.equals(largeIntegerList.remove(402)));
    }
    
    @Test
    public void removeTestFromBackHalf() {
	Integer integer = new Integer(721);
	assertTrue(integer.equals(largeIntegerList.remove(720)));
    }

    //indexOf() and lastIndexOf()
    @Test
    public void indexOfTest() {
	assertEquals(1, mediumCharacterList.indexOf('c'));
    }
    
    @Test
    public void lastIndexOfTest() {
	assertEquals(1, mediumCharacterList.lastIndexOf('c'));
    }
    
    @Test
    public void indexOfTestWithRepeats() {
	assertEquals(0, threeIntegerList.indexOf(3));
    }
    
    @Test
    public void lastIndexOfTestWithRepeats() {
	assertEquals(2, threeIntegerList.lastIndexOf(3));
    }
    
    @Test
    public void indexOfTestWithout() {
	assertEquals(-1, threeIntegerList.indexOf(7));
    }
    
    @Test
    public void lastIndexOfTestWithout() {
	assertEquals(-1, threeIntegerList.lastIndexOf(7));
    }
    
    @Test
    public void indexOfTestEmpty() {
	assertEquals(-1, stringList.indexOf("never!"));
    }
    
    @Test
    public void lastIndexOfTestEmpty() {
	assertEquals(-1, stringList.lastIndexOf("never!"));
    }
    
    //size
    @Test
    public void sizeTestEmpty() {
	assertEquals(0, stringList.size());
    }

    @Test
    public void sizeTestOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertEquals(1, stringList.size());
    }

    @Test
    public void sizeTestThree() {
	assertEquals(3, threeIntegerList.size());
    }

    @Test
    public void sizeTestMedium() {
	assertEquals(20, mediumCharacterList.size());
    }

    @Test
    public void sizeTestLarge() {
	assertEquals(1000, largeIntegerList.size());
    }

    //clear() and isEmpty()
    @Test
    public void isEmptyTest() {
	assertTrue(stringList.isEmpty());
	assertFalse(threeIntegerList.isEmpty());
    }

    @Test
    public void clearTest() {
	stringList.clear();
	mediumCharacterList.clear();
	threeIntegerList.clear();
	assertTrue(stringList.isEmpty() && mediumCharacterList.isEmpty() && threeIntegerList.isEmpty());
    }

    //toArray()
    @Test
    public void toArrayTestEmpty() {
	assertArrayEquals(new Object[0], stringList.toArray());
    }

    @Test
    public void toArrayTestSizeOne() {
	String str = "CS is cool";
	stringList.addFirst(str);
	assertArrayEquals(new Object[] {str}, stringList.toArray());
    }

    @Test
    public void toArrayTestSizeThree() {
	assertArrayEquals(new Object[] {new Integer(3), new Integer(3), new Integer(3)}, threeIntegerList.toArray());
    }

    @Test
    public void toArrayTestMedium() {
	Object[] expected = new Object[20];
	for (int i = 1; i < 21; i++) {
	    char ch = (char) ('a' + i);
	    expected[i - 1] = ch;
	}
	assertArrayEquals(expected, mediumCharacterList.toArray());
    }

    @Test
    public void toArrayTestLarge() {
	Object[] expected = new Object[1000];
	for (int i = 1; i < 1001; i++) {
	    expected[i - 1] = new Integer(i);
	}
	assertArrayEquals(expected, largeIntegerList.toArray());
    }

}







