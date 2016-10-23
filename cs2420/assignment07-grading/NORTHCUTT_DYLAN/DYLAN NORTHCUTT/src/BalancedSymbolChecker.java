package assignment07;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author ??
 */
public class BalancedSymbolChecker {

	@SuppressWarnings("rawtypes")
	LinkedListStack stack = new LinkedListStack();

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked" })
	public String checkFile(String filename) throws IOException {

		boolean comment = false;
		boolean string = false;

		try (Scanner fileIn = new Scanner(new File(filename))) {

			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String content = fileIn.nextLine();

		
// ------------------------------comments-------------------------------------------

				for (int i = 0; i < content.length(); i++) {
					char current = content.charAt(i);

					if (current == '/' && (content.length() > (i+1))&& content.charAt(i + 1) == '/' && !comment && !string) {
						i = content.length();
					}

					else if (current == '/' && (content.length() > (i+1))&& content.charAt(i + 1) == '*' && !comment&& !string) {
						comment = true;
						stack.push('/');
						i++;
					}

					else if (current == '*' && content.charAt(i + 1) == '/') {
						comment = false;
						char got = (char) stack.pop();
						if (!(got == '/')) {
							stack.clear();
							return unmatchedSymbol(lineNum, i+1, current, inverse(got));
						}

					}
					
					else if((current == '"' || current == '\'') && !comment){
						
						if(content.charAt(i-1) == '\\' && string)
							string = true;
						else if(string == false){
							string = true;
						}
						else{
							string = false;
						}
					}
					
					
					
					
					

// ----------------------------symbols---------------------------------------------------------

					else if (current == '(' && !comment&& !string) {
						stack.push('(');
					} 
					
					else if (current == '{' && !comment&& !string) {
						stack.push('{');
					} 
					
					else if (current == '[' && !comment&& !string) {
						stack.push('[');
					}

					
					
					
					else if (current == ')' && !comment&& !string) {
						char got = (char) stack.pop();
						if (!(got == '(')) {
							stack.clear();
							return unmatchedSymbol(lineNum, i+1, current, inverse(got));
						}
					}

					
					
					
					else if (current == '}' && !comment&& !string) {
						char got = (char) stack.pop();
						if (!(got == '{')) {
							stack.clear();
							return unmatchedSymbol(lineNum, i+1,current, inverse(got) );
						}
					}

					
					
					
					else if (current == ']' && !comment&& !string) {
						char got = (char) stack.pop();
						if (!(got == '[')) {
							stack.clear();
							return unmatchedSymbol(lineNum, i+1, current,inverse(got));
						}
					}

				}
				
				lineNum++;
			}
}
			if (!stack.isEmpty()) {
				char got = (char) stack.pop();

				if (got == '/') {
					stack.clear();
					return unfinishedComment();
				} else {

					if (got == '(') {
						stack.clear();
						return unmatchedSymbolAtEOF(')');
					}
					if (got == '[') {
						stack.clear();
						return unmatchedSymbolAtEOF(']');
					}

					if (got == '{') {
						stack.clear();
						return unmatchedSymbolAtEOF('}');
					}
				}

			}
	
			return allSymbolsMatch();
			
		
	}
	
	
	
	
	/**
	 * Returns the inverted symbol
	 */
	private char inverse(char in){
		if (in == '(') {
			return ')';
		} 
		
		else if (in == '{') {
			return '}';
		} 
		
		else if (in== '[') {
			return ']';
		}
		return ' ';
	}
	
	

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected "
				+ symbolExpected + ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}