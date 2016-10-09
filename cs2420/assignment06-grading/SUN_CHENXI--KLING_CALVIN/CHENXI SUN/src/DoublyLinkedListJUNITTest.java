package assignment06;
/**
 * @arthur Chenxi Sun
 * @Uid u0455173
 * */
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import assignment04.AnagramUtil;

public class DoublyLinkedListJUNITTest {

	DoublyLinkedList<String> abc=new DoublyLinkedList<String>();
	
	
	
	
	@Test
	public void AddFirstTest() {
		
	abc.addFirst("taylor");
	assertEquals(abc.getFirst(),"taylor");
	abc.addFirst("swift");
	assertEquals(abc.getFirst(),"swift");
	abc.addFirst("dakota");
	assertEquals(abc.getFirst(),"dakota");
	abc.addFirst("kim");
	assertEquals(abc.getFirst(),"kim");
	}
	
	
	@Test
	public void AddLastTest() {
		
	abc.addLast("taylor");
	assertEquals(abc.getLast(),"taylor");
	abc.addLast("swift");
	assertEquals(abc.getLast(),"swift");
	abc.addLast("dakota");
	assertEquals(abc.getLast(),"dakota");
	abc.addLast("kim");
	assertEquals(abc.getLast(),"kim");
	}
	
	
	@Test
	public void getFirstTest() {
		abc.addFirst("taylor");
		assertEquals(abc.getFirst(),"taylor");
		abc.addFirst("swift");
		assertEquals(abc.getFirst(),"swift");
		abc.addFirst("dakota");
		assertEquals(abc.getFirst(),"dakota");
		abc.addFirst("kim");
		assertEquals(abc.getFirst(),"kim");
		
		
	}
	
	
	@Test
	public void getLastTest() {
		abc.addLast("taylor");
		assertEquals(abc.getLast(),"taylor");
		abc.addLast("swift");
		assertEquals(abc.getLast(),"swift");
		abc.addLast("dakota");
		assertEquals(abc.getLast(),"dakota");
		abc.addLast("kim");
		assertEquals(abc.getLast(),"kim");
	
	
	}
	
	@Test
	public void getTest() {
		abc.addFirst("taylor");
		assertEquals(abc.get(0),"taylor");
		abc.addFirst("swift");
		assertEquals(abc.get(1),"taylor");
		abc.addLast("dakota");
		assertEquals(abc.get(2),"dakota");
		abc.addLast("kim");
		assertEquals(abc.get(3),"kim");
	}
	@Test
	public void removeFirstTest() {
		abc.addFirst("taylor");
		abc.removeFirst();
		assertEquals(true,abc.isEmpty());
		abc.addFirst("swift");
		abc.addFirst("taylor");
		abc.removeFirst();
		assertEquals("swift",abc.getFirst());
		abc.removeFirst();
		assertEquals(true,abc.isEmpty());
	
	
	}
	
	@Test
	public void removeLastTest() {
		abc.addLast("taylor");
		abc.removeLast();
		assertEquals(true,abc.isEmpty());
		abc.addLast("swift");
		abc.removeLast();
		assertEquals(true,abc.isEmpty());
		abc.addLast("taylor");
		abc.addLast("swift");
		abc.removeLast();
		assertEquals("taylor",abc.get(0));
	}
	
	@Test
	public void removeTest() {
		abc.add("taylor");
		abc.add("swift");
		abc.remove(0);
		assertEquals("swift",abc.get(0));
		abc.add("taylor");
		abc.remove(0);
		assertEquals("taylor",abc.get(0));
	}
	
	
	
	
	Iterator a2 = abc.iterator();
	@Test
	public void iteratorhasnextTest() {
		
	}
	
	@Test
	public void iteratornextTest() {
		abc.add("taylor");
		assertEquals("taylor",a2.next());
		abc.add("swift");
		assertEquals("swift",a2.next());
		abc.add("dakota");
		assertEquals("dakota",a2.next());
		abc.add("johnson");
		assertEquals("johnson",a2.next());
	}

	@Test
	public void iteratorremoveTest() {
		abc.add("taylor");
		a2.next();
		a2.remove();
		assertEquals(true,abc.isEmpty());
		abc.add("taylor");
		assertEquals("taylor",a2.next());
		a2.remove();
		assertEquals(true,abc.isEmpty());
		abc.add("dakota");
		a2.next();
		a2.remove();
		assertEquals(true,abc.isEmpty());
	}
	
	
	
	
}
