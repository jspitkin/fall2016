package assignment06;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DoublyLinkedListTest {

	DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	public void addFirstTest() {
		testList.addFirst(0);
		if(testList.get(0) != 0){
			fail("Add  First did not return 0, returned " + testList.get(0));
		}
		if(testList.get(0) != testList.getFirst()){
			fail("get(0) is not equal to getFirst()");
		}
		if(testList.size() != 1){
			fail("Size of the list is not 1, size was " + testList.size());
		}
		testList.clear();
		if(testList.size() != 0){
			fail("Size did not reset after clear, size was " + testList.size());
		}
		testList.addFirst(1);
		if(testList.get(0) != 1){
			fail("Add first did not add 1, added " + testList.get(0));
		}
		if(testList.size() != 1){
			fail("Size did not increment correctly");
		}
		testList.clear();
	}
	
	@Test
	public void addLastTest(){
		testList.addLast(100);
		if(testList.get(0) != 100){
			fail("addLast did not add to the first location.");
		}
		if(testList.size() != 1){
			fail("size did not increment correctly AddLast");
		}
		testList.addLast(101);
		if(testList.getLast() != 101){
			fail("addLast did not add to the back of the list");
		}
		testList.clear();
	}
	
	@Test
	public void addTest(){
		testList.addFirst(1);
		testList.addLast(100);
		testList.add(1, 50);
		if(testList.get(0) != 1){
			fail("addFirst did not work");
		}
		if(testList.get(1) != 50){
			fail("add did not work");
		}
		if(testList.get(2) != 100){
			fail("addLast did not work");
		}
		if(testList.size() != 3){
			fail("Size should have been 3. it was "+ testList.size());
		}
		testList.clear();
	}
	
	@Test
	public void getTest(){
		for(int i = 0; i < 100; i++){
			testList.add(i, i);
		}
		for(int i = 0; i < 100; i++){
			if(testList.get(i) != i){
				fail("test failed at index " + i);
			}
		}
	}

}
