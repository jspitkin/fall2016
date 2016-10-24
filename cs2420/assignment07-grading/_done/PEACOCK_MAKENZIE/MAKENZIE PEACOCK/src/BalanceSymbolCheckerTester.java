package assignment07;

import java.io.FileNotFoundException;

/**
 * A tester class for the BalanceSymbolChecker class.
 * 
 * @author Makenzie Peacock (u0873188)
 */
public class BalanceSymbolCheckerTester {
	public static void main(String args[]){
		BalancedSymbolChecker symbolCheck = new BalancedSymbolChecker();
		
		try{
			System.out.println(symbolCheck.checkFile("Class1.java"));
			System.out.println(symbolCheck.checkFile("Class2.java"));
			System.out.println(symbolCheck.checkFile("Class3.java"));
			System.out.println(symbolCheck.checkFile("Class4.java"));
			System.out.println(symbolCheck.checkFile("Class5.java"));
			System.out.println(symbolCheck.checkFile("Class6.java"));
			System.out.println(symbolCheck.checkFile("Class7.java"));
			System.out.println(symbolCheck.checkFile("Class8.java"));
			System.out.println(symbolCheck.checkFile("Class9.java"));
			System.out.println(symbolCheck.checkFile("Class10.java"));
			System.out.println(symbolCheck.checkFile("Class11.java"));
			System.out.println(symbolCheck.checkFile("Class12.java"));
			System.out.println(symbolCheck.checkFile("Class13.java"));
			System.out.println(symbolCheck.checkFile("Class14.java"));
			
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
}
