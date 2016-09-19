package assignment03;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
/**
 * This is a test class for BinarySearchSet
 * @author Zachary Cutler (u1025642) and Alessandro Ljubicic (U0753409)
 *
 */

public class BinarySearchSetTest {

	Integer[] arr;
	Integer[] arr2;
	Integer[] arr3;
	Integer[] arr4;
	ArrayList<Integer> list;
	ArrayList<Integer> list2;
	
	@Before
	public void setUp() throws Exception {
		arr = new Integer[]{1, 2, 5, 7, 8};
		arr2 = new Integer[]{9, 87, 247};
		arr3 = new Integer[]{1, 5, 7, 9, 20, 27, 30, 33, 40, 90};
		arr4 = new Integer[]{1, 7, 9, 20, 27, 30, 33, 40};
		list = new ArrayList<Integer>();
		list.add(5);
		list.add(9);
		list.add(27);
		list.add(33);
		list2 = new ArrayList<Integer>();
		list2.add(247);
		list2.add(9);
		list2.add(87);
		
		
	}

	@Test
	public void addTestBoolean(){
		BinarySearchSet<String> _arr = new BinarySearchSet<String>();
		assertEquals(_arr.add("blue"), true);
		_arr.add("min");
		assertEquals(_arr.add("min"), false);
	}

	@Test
	public void addTest() {
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(8);
		_arr2.add(1);
		_arr2.add(2);
		_arr2.add(5);
		_arr2.add(7);
		assertEquals(_arr2.toArray(), arr);
	}
	
	@Test
	public void largeAddTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		assertEquals(_arr2.toArray(), arr3);
	}

	@Test
	public void addAllTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(40);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.addAll(list);
		assertEquals(_arr2.toArray(), arr3);
	}
	
	@Test
	public void addAllTestEmptyArray(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.addAll(list2);
		assertEquals(_arr2.toArray(), arr2);
	}

	@Test
	public void firstAndLastTest(){
		BinarySearchSet<Integer> _arr = new BinarySearchSet<Integer>();
		_arr.add(79);
		_arr.add(2);
		_arr.add(10);
		assertTrue(_arr.first().equals(2));
		assertTrue(_arr.last().equals(79));
	}
	
	@Test
	public void largeFirstAndLastTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		assertTrue(_arr2.first().equals(1));
		assertTrue(_arr2.last().equals(90));
	}
	
	@Test
	public void firstAndLastTestAfterRemove(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		_arr2.remove(5);
		_arr2.remove(90);
		assertTrue(_arr2.first().equals(1));
		assertTrue(_arr2.last().equals(40));
	}
	
	@Test (expected = Exception.class)
	public void firstExceptions(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.remove(9);
		_arr2.first();
	}
	
	@Test (expected = Exception.class)
	public void lastExceptions(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.remove(9);
		_arr2.last();
	}
	
	@Test
	public void largeContainsTestAfterRemove(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		_arr2.add(null);
		_arr2.remove(30);
		assertTrue(_arr2.contains(33));
		assertFalse(_arr2.contains(30));
	}
	
	@Test
	public void containsTest(){
		BinarySearchSet<Integer> _arr = new BinarySearchSet<Integer>();
		_arr.add(7);
		_arr.add(0);
		_arr.add(9);
		assertTrue(_arr.contains(0));
		assertFalse(_arr.contains(2));
	}
	
	@Test
	public void containsTestWithZero(){
		BinarySearchSet<Integer> _arr = new BinarySearchSet<Integer>();
		assertFalse(_arr.contains(7));
	}
	
	@Test
	public void containsAllTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		assertTrue(_arr2.containsAll(list));
		_arr2.remove(33);
		assertFalse(_arr2.containsAll(list));
	}
	
	@Test
	public void containsAllTestAtZero(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		assertFalse(_arr2.containsAll(list));
	}
	
	@Test
	public void removeTest(){
		BinarySearchSet<Integer> _arr = new BinarySearchSet<Integer>();
		_arr.add(7);
		_arr.add(0);
		_arr.add(9);
		assertFalse(_arr.remove(12));
		assertTrue(_arr.remove(7));
	}
	
	@Test
	public void largeRemoveTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		_arr2.remove(5);
		_arr2.remove(90);
		assertEquals(_arr2.toArray(), arr4);
	}
	
	@Test
	public void removeTestAtZero(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		BinarySearchSet<Integer> _arr1 = new BinarySearchSet<Integer>();
		_arr2.remove(7);
		assertEquals(_arr2.toArray(), _arr1.toArray());
	}
	
	@Test
	public void sizeTestAfterRemove(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		_arr2.remove(5);
		_arr2.remove(90);
		assertEquals(_arr2.size(), 8);
	}
	
	@Test
	public void normalSizeTest(){
		BinarySearchSet<Integer> _arr = new BinarySearchSet<Integer>();
		_arr.add(7);
		_arr.add(0);
		_arr.add(9);
		assertEquals(_arr.size(), 3);
	}
	
	@Test
	public void largeSizeTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		assertEquals(_arr2.size(), 10);
	}
	
	@Test
	public void sizeTestAtZero(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		assertEquals(_arr2.size(), 0);
	}
	
	@Test
	public void clearTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		_arr2.clear();
		Integer[] empty = new Integer[]{};
		assertEquals(_arr2.toArray(), empty);
		assertEquals(_arr2.size(),  0);
	}
	
	@Test
	public void isEmptyTest(){
		BinarySearchSet<Integer> _arr2 = new BinarySearchSet<Integer>();
		BinarySearchSet<Integer> _arr1 = new BinarySearchSet<Integer>();
		_arr2.add(9);
		_arr2.add(27);
		_arr2.add(40);
		_arr2.add(5);
		_arr2.add(7);
		_arr2.add(90);
		_arr2.add(1);
		_arr2.add(20);
		_arr2.add(30);
		_arr2.add(33);
		_arr2.clear();
		assertTrue(_arr1.isEmpty());
		assertTrue(_arr2.isEmpty());
	}
}
