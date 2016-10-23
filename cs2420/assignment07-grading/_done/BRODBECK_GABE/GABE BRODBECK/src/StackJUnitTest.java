package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Class used to test the features of LinkedListStack
 * 
 * @author Gabe Brodbeck u0847035
 */
public class StackJUnitTest {

	LinkedListStack<String> stack=new LinkedListStack<String>();
	
	@Before
	public void setUp(){
		stack.clear();
	}
	@Test
	public void testPush() {
	stack.push(new String("first in"));
	Assert.assertEquals("first in", stack.peek());
	stack.push(new String("2nd in"));
	stack.push(new String("3rd in"));
	Assert.assertEquals("3rd in", stack.peek());
	}
	@Test
	public void testPeek(){
		stack.push(new String("first in"));
		String hold=stack.peek();
		Assert.assertEquals(stack.pop(), hold);
		stack.push(new String("2nd in"));
		stack.push(new String("3rd in"));
		hold=stack.peek();
		Assert.assertEquals(stack.pop(), hold);
	}
	@Test
	public void testEmpty(){
		assertTrue(stack.isEmpty());
		stack.push("First in");
		assertFalse(stack.isEmpty());
	}
	@Test
	public void testPop(){
		stack.push(new String("first in"));
		Assert.assertEquals("first in", stack.pop());
		stack.push(new String("2nd in"));
		stack.push(new String("3rd in"));
		Assert.assertEquals("3rd in",stack.pop());
	}
	@Test 
	public void testPopWhenEmpty(){
		boolean failed=false;
		try{
			stack.pop();
		}
		catch(NoSuchElementException e){
			failed=true;
		}
		Assert.assertTrue(failed);
	}
	@Test
	public void testClear(){
		stack.push(new String("first in"));
		stack.clear();
		assertTrue(stack.isEmpty());
	}

}
