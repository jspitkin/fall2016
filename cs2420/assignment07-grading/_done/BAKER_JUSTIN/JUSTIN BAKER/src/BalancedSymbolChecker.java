package assignment07;
/**@author Justin Baker u0726589
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author ??
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(filename));
		return Analyzer(scanner);
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

	/** Handles Cases for checkFile method
	 * returns - String
	 * If we read a (, {, or [, we will push it on the stack. 
	 * If we read a ), }, or ], we will pop and examine the symbol at the top of the stack.
	 * If the symbol matches, e.g., we read a ] and the top symbol is a [, then we will continue reading.
	 * If the symbol does not match, e.g., we read a } and the top symbol is a [, then we will report the error and terminate.

	 * When all symbols have been read, we will check for an empty stack.
	 * If the stack is empty, then the program correctly matches all symbols.
	 * If the stack is not empty, then we will report the error.

	 * Two kinds of comments are allowed (// and), and we will ignore any symbols contained in a comment. Furthermore, we will also ignore symbols contained in string and character literals.
	 */
	private String Analyzer(Scanner scanner) {
		int line_number = 0;
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		boolean open_comment = false;
		while (scanner.hasNextLine()) {
			String next_line = scanner.nextLine();
			line_number++;
			for (int i = 0; i < next_line.length(); i++) {
				char next_char = next_line.charAt(i);
				if (open_comment) {
					char prev_char = 'a';
					if (i > 0) {
						prev_char = next_line.charAt(i - 1);
					}
					if(next_char == '/' && prev_char =='*'){
						open_comment=false;
					}
					if ((next_char == '\'' || next_char == '"') && prev_char != '\\') {
						open_comment = false;
					}
				} else if (next_char == '{' || next_char == '[' || next_char == '(') {
					stack.push(next_char);
				} else if (next_char == ')') {
					if (!stack.isEmpty() && stack.peek() != '(') {
						if (stack.peek() == '{') {
							return unmatchedSymbol(line_number, i + 1, next_char, '}');
						} else {
							return unmatchedSymbol(line_number, i + 1, next_char, ']');
						}
					}
					if (stack.isEmpty()) {
						return unmatchedSymbol(line_number, i + 1, next_char, ' ');
					}
					stack.pop();
				} else if (next_char == '}') {
					if (!stack.isEmpty() && stack.peek() != '{') {
						if (stack.peek() == '(') {
							return unmatchedSymbol(line_number, i + 1, next_char, ')');
						} else {
							return unmatchedSymbol(line_number, i + 1, next_char, ']');
						}
					}
					if (stack.isEmpty()) {
						return unmatchedSymbol(line_number, i + 1, next_char, ' ');
					}
					stack.pop();
				} else if (next_char == ']') {
					if (!stack.isEmpty() && stack.peek() != '[') {
						if (stack.peek() == '{') {
							return unmatchedSymbol(line_number, i + 1, next_char, '}');
						} else {
							return unmatchedSymbol(line_number, i + 1, next_char, ')');
						}
					}
					if (stack.isEmpty()) {
						return unmatchedSymbol(line_number, i + 1, next_char, ' ');
					}
					stack.pop();
				} else if (next_char == '/') {
					i++;
					if (i >= next_line.length()) {
						break;
					}
					next_char = next_line.charAt(i);
					if (next_char == '*') {
						open_comment = true;
					} else if (next_char == '/') {
						break;
					}
				} else if (next_char == '"' || next_char == '\'') {
					open_comment = true;
				}
			}
		}		
		if (open_comment) {
			return unfinishedComment();
		}
		if (stack.isEmpty()) {
			return allSymbolsMatch();
		}
		char c = stack.pop();
		if (c == '{') {
			return unmatchedSymbolAtEOF('}');
		} else if (c == '[') {
			return unmatchedSymbolAtEOF(']');
		} else {
			return unmatchedSymbolAtEOF(')');
		}

	}
}