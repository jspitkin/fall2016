package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BalancedSymbolCheckerTester {

	public static void main(String[] args) throws FileNotFoundException {

		BalancedSymbolChecker bc = new BalancedSymbolChecker();
		System.out.println(bc.checkFile("Class1.java"));
		System.out.println(bc.checkFile("Class2.java"));
		System.out.println(bc.checkFile("Class3.java"));
		System.out.println(bc.checkFile("Class4.java"));
		System.out.println(bc.checkFile("Class5.java"));
		System.out.println(bc.checkFile("Class6.java"));
		System.out.println(bc.checkFile("Class7.java"));
		System.out.println(bc.checkFile("Class8.java"));
		System.out.println(bc.checkFile("Class9.java"));
		System.out.println(bc.checkFile("Class10.java"));
		System.out.println(bc.checkFile("Class11.java"));
		System.out.println(bc.checkFile("Class12.java"));
		System.out.println(bc.checkFile("Class13.java"));
		System.out.println(bc.checkFile("Class14.java"));
		System.out.println(bc.checkFile("Class15.java"));
	}

}
