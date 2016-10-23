/**
 * @author Scott Krstyen
 * UNID = U0760822
 */

package assignment06;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


public class DoublyLinkedListJUnitTest {

	DoublyLinkedList<Integer> dllInteger = new DoublyLinkedList<Integer>();
	DoublyLinkedList<String> dllString = new DoublyLinkedList<String>();
	
	@After
	public void after(){
		dllInteger.clear();
	}
	
	@Test
	public void addLastGetLastOnAnEmptyListOfIntegers(){

		dllInteger.addLast(400);
		Assert.assertEquals(400, (int) dllInteger.getLast());
	}
	
	@Test
	public void addFirstGetFirstOnAnEmptyListOfIntegers(){
		dllInteger.addFirst(500);
		
		Assert.assertEquals(500,(int) dllInteger.getFirst());
	}
	
	@Test
	public void getFirstOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		Assert.assertEquals(2,(int) dllInteger.getFirst());
	}
	
	@Test
	public void getFirstOnSetOfFiveStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.addLast("e");
		
		Assert.assertEquals("a",(String) dllString.getFirst());
	}
	
	@Test
	public void getLastOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		Assert.assertEquals(3,(int) dllInteger.getLast());
	}
	
	@Test
	public void addFirstOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addFirst(3);
		
		Assert.assertEquals(3,(int) dllInteger.getFirst());
	}
	
	@Test
	public void addAtIndexZeroOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.add(0, 3);
		
		Assert.assertEquals(3,(int) dllInteger.getFirst());
	}
	
	@Test
	public void addAtIndexTwoOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.add(2, 3);
		
		Assert.assertEquals(3,(int) dllInteger.get(2));
	}
	
	@Test
	public void addAtIndexTwoOnSetOfFiveStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.add(2,"e");
		
		Assert.assertEquals("e",(String) dllString.get(2));
	}
	
	@Test
	public void addAtIndexFiveOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.add(4, 3);
		
		Assert.assertEquals(3,(int) dllInteger.get(4));
	}
	
	@Test
	public void removeFirstOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		
		Assert.assertEquals(2,(int) dllInteger.removeFirst());
	}
	
	@Test
	public void DoublyLinkedListAfterRemoveFirstOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		dllInteger.removeFirst();
		
		
		Assert.assertEquals(4, (int) dllInteger.get(2));
	}
	
	@Test
	public void DoublyLinkedListAfterRemoveFirstOnSetOfFiveStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.addLast("e");
		
		dllString.remove(2);
		
		Assert.assertEquals("b",(String) dllString.get(2));
	}
	
	@Test
	public void removeLastOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		
		Assert.assertEquals(3,(int) dllInteger.removeLast());
	}
	
	@Test
	public void DoublyLinkedListAfterRemoveLastOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		dllInteger.removeLast();
		
		
		Assert.assertEquals(4, (int) dllInteger.getLast());
	}
	
	@Test
	public void indexOfOnSetOfSixIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		dllInteger.addLast(1);
		
		Assert.assertEquals(2, (int) dllInteger.indexOf(1));
	}
	
	@Test
	public void indexOfOnSetOfSixStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.addLast("e");
		dllString.addLast("b");
		
		
		Assert.assertEquals(3,(int) dllString.indexOf("b"));
	}
	
	@Test
	public void lastIndexOfOnSetOfSevenIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		dllInteger.addLast(5);
		dllInteger.addLast(2);
		
		
		Assert.assertEquals(5, (int) dllInteger.lastIndexOf(5));
	}
	
	@Test
	public void callSizeOnSetOfSevenIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addFirst(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		dllInteger.addLast(5);
		dllInteger.addLast(2);
		
		
		Assert.assertEquals(7, (int) dllInteger.size());
	}
	
	@Test
	public void callSizeOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addFirst(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		
		Assert.assertEquals(5, (int) dllInteger.size());
	}
	
	@Test
	public void isEmptyOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addFirst(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		
		Assert.assertEquals(false, dllInteger.isEmpty());
	}
	
	@Test
	public void isEmptyOnSetOfZeroIntegers(){
		
		Assert.assertEquals(true, dllInteger.isEmpty());
	}
	
	@Test
	public void clearOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addFirst(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		dllInteger.clear();
		
		Assert.assertEquals(true, dllInteger.isEmpty());
	}
	
	@Test
	public void toArrayOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		Object[] resultArray = new Object[] {2,5,1,4,3};
		
		Assert.assertArrayEquals(resultArray, dllInteger.toArray());
	}
	
	@Test
	public void toArrayOnSetOfFiveStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.addLast("e");
		
		Object[] resultArray = new Object[] {"a","c","d","b","e"};
		
		Assert.assertEquals(resultArray, dllString.toArray());
	}
	
	@Test
	public void hasNextOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		Iterator myIterator = dllInteger.iterator();
		
		
		
		Assert.assertEquals(true, myIterator.hasNext());
	}
	
	@Test
	public void nextOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		Iterator myIterator = dllInteger.iterator();
		
		
		
		Assert.assertEquals(2,(int) myIterator.next());
	}
	
	@Test
	public void hasNextOnSetOfFiveStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.addLast("e");
		
		Iterator myIterator = dllString.iterator();
		
		Assert.assertEquals(true, myIterator.hasNext());
	}
	
	@Test
	public void twoNextCallsOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		Iterator myIterator = dllInteger.iterator();
		myIterator.next();
		
		
		
		Assert.assertEquals(5,(int) myIterator.next());
	}
	
	@Test
	public void twoNextCallsOnSetOfFiveStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.addLast("e");
		
		Iterator myIterator = dllString.iterator();
		
		myIterator.next();
		
		Assert.assertEquals("c", myIterator.next());
	}
	
	@Test
	public void removeOnSetOfFiveIntegers(){
		dllInteger.addLast(2);
		dllInteger.addLast(5);
		dllInteger.addLast(1);
		dllInteger.addLast(4);
		dllInteger.addLast(3);
		
		Iterator myIterator = dllInteger.iterator();
		
		myIterator.next();
		myIterator.next();
		myIterator.remove();
		
		Object[] resultArray = new Object[] {2,1,4,3};
		
		
		Assert.assertEquals(resultArray ,dllInteger.toArray());
	}
	
	@Test
	public void removeOnSetOfFiveStrings(){
		dllString.addLast("a");
		dllString.addLast("c");
		dllString.addLast("d");
		dllString.addLast("b");
		dllString.addLast("e");
		
		Iterator myIterator = dllString.iterator();
		
		myIterator.next();
		myIterator.next();
		myIterator.remove();
		
		Object[] resultArray = new Object[] {"a","d","b","e"};
		
		
		Assert.assertEquals(resultArray ,dllString.toArray());
	}
	
}
