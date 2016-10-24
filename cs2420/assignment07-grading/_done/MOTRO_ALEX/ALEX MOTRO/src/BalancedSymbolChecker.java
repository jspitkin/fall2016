package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Alex Motro
 */
public class BalancedSymbolChecker {
	
	public static void main(String[] args){
		try {
			System.out.println("1. " + checkFile("Class1.java"));
			System.out.println("2. " + checkFile("Class2.java"));
			System.out.println("3. " + checkFile("Class3.java"));
			System.out.println("4. " + checkFile("Class4.java"));
			System.out.println("5. " + checkFile("Class5.java"));
			System.out.println("6. " + checkFile("Class6.java"));
			System.out.println("7. " + checkFile("Class7.java"));
			System.out.println("8. " + checkFile("Class8.java"));
			System.out.println("9. " + checkFile("Class9.java"));
			System.out.println("10. " + checkFile("Class10.java"));
			System.out.println("11. " + checkFile("Class11.java"));
			System.out.println("12. " + checkFile("Class12.java"));
			System.out.println("13. " + checkFile("Class13.java"));
			System.out.println("14. " + checkFile("Class14.java"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public static String checkFile(String filename) throws FileNotFoundException {
		LinkedListStack<String> stack = new LinkedListStack<String>();  


		try{
			Scanner input = new Scanner(new File(filename));
		}
		catch (FileNotFoundException e){
		}

		Scanner input = new Scanner(new File(filename));

		input.useDelimiter("");

		int currentColumn = 1;
		int currentRow = 0;
		boolean inAComment = false;
		boolean inQuotes = false;
		String next;

		while (input.hasNext()){
			next = input.next();
			currentRow ++;
			ArrayList<String> line = new ArrayList<String>();
			while (!next.contains("\n")){
				line.add(next);
				if (!input.hasNext() || next.contains("\n") ){
					break;
				}else {
					next = input.next();
				}
			}


			for (int i = 0; i < line.size(); i++){
				currentColumn = i+1;
				if (line.get(i).contains("\"") || line.get(i).contains("\'") ){
					inQuotes = !inQuotes;
				}
				if (i+1 < line.size()){
					if ((line.get(i).contains("/") && line.get(i+1).contains("/")) && inAComment == false && inQuotes == false){
						break;
					}
				}
				if (line.get(i).contains("/") && line.get(i+1).contains("*") && inAComment == false && inQuotes == false){
					stack.push(line.get(i));
					stack.push(line.get(i+1));
					inAComment = true;
					continue;
				}
				if (line.get(i).contains("\\")){
					i++;
				}
				else if ((line.get(i).contains("{") || line.get(i).contains("[" ) || line.get(i).contains("(")) && stack.peek() != "*" && inAComment == false  && inQuotes == false){
					stack.push(line.get(i));
				}
				else if ((line.get(i).contains("}")|| line.get(i).contains("]" ) || line.get(i).contains(")") || line.get(i).contains("*")) && inQuotes == false){
					String expected = stack.pop();
					if (expected == null){
						expected = "";
					}
					switch (expected){
					case "{":
						if (!line.get(i).contains("}")){
							return unmatchedSymbol(currentRow, currentColumn, line.get(i).charAt(0), "}".charAt(0));
						}
						break;
					case "[":
						if (!line.get(i).contains("]")){
							return unmatchedSymbol(currentRow, currentColumn, line.get(i).charAt(0), "]".charAt(0));
						}
						break;
					case "(":
						if (!line.get(i).contains(")")){
							return unmatchedSymbol(currentRow, currentColumn, line.get(i).charAt(0), ")".charAt(0));
						}
						break;
					case "":
						return unmatchedSymbol(currentRow, currentColumn, line.get(i).charAt(0), ' ');
					case "*":
						stack.push("*");
						if (line.get(i).contains("*") && line.get(i+1).contains("/")){
							stack.pop();
							stack.pop();
							inAComment = false;
							i++;
						}
						break;
					}
				}
			}

		}

		input.close();

		if (stack.isEmpty()){
			return allSymbolsMatch(); 
		}


		String expected = stack.pop();
		switch (expected){
		case "{":
			return unmatchedSymbolAtEOF("}".charAt(0));
		case "[":
			return unmatchedSymbolAtEOF("]".charAt(0));
		case "(":
			return unmatchedSymbolAtEOF(")".charAt(0));
		case "*":
			return unfinishedComment();
		}

		return "Something went wrong";
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}