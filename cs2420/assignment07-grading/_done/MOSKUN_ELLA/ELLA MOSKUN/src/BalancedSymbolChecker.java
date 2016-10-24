package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Ella Moskun u0897252
 */
public class BalancedSymbolChecker {

	private enum STATE {
		NORMAL, OPEN_BLOCK_COMMENT, OPEN_STRING_LITERAL, OPEN_CHAR_LITERAL
	}

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * 
	 * Errors that are not defined by the assignment description are not
	 * implemented.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		LinkedListStack<Character> characterStack = new LinkedListStack<Character>();
		STATE state = STATE.NORMAL;

		// try with resources used to ensure scanner closes
		try (Scanner scanner = new Scanner(new File(filename))) {
			int lineNumber = 0;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lineNumber++;

				for (int i = 0; i < line.length(); i++) {
					char character = line.charAt(i);

					switch (state) {
					case NORMAL:
						switch (character) {
						case '{':
						case '(':
						case '[':
							// push left character to the stack
							characterStack.push(character);
							break;

						case '}':
						case ')':
						case ']':
							// proceed normally if right character matched the
							// previous left character and return error
							// otherwise
							char expected = (characterStack.isEmpty() ? ' ' : right(characterStack.pop()));
							if (character != expected) {
								return unmatchedSymbol(lineNumber, i + 1, character, expected);
							}
							break;

						case '"':
							state = STATE.OPEN_STRING_LITERAL;
							break;

						case '\'':
							state = STATE.OPEN_CHAR_LITERAL;
							break;

						case '\\':
							// ignore escaped character
							i++;
							break;

						case '/':
							if (i < line.length() - 1 && line.charAt(i + 1) == '*') {
								state = STATE.OPEN_BLOCK_COMMENT;
								i++;
							} else if (i < line.length() - 1 && line.charAt(i + 1) == '/') {
								// begin line comment
								i = line.length();
							}
							break;

						}

					case OPEN_BLOCK_COMMENT:
						if (i < line.length() - 1 && character == '*' && line.charAt(i + 1) == '/') {
							state = STATE.NORMAL;
							i++;
						}
						break;

					case OPEN_STRING_LITERAL:
						switch (character) {
						case '\\':
							// ignore escaped character
							i++;
							break;
						case '"':
							state = STATE.NORMAL;
							break;
						}
						break;

					case OPEN_CHAR_LITERAL:
						switch (character) {
						case '\\':
							// ignore escaped character
							i++;
							break;
						case '\'':
							state = STATE.NORMAL;
							break;
						}
						break;
					}

				}
			}
		}
		// no catch -- let the FileNotFoundException propagate up

		// EOF
		switch (state) {
		case NORMAL:
			if (characterStack.isEmpty()) {
				return allSymbolsMatch();
			} else {
				return unmatchedSymbolAtEOF(right(characterStack.pop()));
			}
		case OPEN_BLOCK_COMMENT:
			return unfinishedComment();
		default:
			// Error messages for an unfinished string literal or unfinished
			// char literal are not defined by the assignment description, and
			// thus they are not implemented here.
			return null;
		}
	}

	/**
	 * Helper method to get the right version of the given left char.
	 * 
	 * @param left
	 *            Input character. Must be one of {, (, or [.
	 * @return the opposite of c (which will be one of }, ), or ]).
	 */
	private char right(char left) {
		String leftCharacters = "{([";
		String rightCharacters = "})]";
		return rightCharacters.charAt(leftCharacters.indexOf(left));
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