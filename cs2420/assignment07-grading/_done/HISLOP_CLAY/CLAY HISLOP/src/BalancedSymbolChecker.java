package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Clayton Hislop
 * uID: u0515744
 */
public class BalancedSymbolChecker {
	
	private Scanner sc;
	private LinkedListStack<Character> stack = new LinkedListStack<Character>();
	private boolean isComment = false;
	private int lineNumber, colNumber;
	private char expected = ' ';

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		sc = new Scanner(new File(filename));
		lineNumber = 0;		
		while (sc.hasNextLine()) {
			lineNumber++;
			colNumber = 1;
			isComment = false;
			String currentLine = sc.nextLine();
			
			for (int i = 0; i < currentLine.length(); i++, colNumber++) {				
				if (currentLine.charAt(i) == '{' ||
					 currentLine.charAt(i) == '[' ||
					 currentLine.charAt(i) == '(') 		{
					if (stack.isEmpty()) {
						stack.push(currentLine.charAt(i));	
					}
					else if (stack.peek() == '*') {
						continue;
					}
					else if (stack.peek() == '\'') {
						continue;
					}
					else if (stack.peek() != '\"') {
						stack.push(currentLine.charAt(i));								
					}					
				}
				else if (currentLine.charAt(i) == '\\') {
					if (currentLine.charAt(i+1) == '\'' || 
						 currentLine.charAt(i+1) == '\"' ||
						 currentLine.charAt(i+1) == '\\'  )   {
						i++;
						colNumber++;
					}
					else if (stack.peek() == '*') {
						continue;
					}
				}						
				else if (currentLine.charAt(i) == '\'') {
					if (stack.peek() == '*') {						
					}
					else if (stack.peek() == '\'') {
						stack.pop();
					}
					else {
						stack.push('\'');
					}
				}
				else if (currentLine.charAt(i) == '/') {
					if (stack.isEmpty()) {
						stack.push('/');
					}
					else if (stack.peek() == '/') {
						stack.pop();
						isComment = true;
					}
					else if (stack.peek() == '\"' || stack.peek() == '\'') {
						continue;
					}
					else if (stack.peek() == '*'){
						if (currentLine.charAt(i-1) == '*') {
							stack.pop();
							stack.pop();
						}
						else
							continue;
					}						
					else
						stack.push('/');
				}				
				else if (currentLine.charAt(i) == '*') {
					if (stack.peek() == '/') {
						stack.push('*');
					}
				}
				else if (currentLine.charAt(i) == '\"') {
					if (stack.peek() == '\"') {
						stack.pop();
					}
					else if (stack.peek() == '*') {
						continue;
					}
					else {
						stack.push('\"');
					}
				}
				else if(currentLine.charAt(i) == '}') {
					 if (stack.isEmpty()) {
							return unmatchedSymbol(lineNumber, colNumber, '}', expected);
					 }
					 else if (stack.peek() == '*') {
						 continue;
					 }
					 else if (stack.peek() == '\"' || stack.peek() == '\'') {
						continue;
					}
					 
					if (stack.peek() == '{') {
						stack.pop();
					}
					else {
						if (stack.peek() == '(') {
							expected = ')';
						}
						else if (stack.peek() == '[') {
							expected = ']';
						}
						return unmatchedSymbol(lineNumber, colNumber, '}', expected);
					}				
				}
				else if (currentLine.charAt(i) == ']') {					
					 if (stack.isEmpty()) {
							return unmatchedSymbol(lineNumber, colNumber, ']', expected);
					 }
					 else if (stack.peek() == '*') {
						 continue;
					 }
					 else if (stack.peek() == '\"' || stack.peek() == '\'') {
							continue;
					}
					 
					if (stack.peek() == '[') {
						stack.pop();
					}					
					else {
						if (stack.peek() == '(') {
							expected = ')';
						}
						else if (stack.peek() == '{') {
							expected = '}';
						}
						return unmatchedSymbol(lineNumber, colNumber, ']', expected);
					}		
				}
				else if (currentLine.charAt(i) == ')') {
					 if (stack.isEmpty()) {
							return unmatchedSymbol(lineNumber, colNumber, ')', expected);
					 }
					 else if (stack.peek() == '*') {
						 continue;
					 }
					 else if (stack.peek() == '\"' || stack.peek() == '\'') {
							continue;
					}
					
					if (stack.peek() == '(') {
						stack.pop();
					}
					else {
						if (stack.peek() == '{') {
							expected = '}';
						}
						else if (stack.peek() == '[') {
							expected = ']';
						}
						return unmatchedSymbol(lineNumber, colNumber, ')', expected);
					}		
				}
				
				if (isComment) { //if the rest of the line is a comment
					break;
				}
			}
		}		
		
		if (!stack.isEmpty()) {
			if (stack.peek() == '*') {
				return unfinishedComment();
			}
			else {
				if (stack.peek() == '(') {
					expected = ')';
				}
				else if (stack.peek() == '[') {
					expected = ']';
				}
				else if (stack.peek() == '{') {
					expected = '}';
				}
			}
			return unmatchedSymbolAtEOF(expected);
		}		
		return allSymbolsMatch();
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