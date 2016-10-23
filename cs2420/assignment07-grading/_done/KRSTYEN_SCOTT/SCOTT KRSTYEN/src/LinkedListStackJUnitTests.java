


package assignment07;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Scott Krstyen U0760822
 */


public class LinkedListStackJUnitTests {
	
	BalancedSymbolChecker bsc = new BalancedSymbolChecker();
	LinkedListStack<Integer> linkedLS = new LinkedListStack<Integer>();
	String result1 = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
	String result2 = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
	String result3 = "No errors found. All symbols match.";
	String result4 = "ERROR: File ended before closing comment.";
	String result5 = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
	String result6 = "No errors found. All symbols match.";
	String result7 = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
	String result8 = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
	String result9 = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
	String result10 = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
	String result11 = "ERROR: Unmatched symbol at the end of file. Expected }.";
	String result12 = "No errors found. All symbols match.";
	String result13 = "No errors found. All symbols match.";
	String result14 = "No errors found. All symbols match.";
	String result15 = "No errors found. All symbols match.";
	
	@Test
	public void LinkedListStackClear(){
		linkedLS.push(5);
		linkedLS.push(4);
		linkedLS.clear();
		Assert.assertTrue(linkedLS.isEmpty());
	}
	
	@Test
	public void LinkedListStackPushAndPop(){
		linkedLS.push(5);
		Assert.assertEquals(5, (int) linkedLS.pop());
	}
	
	@Test
	public void LinkedListStackPush(){
		linkedLS.push(5);
		linkedLS.push(5);
		linkedLS.push(5);
		linkedLS.push(5);
		linkedLS.push(5);
		Assert.assertEquals(5, (int) linkedLS.size());
	}
	
	@Test
	public void LinkedListStackPeek(){
		linkedLS.push(5);
		linkedLS.push(4);
		linkedLS.push(2);
		linkedLS.push(1);
		linkedLS.push(3);
		Assert.assertEquals(3, (int) linkedLS.peek());
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass1() throws IOException{
		Assert.assertEquals(result1, bsc.checkFile("Class1.java"));
	}

	@Test
	public void BalancedSymbolCheckerOnClass2() throws IOException{
		Assert.assertEquals(result2, bsc.checkFile("Class2.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass3() throws IOException{
		Assert.assertEquals(result3, bsc.checkFile("Class3.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass4() throws IOException{
		Assert.assertEquals(result4, bsc.checkFile("Class4.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass5() throws IOException{
		Assert.assertEquals(result5, bsc.checkFile("Class5.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass6() throws IOException{
		Assert.assertEquals(result6, bsc.checkFile("Class6.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass7() throws IOException{
		Assert.assertEquals(result7, bsc.checkFile("Class7.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass8() throws IOException{
		Assert.assertEquals(result8, bsc.checkFile("Class8.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass9() throws IOException{
		Assert.assertEquals(result9, bsc.checkFile("Class9.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass10() throws IOException{
		Assert.assertEquals(result10, bsc.checkFile("Class10.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass11() throws IOException{
		Assert.assertEquals(result11, bsc.checkFile("Class11.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass12() throws IOException{
		Assert.assertEquals(result12, bsc.checkFile("Class12.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass13() throws IOException{
		Assert.assertEquals(result13, bsc.checkFile("Class13.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass14() throws IOException{
		Assert.assertEquals(result14, bsc.checkFile("Class14.java"));
	}
	
	@Test
	public void BalancedSymbolCheckerOnClass15() throws IOException{
		Assert.assertEquals(result15, bsc.checkFile("Class15.txt"));
	}
	
	
}
