/**
 * Assignment 7 - Symbol Checker
 * Name: Dallin Van Mondfrans
 * uID: u0717113
 * Date: October 19, 2016
 */

package assignment07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Dallin Van Mondfrans
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		FileReader fr = new FileReader(filename);
		int row = 1;
		int col = 1;
		int commentType = 0;
		
		// Initialize boolean flags to be used in control flow of the program
		boolean isComment = false;
		boolean isString = false;
		boolean isChar = false;
		boolean ignoreNext = false;
		
		try {
			int chInt = fr.read();
			char ch = (char) chInt;
			int nextChInt = fr.read();
			char nextCh = (char) nextChInt;
			
			while(chInt != -1) { // Read each character sequentially until we reach the end of the file
				if( isBracket(ch) && !isComment && !isString && !isChar && !ignoreNext ) {
					if( isOpeningBracket(ch) ) {
						stack.push(ch);
					} else { // is closing bracket
						try {
							if( areCompliments((char) stack.peek(), ch) ) {
								stack.pop();
							} else {
								return unmatchedSymbol(row, col, ch, getCompliment((char) stack.pop()) );
							}
						} catch(Exception e) { // Will catch the case where closing bracket tries to pop 
											   // its corresponding opening bracket but there is nothing on the stack
							if(e instanceof NoSuchElementException) {
								return unmatchedSymbol(row, col, ch, ' ');
							}
						}
						
						
					}
					
				} 
				if ( isComment ) { // Check to see if we should end the comment
					if( ch < 0 ) {
						return unfinishedComment();
					} 
					if ((ch == '*' && nextCh == '/') && commentType == 1) { // End block comment check
						isComment = false;
					} else if (ch == '\n' && commentType == 2) { // End line comment check
						isComment = false;
					}
				} else if ( ignoreNext ){ // This handles the special case of when the \ character is used, in which case we ignore the character after it
					ignoreNext = false;
				} else if( ch == '\'' && !isString ) { // Start/End character literal
					if(isChar) {
						isChar = false;
					} else {
						isChar = true;
					}
				} else if( ch == '\"' && !isChar) { // Start/End String
					if(isString) {
						isString = false;
					} else {
						isString = true;
					}
				} else if (ch == '/' && nextCh == '*') { // Start block comment check
					isComment = true;
					commentType = 1;
				} else if (ch == '/' && nextCh == '/') { // Start line comment check
					isComment = true;
					commentType = 2;
				} else if ( ch == '\\' ) {
					ignoreNext = true;
				}
				
				if( chInt < 0 ) { // Check to see if we have reached the end of the file
					break;
				}
				
				col++;
				if( ch == '\n' ) { // Check to see if we are at the end of a line, and update row/col accordingly
					row++;
					col = 1;
				}
				
				// Get next two characters
				chInt = nextChInt;
				ch = nextCh;
				nextChInt = fr.read();
				nextCh = (char) nextChInt;
				
			}
			
			if( isComment && commentType == 1 ) {
				return unfinishedComment();
			}
			if( !stack.isEmpty() ) {
				return unmatchedSymbolAtEOF( getCompliment((char) stack.pop()) );
			}
			
		fr.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return allSymbolsMatch();
	}
	
	/**
	 * 
	 * @param ch - any character (char)
	 * @return true if the character passed in is a bracket, false otherwise
	 */
	public boolean isBracket(char ch) {
		if(ch == '(' || ch == ')' || ch == '[' || ch ==']' || ch =='{' || ch == '}') {
		return true;
		} 
		return false;
	}
	
	/**
	 * 
	 * @param ch - any character (char)
	 * @return true if the bracket is an opening bracket, false otherwise
	 */
	public boolean isOpeningBracket(char ch) {
		if( ch == '(' || ch == '[' || ch =='{' ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param ch1 - any character (char)
	 * @param ch2 - any character (char)
	 * @return true if ch1 is an opening bracket and ch2 is the corresponding closing 
	 * bracket, false otherwise
	 */
	public boolean areCompliments(char ch1, char ch2) {
		boolean result = false;
		if (ch1 == '(') {
			if(ch2 == ')') {
				result = true;
			} 
		} else if (ch1 == '[') {
			if(ch2 == ']') {
				result = true;
			} 
		} else if (ch1 == '{') {
			if(ch2 == '}') {
				result = true;
			} 
		}
		return result;
	}
	
	/**
	 * 
	 * @param ch - any opening bracket character (char)
	 * @return the corresponding closing bracket to the passed in opening bracket
	 * Note: this method should be used after ensuring that ch is indeed an opening 
	 * bracket, otherwise incorrect results may be returned.
	 */
	private char getCompliment(char ch) {
		if(ch == '{') {
			return '}';
		} else if (ch == '(') {
			return ')';
		} else {
			return ']';
		}
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
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