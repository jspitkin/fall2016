package assignment08;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Qi Luo & Anthony Iovino 
 */
public class BinarySearchTreeTester {

	BinarySearchTree<Integer> intTree;
	BinarySearchTree<String> strTree;
	BinarySearchTree<Character> charTree;
	ArrayList<Integer> intCollection;
	ArrayList<String> strCollection;
	ArrayList<Character> charCollection;

	@Before
	public void setUp() throws Exception {
		intTree = new BinarySearchTree<Integer>();
		strTree = new BinarySearchTree<String>();
		charTree = new BinarySearchTree<Character>();
		intCollection = new ArrayList<Integer>();
		strCollection = new ArrayList<String>();
		charCollection = new ArrayList<Character>();
	}

	@Test(expected = NullPointerException.class)
	public void addIntTest(){
		intTree.add(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void addStrTest(){
		strTree.add(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void addCharTest(){
		charTree.add(null);
	}
	
	@Test
	public void addToEmptySettest() {
		intTree.add(1);
		int result1 = intTree.first();
		assertEquals(1,result1);

		strTree.add("hello");
		String result2 = strTree.first();
		assertEquals("hello",result2);

		charTree.add('a');
		char result3 = charTree.first();
		assertEquals('a',result3);
	}

	@Test
	public void addToNonEmptySettest() {
		intTree.add(5);
		intTree.add(8);
		intTree.add(3);
		intTree.add(2);
		intTree.add(10);
		assertEquals(5, intTree.size());
		int result1 = intTree.first();
		assertEquals(2,result1);
		int result2 = intTree.last();
		assertEquals(10,result2);

		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		assertEquals(6, strTree.size());
		String result3 = strTree.first();
		assertEquals("aa",result3);
		String result4 = strTree.last();
		assertEquals("yy",result4);

		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		assertEquals(8, charTree.size());
		char result5 = charTree.first();
		assertEquals('a',result5);
		char result6 = charTree.last();
		assertEquals('z',result6);
	}

	@Test
	public void addDuplicateTest() {
		intTree.add(5);
		intTree.add(8);
		intTree.add(3);
		intTree.add(2);
		intTree.add(10);
		assertEquals(5, intTree.size());
		intTree.add(10);
		assertEquals(5, intTree.size());
		
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		assertEquals(6, strTree.size());
		strTree.add("bc");
		assertEquals(6, strTree.size());

		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		assertEquals(8, charTree.size());
		charTree.add('a');
		assertEquals(8, charTree.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void addAllIntTest(){
		intCollection.add(2);
		intCollection.add(3);
		intCollection.add(1);
		intCollection.add(6);
		intCollection.add(4);
		intCollection.add(null);
		intTree.addAll(intCollection);
	}
	
	@Test(expected = NullPointerException.class)
	public void addAllStrTest(){
		strCollection.add("hello");
		strCollection.add(null);
		strTree.addAll(strCollection);
	}
	
	@Test(expected = NullPointerException.class)
	public void addAllCharTest(){
		charCollection.add('v');
		charCollection.add(null);
		charTree.addAll(charCollection);
	}
	
	@Test
	public void addAllNonEmptyTest(){
		intCollection.add(2);
		intCollection.add(3);
		intCollection.add(1);
		intCollection.add(6);
		intCollection.add(4);

		intTree.add(5);
		intTree.add(8);
		intTree.add(3);
		intTree.add(2);
		intTree.add(10);
		
		intTree.addAll(intCollection);
		ArrayList<Integer> result1 = new ArrayList<Integer>() ;
		result1.add(1);
		result1.add(2);
		result1.add(3);
		result1.add(4);
		result1.add(5);
		result1.add(6);
		result1.add(8);
		result1.add(10);
		assertEquals(result1, intTree.toArrayList());
		assertEquals(8,intTree.size());
		
		strCollection.add("ac");
		strCollection.add("ad");
		strCollection.add("db");
		strTree.add("bc");
		strTree.add("yy");
		assertEquals(2,strTree.size());
		strTree.addAll(strCollection);
		assertEquals(5,strTree.size());
		ArrayList<String> result2 = new ArrayList<String>() ;
		result2.add("ac");
		result2.add("ad");
		result2.add("bc");
		result2.add("db");
		result2.add("yy");
		assertEquals(result2, strTree.toArrayList());
		
		charCollection.add('s');
		charCollection.add('a');
		charTree.add('f');
		assertEquals(1,charTree.size());
		charTree.addAll(charCollection);
		assertEquals(3,charTree.size());
		ArrayList<Character> result3 = new ArrayList<Character>() ;
		result3.add('a');
		result3.add('f');
		result3.add('s');
		assertEquals(result3, charTree.toArrayList());
		
	}
	
	@Test
	public void addAllEmptyTest(){
		intCollection.add(2);
		intCollection.add(3);
		intCollection.add(1);
		intCollection.add(6);
		intCollection.add(4);
		assertEquals(0,intTree.size());
		intTree.addAll(intCollection);
		ArrayList<Integer> result = new ArrayList<Integer>() ;
		result.add(1);
		result.add(2);
		result.add(3);
		result.add(4);
		result.add(6);
		assertEquals(result, intTree.toArrayList());
		assertEquals(5,intTree.size());
		
		strCollection.add("ac");
		strCollection.add("ad");
		strCollection.add("db");
		assertEquals(0,strTree.size());
		strTree.addAll(strCollection);
		ArrayList<String> result1 = new ArrayList<String>() ;
		result1.add("ac");
		result1.add("ad");
		result1.add("db");
		assertEquals(result1, strTree.toArrayList());
		assertEquals(3,strTree.size());
		
		charCollection.add('s');
		charCollection.add('a');
		charCollection.add('d');
		assertEquals(0,charTree.size());
		charTree.addAll(charCollection);
		ArrayList<Character> result3 = new ArrayList<Character>() ;
		result3.add('a');
		result3.add('d');
		result3.add('s');
		assertEquals(result3, charTree.toArrayList());
		assertEquals(3,charTree.size());
	}

	@Test
	public void addAllEmptyDuplicateTest(){
		intCollection.add(2);
		intCollection.add(3);
		intCollection.add(1);
		intCollection.add(6);
		intCollection.add(4);

		intTree.add(2);
		intTree.add(5);
		assertEquals(2,intTree.size());
		intTree.addAll(intCollection);
		ArrayList<Integer> result = new ArrayList<Integer>() ;
		result.add(1);
		result.add(2);
		result.add(3);
		result.add(4);
		result.add(5);
		result.add(6);
		assertEquals(result, intTree.toArrayList());
		assertEquals(6,intTree.size());
		
		strCollection.add("ac");
		strCollection.add("ad");
		strCollection.add("db");
		strTree.add("ad");
		assertEquals(1,strTree.size());
		strTree.addAll(strCollection);
		ArrayList<String> result1 = new ArrayList<String>() ;
		result1.add("ac");
		result1.add("ad");
		result1.add("db");
		assertEquals(result1, strTree.toArrayList());
		assertEquals(3,strTree.size());
		
		charCollection.add('s');
		charCollection.add('a');
		charCollection.add('d');
		charTree.add('a');
		assertEquals(1,charTree.size());
		charTree.addAll(charCollection);
		ArrayList<Character> result3 = new ArrayList<Character>() ;
		result3.add('a');
		result3.add('d');
		result3.add('s');
		assertEquals(result3, charTree.toArrayList());
		assertEquals(3,charTree.size());
		
	}
	
	@Test
	public void clearNonEmptyTest(){ 
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		intTree.clear();
		assertTrue(intTree.isEmpty());
		
		strTree.add("hello");
		strTree.add("hi");
		strTree.clear();
		assertTrue(strTree.isEmpty());
		
		charTree.add('d');
		charTree.clear();
		assertTrue(charTree.isEmpty());
	}
	
	@Test
	public void clearNonEmptyAndAddTest(){ 
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		intTree.clear();
		assertTrue(intTree.isEmpty());
		intTree.add(3);
		intTree.add(4);
		assertEquals(2, intTree.size());
		
		strTree.add("hello");
		strTree.add("hi");
		strTree.clear();
		assertTrue(strTree.isEmpty());
		strTree.add("morning");
		assertFalse(strTree.isEmpty());
		assertEquals(1, strTree.size());
		
		charTree.add('d');
		charTree.clear();
		assertTrue(charTree.isEmpty());
		charTree.add('a');
		assertFalse(charTree.isEmpty());
		assertEquals(1, charTree.size());	
	}
	
	@Test
	public void clearEmptyTest(){
		intTree.clear();
		assertTrue(intTree.isEmpty());
		strTree.clear();
		assertTrue(strTree.isEmpty());
		charTree.clear();
		assertTrue(charTree.isEmpty());
	}

	@Test(expected =  NoSuchElementException.class)
	public void firstExceptionTest(){
		intTree.first();
		strTree.first();
		charTree.first();
	}
	
	@Test
	public void firstTest(){
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		int integer = intTree.first();
		assertEquals(1,integer);
		
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		String string = strTree.first();
		assertEquals("aa",string);
		
		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		char charator = charTree.first();
		assertEquals('a',charator);
	}
	
	@Test(expected =  NoSuchElementException.class)
	public void lastExceptionTest(){
		intTree.last();
		strTree.last();
		charTree.last();
	}
	
	@Test
	public void lastTest(){
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		int integer = intTree.last();
		assertEquals(4,integer);
		
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		String string = strTree.last();
		assertEquals("yy",string);
		
		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		char charator = charTree.last();
		assertEquals('z',charator);
	}
	@Test
	public void containsNonEmptyTest(){
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		assertTrue(intTree.contains(3));
		assertFalse(intTree.contains(12));
		
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		assertTrue(strTree.contains("ad"));
		assertFalse(strTree.contains("hello"));
		
		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		assertTrue(charTree.contains('x'));
		assertFalse(charTree.contains('c'));
	}
	
	@Test
	public void containsEmptyTest(){
		assertFalse(intTree.contains(3));
		assertFalse(strTree.contains("hello"));
		assertFalse(charTree.contains('a'));
	}
	
	@Test(expected = NullPointerException.class)
	public void containsEmptyExceptionTest(){
		assertFalse(intTree.contains(null));
		assertFalse(strTree.contains(null));
		assertFalse(charTree.contains(null));
	}
	
	@Test
	public void containsAllNonEmptyTest1(){
		intCollection.add(2);
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		assertFalse(intTree.containsAll(intCollection));
		
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		strCollection.add("ab");
		strCollection.add("aa");
		assertTrue(strTree.containsAll(strCollection));
		
		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		charCollection.add('c');
		charCollection.add('y');
		assertFalse(charTree.containsAll(charCollection));	
	}

	@Test
	public void containsAllEmptyTest(){
		assertTrue(intTree.containsAll(intCollection));
		intCollection.add(2);
		intCollection.add(3);
		intCollection.add(5);
		intCollection.add(7);
		assertFalse(intTree.containsAll(intCollection));
		
		assertTrue(strTree.containsAll(strCollection));
		strCollection.add("ab");
		strCollection.add("aa");
		assertFalse(strTree.containsAll(strCollection));
		
		assertTrue(charTree.containsAll(charCollection));
		charCollection.add('c');
		charCollection.add('y');
		assertFalse(charTree.containsAll(charCollection));
	}
	
	@Test
	public void sizeTest(){
		assertEquals(0,intTree.size());
		assertEquals(0,strTree.size());
		assertEquals(0,charTree.size());
		
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		assertEquals(3,intTree.size());
		
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		assertEquals(6,strTree.size());
		
		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		assertEquals(8,charTree.size());
	}
	
	@Test
	public void toArrayListTest(){
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		assertEquals(intArray,intTree.toArrayList()); 
		
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);

		intArray.add(1);
		intArray.add(3);
		intArray.add(4);
		assertEquals(intArray,intTree.toArrayList()); 
		
		ArrayList<String> strArray = new ArrayList<String>();
		assertEquals(strArray,strTree.toArrayList()); 
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		
		strArray.add("aa");
		strArray.add("ab");
		strArray.add("ad");
		strArray.add("bc");
		strArray.add("ft");
		strArray.add("yy");
		assertEquals(strArray,strTree.toArrayList()); 
		
		ArrayList<Character> charArray = new ArrayList<Character>();
		assertEquals(charArray,charTree.toArrayList()); 
		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		
		charArray.add('a');
		charArray.add('b');
		charArray.add('d');
		charArray.add('r');
		charArray.add('v');
		charArray.add('x');
		charArray.add('y');
		charArray.add('z');
		assertEquals(charArray,charTree.toArrayList()); 
	}
	
	@Test(expected = NullPointerException.class)
	public void removeException(){
		intTree.contains(null);
		strTree.contains(null);
		charTree.contains(null);
	}
	
	@Test
	public void removeSizezero(){
		assertFalse(intTree.contains(2));
		assertFalse(strTree.contains("hi"));
		assertFalse(charTree.contains('s'));
	}
	
	@Test
	public void removeSizeOne(){
		intTree.add(1);
		assertFalse(intTree.remove(2));
		assertFalse(intTree.contains(2));
		assertEquals(1, intTree.size());
		
		assertTrue(intTree.contains(1));
		assertTrue(intTree.remove(1));
		assertFalse(intTree.contains(1));
		assertEquals(0, intTree.size());
		
		strTree.add("hi");
		assertFalse(strTree.remove("hii"));
		assertFalse(strTree.contains("hii"));
		assertEquals(1, strTree.size());
		
		assertTrue(strTree.contains("hi"));
		assertTrue(strTree.remove("hi"));
		assertFalse(strTree.contains("hi"));
		assertEquals(0, strTree.size());
		
		charTree.add('s');
		assertFalse(charTree.remove('a'));
		assertFalse(charTree.contains('a'));
		assertEquals(1, charTree.size());
		
		assertTrue(charTree.contains('s'));
		assertTrue(charTree.remove('s'));
		assertFalse(charTree.contains('s'));
		assertEquals(0, charTree.size());
	}
	@Test
	public void removeLeaf(){
		intTree.add(1);
		intTree.add(3);
		intTree.add(4);
		assertTrue(intTree.remove(4));
		assertFalse(intTree.contains(4));
		assertEquals(2, intTree.size());
		int first = intTree.first();
		int last = intTree.last();
		assertEquals(1, first);
		assertEquals(3, last);
		
		strTree.add("ab");
		strTree.add("bc");
		strTree.add("aa");
		strTree.add("ad");
		strTree.add("ft");
		strTree.add("yy");
		assertEquals(6, strTree.size());
		assertTrue(strTree.remove("aa"));
		assertFalse(strTree.remove("az"));
		assertEquals(5, strTree.size());
		
		charTree.add('a');
		charTree.add('d');
		charTree.add('x');
		charTree.add('z');
		charTree.add('y');
		charTree.add('b');
		charTree.add('v');
		charTree.add('r');
		assertEquals(8, charTree.size());
		assertTrue(charTree.remove('d'));
		assertFalse(charTree.remove('c'));
		assertTrue(charTree.remove('v'));
		assertEquals(6, charTree.size());
	}
	
	@Test
	public void getRoot(){
		intTree.add(10);
		intTree.add(9);
		intTree.add(8);
		intTree.add(7);
		intTree.add(6);
		intTree.add(5);
		intTree.add(4);
		
		assertTrue(intTree.remove(10));
		int root = intTree.getRoot();
		assertEquals(9,root);
		
		assertTrue(intTree.remove(9));
		int root1 = intTree.getRoot();
		assertEquals(8,root1);

	}
	
	@Test
	public void removeOneLeftChild(){
		intTree.add(6);
		intTree.add(4);
		intTree.add(3);
		intTree.add(10);
		intTree.add(7);
		intTree.add(12);
		
		assertEquals(6, intTree.size());
		assertTrue(intTree.remove(4));
		assertFalse(intTree.contains(4));
		assertEquals(5, intTree.size());
		int first = intTree.first();
		int last = intTree.last();
		assertEquals(3, first);
		assertEquals(12, last);
	}
	
	@Test
	public void removeOneRightChild(){
		intTree.add(6);
		intTree.add(4);
		intTree.add(5);
		intTree.add(10);
		intTree.add(7);
		intTree.add(12);
		
		assertEquals(6, intTree.size());
		assertTrue(intTree.remove(4));
		assertFalse(intTree.contains(4));
		assertEquals(5, intTree.size());
		int first = intTree.first();
		int last = intTree.last();
		assertEquals(5, first);
		assertEquals(12, last);
	}
	
	@Test
	public void removeTwoChildrenNoRightLeft(){
		intTree.add(6);
		intTree.add(4);
		intTree.add(3);
		intTree.add(10);
		intTree.add(7);
		intTree.add(12);
		assertEquals(6, intTree.size());
		assertTrue(intTree.remove(10));
		assertFalse(intTree.contains(10));
		assertEquals(5, intTree.size());
		int first = intTree.first();
		int last = intTree.last();
		assertEquals(3, first);
		assertEquals(12, last);
	}
	
	@Test
	public void removeTwoChildrenHaveRightLeft(){
		intTree.add(6);
		intTree.add(4);
		intTree.add(3);
		intTree.add(10);
		intTree.add(7);
		intTree.add(8);
		intTree.add(12);
		assertEquals(7, intTree.size());
		assertTrue(intTree.remove(6));
		assertFalse(intTree.contains(6));
		assertEquals(6, intTree.size());
		int first = intTree.first();
		int last = intTree.last();
		assertEquals(3, first);
		assertEquals(12, last);
		int root = intTree.getRoot();
		assertEquals(7,root);
	}
	
	@Test
	public void removeAllBasic(){
		intTree.add(6);
		intTree.add(4);
		intTree.add(3);
		intTree.add(10);
		intTree.add(7);
		intTree.add(8);
		intTree.add(12);
		
		intCollection.add(4);
		intCollection.add(3);
		intCollection.add(8);
		intCollection.add(1);
		assertTrue(intTree.removeAll(intCollection));
		assertEquals(4, intTree.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void removeAllwithNull(){
		intTree.add(6);
		intTree.add(4);
		intTree.add(3);
		intTree.add(10);
		intTree.add(7);
		intTree.add(8);
		intTree.add(12);
		
		intCollection.add(4);
		intCollection.add(3);
		intCollection.add(8);
		intCollection.add(null);
		intTree.removeAll(intCollection);
	}
}
