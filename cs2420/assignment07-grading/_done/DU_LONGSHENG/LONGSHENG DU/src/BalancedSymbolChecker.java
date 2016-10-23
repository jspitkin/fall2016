package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Longsheng Du
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		LinkedListStack<Character> checkStack = new LinkedListStack<Character>();	
	
		FileReader reader = new FileReader(new File(filename));
		Scanner scan = new Scanner(reader);
		int lineCount = 0;
		boolean notComments = true;
		boolean notLiterals = true;
		
		while(scan.hasNextLine()){
			lineCount++;
			String thisLine = scan.nextLine();
			
			char lastChar = '\0';
			
			for(int colCount = 1; colCount <= thisLine.length();colCount++){
				char thisChar = thisLine.charAt(colCount-1);
				
				//Skip rest line
				if(notLiterals && lastChar == '/' && thisChar=='/') {
					colCount = thisLine.length()+1;
					continue;
				}
				//Start block comments
				else if(notLiterals && lastChar == '/' && thisChar=='*') {
					notComments = false;
				}
				//Stop block comments
				else if(notLiterals && lastChar == '*' && thisChar=='/') {
					notComments = true;
				}
				
				//Ignore Literals
				else if(notComments && (thisChar=='\"' || thisChar=='\'') && lastChar != '\\') {
					if(checkStack.peek().equals(thisChar)) {
						checkStack.pop();
						notLiterals = true;
					}
					else {
						checkStack.push(thisChar);	
						notLiterals = false;
					}
				}
				
				
				if(notComments && notLiterals) {
					//Push left
					if(thisChar=='('||thisChar=='['||thisChar=='{') {
						checkStack.push(thisChar);
					}
					//Match right
					else if(thisChar==')'||thisChar==']'||thisChar=='}') {
						//Stack is already empty
						if(checkStack.isEmpty()){
							scan.close();
							return unmatchedSymbol(lineCount,colCount,thisChar,' ');
						}
						//Symbol matches
						if(checkStack.peek().equals(matchedSymbol(thisChar))) {
							checkStack.pop();
						}
						//Symbol does not match
						else {
							scan.close();
							return unmatchedSymbol(lineCount,colCount,thisChar,matchedSymbol(checkStack.peek()));
						}						
					}
				}
				//Track last character
				lastChar = thisChar;
			}
		}
		
		scan.close();
		
		if(!notComments) {
			return unfinishedComment();
		}
		else if(!checkStack.isEmpty()) {
			return unmatchedSymbolAtEOF(matchedSymbol(checkStack.peek()));
		}
		else {
			return allSymbolsMatch();
		}

	}
	
	/**
	 * Get a matched symbol.
	 * 
	 * @param char
	 *            -- The symbol want to match
	 * @return char
	 *            -- The matched symbol
	 */
	private char matchedSymbol(char input) {
		switch(input) {
			case ')' : return '(';
			case ']' : return '[';
			case '}' : return '{';
			case '(' : return ')';
			case '[' : return ']';
			case '{' : return '}';
			default  : return '\0';
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