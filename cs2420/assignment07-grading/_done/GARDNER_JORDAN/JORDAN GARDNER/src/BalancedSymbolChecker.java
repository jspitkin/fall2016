package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Jordan Gardner u0566259
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	
	public static String checkFile(String filename)
			throws FileNotFoundException {
		if (filename.isEmpty() || filename.length() == 0) {
			throw new FileNotFoundException();
		}
		Scanner scanner = new Scanner(new File(filename));
		char newChar = ' ';
		char newChar2 = ' ';
		char c = ' ';
		boolean isCommentOrString = false;
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		int lineNumber = 1;

		while (scanner.hasNextLine()) {


			String data = scanner.nextLine();//looking line by line
			for (int i = 0; i < data.length(); i++) {

				if (i + 1 <= data.length() - 1) {
					if (data.charAt(i) == '\\') {
						i++;
						continue;
					}
					//checks for comments first
					if (data.charAt(i) == '*' && data.charAt(i + 1) == '/') {
						isCommentOrString = false;
					} else if (data.charAt(i) == '/'
							&& data.charAt(i + 1) == '*') {
						isCommentOrString = true;
					}
					if (data.charAt(i) == '\'') {
						isCommentOrString = !isCommentOrString;
					}
					//checks for strings
					if (data.charAt(i) == '"') {
						isCommentOrString = !isCommentOrString;
					}
					if (data.charAt(i) == '/' && data.charAt(i + 1) == '/') {
						break;
					}

				}
				if (isCommentOrString) {
					continue;
				}
				//grabs first symbol and checks orientation
				if (data.charAt(i) == '[') {
					stack.push(data.charAt(i));
				} else if (data.charAt(i) == '{') {
					stack.push(data.charAt(i));
				} else if (data.charAt(i) == '(') {
					stack.push(data.charAt(i));
				}
				if (data.charAt(i) == ']' || data.charAt(i) == '}'
						|| data.charAt(i) == ')') {
					//saves the correct change to newChar2
					if (data.charAt(i) == '[') {
						newChar2 = ']';
					} else if (data.charAt(i) == '{') {
						newChar2 = '}';
					} else if (data.charAt(i) == '(') {
						newChar2 = ')';
					} else if (data.charAt(i) == ']') {
						newChar2 = '[';
					} else if (data.charAt(i) == '}') {
						newChar2 = '{';
					} else if (data.charAt(i) == ')') {
						newChar2 = '(';
					}
					//check stack
					if (stack.isEmpty()) {
						char empty = ' ';
						return unmatchedSymbol(lineNumber, i + 1,
								data.charAt(i), empty);
					}
					// pop if top of stack equals the correct character
					if (newChar2 == stack.peek()) {
						stack.pop();
					} else {
						//else change it
						if (stack.peek() == '[') {
							newChar = ']';
						} else if (stack.peek() == '{') {
							newChar = '}';
						} else if (stack.peek() == '(') {
							newChar = ')';
						} else if (stack.peek() == ']') {
							newChar = '[';
						} else if (stack.peek() == '}') {
							newChar = '{';
						} else if (stack.peek() == ')') {
							newChar = '(';
						}

						if (!scanner.hasNextLine()) {
							return unmatchedSymbolAtEOF(newChar);
						} else {
							return unmatchedSymbol(lineNumber, i + 1,
									data.charAt(i), newChar);
						}
					}
				}

			}
			//check next line
			lineNumber++;
		}
		if (isCommentOrString) {
			return unfinishedComment();
		}
		//out of lines now check stack
		if (!stack.isEmpty()) {
			
			if (stack.peek() == '[') {
				c = ']';
			} else if (stack.peek() == '{') {
				c = '}';
			} else if (stack.peek() == '(') {
				c = ')';
			} else if (stack.peek() == ']') {
				c = '[';
			} else if (stack.peek() == '}') {
				c = '{';
			} else if (stack.peek() == ')') {
				c = '(';
			}
			return unmatchedSymbolAtEOF(c);
		}
		return allSymbolsMatch();
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber,
			char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column "
				+ colNumber + ". Expected " + symbolExpected + ", but read "
				+ symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected "
				+ symbolExpected + ".";
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