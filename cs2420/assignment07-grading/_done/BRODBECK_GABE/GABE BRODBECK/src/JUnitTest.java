package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;


/**
 * Class used to test BalancedSymbolChecker
 * 
 * @author Gabe Brodbeck u0847035
 */
public class JUnitTest {
	BalancedSymbolChecker checker= new BalancedSymbolChecker();
//	@Test
	//I should say incase a TA looks at this and finds it doesn't run that in the discussion it said to leave out all of the files
	//which did not compile so I did not include Class1 and the like in my submission
	public void testClass1() {
		try{
		String got=checker.checkFile("Class1.java");
		Assert.assertEquals(got,"ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.");
		System.out.println("got to run this 1");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class1.java");
		}
	}
	@Test
	public void testClass2() {
		try{
		String got=checker.checkFile("Class2.java");
		Assert.assertEquals(got,"ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.");
		System.out.println("got to run this 2");
		} 
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class2.java");
		}
	}
	@Test
	public void testClass3() {
		try{
		String got=checker.checkFile("Class3.java");
		Assert.assertEquals(got,"No errors found. All symbols match.");
		System.out.println("got to run this 3");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class3.java");
		}
	}
	@Test
	public void testClass4() {
		try{
		String got=checker.checkFile("Class4.java");
		Assert.assertEquals(got,"ERROR: File ended before closing comment.");
		System.out.println("got to run this 4");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class4.java");
		}
	}
	@Test
	public void testClass5() {
		try{
		String got=checker.checkFile("Class5.java");
		Assert.assertEquals(got,"ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.");
		System.out.println("got to run this 5");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class5.java");
		}
	}
	@Test
	public void testClass6() {
		try{
		String got=checker.checkFile("Class6.java");
		Assert.assertEquals(got,"No errors found. All symbols match.");
		System.out.println("got to run this 6");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class6.java");
		}
	}
	@Test
	public void testClass7() {
		try{
		String got=checker.checkFile("Class7.java");
		Assert.assertEquals(got, "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.");
		System.out.println("got to run this 7");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class7.java");
		}
	}
	@Test
	public void testClass8() {
		try{
		String got=checker.checkFile("Class8.java");
		Assert.assertEquals(got,"ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.");
		System.out.println("got to run this 8");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class8.java");
		}
	}
	@Test
	public void testClass9() {
		try{
		String got=checker.checkFile("Class9.java");
		Assert.assertEquals(got,"ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.");
		System.out.println("got to run this 9");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class9.java");
		}
	}
	@Test
	public void testClass10() {
		try{
		String got=checker.checkFile("Class10.java");
		Assert.assertEquals(got, "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.");
		System.out.println("got to run this 10");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class10.java");
		}
	}
	@Test
	public void testClass11() {
		try{
		String got=checker.checkFile("Class11.java");
		Assert.assertEquals(got, "ERROR: Unmatched symbol at the end of file. Expected }.");
		System.out.println("got to run this 11");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class11.java");
		}
	}
	@Test
	public void testClass12() {
		try{
		String got=checker.checkFile("Class12.java");
		Assert.assertEquals(got,"No errors found. All symbols match.");
		System.out.println("got to run this 12");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class12.java");
		}
	}
	@Test
	public void testClass13() {
		try{
		String got=checker.checkFile("Class13.java");
		Assert.assertEquals(got,"No errors found. All symbols match.");
		System.out.println("got to run this 13");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class13.java");
		}
	}
	@Test
	public void testClass14() {
		try{
		String got=checker.checkFile("Class14.java");
		Assert.assertEquals(got,"No errors found. All symbols match.");
		System.out.println("got to run this 14");
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find Class14.java");
		}
	}

}
