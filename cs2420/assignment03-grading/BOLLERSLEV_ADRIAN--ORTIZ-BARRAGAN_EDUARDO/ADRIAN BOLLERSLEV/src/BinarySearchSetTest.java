/**
@Author Adrian
*/
package assignment03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BinarySearchSetTest {

	BinarySearchSet<Integer> tester = new BinarySearchSet<>();	
	BinarySearchSet<String> testerString = new BinarySearchSet<>();	
	
	ReverseComparator comp = new ReverseComparator();
	
	BinarySearchSet<Integer> testercomp = new BinarySearchSet<>(comp);	

	@Before
	public void setup() {
		
	}
	@Test
	public void testerReverse() {
		BinarySearchSet<Integer> testercomp = new BinarySearchSet<>(comp);	
		testercomp.add(3);
		
		
		System.out.println(testercomp.first());		
	}
		
	@Test
	public void containsTest1() {
		tester.add(3);
		tester.remove(3);
		Assert.assertEquals(false,tester.contains(3));
		
	}
	@Test
	public void containsTest2() {
		tester = new BinarySearchSet();
		Assert.assertEquals(true,tester.contains(null));
	}
	@Test
	public void containsTest3() {
	
		Assert.assertEquals(false,tester.contains(3));}
	@Test
	public void containsTest4() {
		
		tester.add(3);
		Assert.assertEquals(true,tester.contains(3));}
	@Test
	public void clearTest1() {
		tester.add(3);
		tester.clear();
		
		Assert.assertEquals(false,tester.contains(3));}
	@Test
	public void clearTest2() {
		
		tester.clear();
		Assert.assertEquals(false,tester.contains(3));}
	@Test
	public void clearTest3() {
		tester.add(3);
		tester.add(4);
		tester.add(5);
		tester.clear();
		
		Assert.assertEquals(false,tester.contains(3));}
	@Test
	public void firstTest1() {
		BinarySearchSet tester = new BinarySearchSet();
		tester.add(3);
		tester.first();
		
		Assert.assertEquals(3,tester.first());}
	
	@Test
	public void firstTest2() {
		BinarySearchSet tester = new BinarySearchSet();
		tester.add(1);
		tester.add(56);
		tester.add(2);
		tester.first();
		Assert.assertEquals(1,tester.first());
	
}
	@Test(expected=NoSuchElementException.class)
	public void firstTest3() {
		BinarySearchSet tester = new BinarySearchSet();
		tester.first();
		
		Assert.assertEquals(3,tester.first());}
	
	@Test(expected=NoSuchElementException.class)
	public void lastTest1() {
		BinarySearchSet tester = new BinarySearchSet();
		tester.last();
		Assert.assertEquals(3,tester.first());
	
}

	@Test
	public void LastTest2() {
		tester.add(3);
		tester.add(4);
		Assert.assertTrue(tester.contains(3));
	
}
	@Test
	public void removeTest1() {
		tester.add(3);
		tester.remove(3);
		Assert.assertEquals(false,tester.contains(3) );
	
}
	
	@Test
	public void removeTest2() {
	tester.remove(3);
		Assert.assertEquals(false,tester.remove(3));
	
}
	@Test
	public void removeTest3() {
		tester.add(4);
		tester.add(1);
		tester.add(100);
		tester.remove(4);
		Assert.assertEquals(false,tester.contains(4));
	
}
	@Test
	public void addTest1() {
		tester.add(3);
		Assert.assertEquals(true,tester.contains(3));	
	
}

	@Test
	public void addTest2() {
		testerString.add("hi"	);
		testerString.add("hello");
		Assert.assertEquals(true,testerString.contains("hello"));	
	}

	@Test
	public void addTest3() {
		testerString.add("hi");
		testerString.add("hello"); 
		
		Assert.assertEquals(false,testerString.add("hi"));
	}
	@Test
	public void sizeTest1() {
		Assert.assertEquals(0,tester.size());	
	
}
	@Test
	public void sizeTest2() {
		tester.add(3);
		Assert.assertEquals(1,tester.size());	
	}

	@Test
	public void sizeTest3() {
		tester.add(3);
		tester.add(5);
		tester.add(9);
		Assert.assertEquals(3,tester.size());	
	}
	@Test
	public void itRemoveTest1 () {
		tester.add(3);
		tester.itRemove(0);
		Assert.assertEquals(false,tester.contains(3));
	}
	@Test
	public void itRemoveTest2 () {
		tester.itRemove(0);
		Assert.assertEquals(false,tester.contains(3));
	}
	@Test
	public void itRemoveTest3() {
		tester.itRemove(-8);
		Assert.assertEquals(false,tester.contains(3));
	}	
	@Test
	public void itRemoveTest4() {
		tester.itRemove(10);
		Assert.assertEquals(false,tester.contains(3));
	}	
	@Test
	public void isEmptyTest1() {
		tester.add(3);
		Assert.assertEquals(false,tester.isEmpty());
	}	
	@Test
	public void isEmptyTest2() {
	
		Assert.assertEquals(true, tester.isEmpty());
} 
	@Test
	public void iteratorTest1() {
		tester.add(3);
		tester.add(4);
		tester.add(5);
		
		Iterator<Integer> it = tester.iterator();
		while (it.hasNext())
			tester.remove(it.next());
		Assert.assertEquals(0,tester.size());
		}
		

	@Test (expected=NoSuchElementException.class)
	public void iteratorTest2() {
		Iterator<Integer> it = tester.iterator();
		for(int i = 0; i <3; i++)
			tester.remove(it.next());
		
	}
	@Test (expected = IllegalStateException.class)
	public void iteratorTest3() {
		
		Iterator<Integer> it = tester.iterator();
		tester.add(3);
		while(it.hasNext())
			it.remove();
		
	}	
	@Test (expected = NullPointerException.class)
	public void testerReverse1() {
		BinarySearchSet<Integer> testercomp = new BinarySearchSet<>(comp);	
		testercomp.add(3);
		testercomp.add(2);
		
	}
	@Test
	public void containsAllTest1() {
		
	Collection<String> c = Arrays.asList("one","two","three");

	testerString.add("one");
	testerString.add("two");
	testerString.add("three");

	 
	Assert.assertEquals(true, testerString.containsAll(c));
} 
	@Test
	public void containsAllTest2() {
		
	Collection<String> c = Arrays.asList("one","two","three");
 
	Assert.assertEquals(false, testerString.containsAll(c));
}
	@Test
	public void addAllTest1() {
		
	Collection<Integer> c = Arrays.asList(1,2,3);

	tester.add(1);
	tester.add(2);
	tester.add(3);

	 
	Assert.assertEquals(true, tester.containsAll(c));
}
	@Test
	public void addAllTest2() {
		
	Collection<Integer> c = Arrays.asList(1,2,3);

	 
	Assert.assertEquals(false, tester.containsAll(c));
} 
	@Test
	public void addAllTest4() {
		
	Collection<Integer> c = Arrays.asList(1,2,3);

	tester.add(1);
	tester.add(2);
	tester.add(4);	 
	Assert.assertEquals(false, tester.containsAll(c));
} 
}