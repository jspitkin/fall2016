package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * @author Anthony Iovino 
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner fileScanner = new Scanner(file);

		LinkedListStack<Character> symbolStack = new LinkedListStack<>();
		boolean lineCommentStarted = false;
		boolean multilineCommentStarted = false;
		boolean stringStarted = false;
		boolean charStarted = false;

		int lineNumber = 0;
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();

			// Look for beginning of String
			for (int col = 0; col < line.length(); col++) {
				char character = line.charAt(col);

				// If a string has been started
				if (!lineCommentStarted && !multilineCommentStarted && character == '\"') {
					// Handle special case when previous character is an escape
					if (col > 0) {
						char previousCharacter = line.charAt(col-1);
						
						// Toggle whether the code is in a string
						if (previousCharacter != '\\')
							stringStarted = !stringStarted;
					}
					else {
						// Toggle whether the code is in a string
						stringStarted = !stringStarted;
					}
				} 
				// Look for beginning of char
				else if (!lineCommentStarted && !multilineCommentStarted && character == '\'') {
					charStarted = !charStarted;
				} 
				// Look for beginning of comment
				else if (character == '/') {
					// If there is another character after this one
					if (col != line.length() - 1) {
						char nextCharacter = line.charAt(col + 1);

						// Line comment started
						if (nextCharacter == '/')
							lineCommentStarted = true;
						// Multiline comment started
						else if (nextCharacter == '*')
							multilineCommentStarted = true;
					}
				}
				// Look for end of multiline comment
				else if (character == '*') {
					// If there is another character after this one
					if (col != line.length() - 1) {
						char nextCharacter = line.charAt(col + 1);

						if (nextCharacter == '/')
							multilineCommentStarted = false;
					}
				} 
				// Character of actual code reached!
				else if (!lineCommentStarted && !multilineCommentStarted && !stringStarted && !charStarted) {
					if (isValidLeftSymbol(character)) {
						symbolStack.push(character);
					} else if (isValidRightSymbol(character)) {
						if (symbolStack.isEmpty()) {
							// Show ERROR
							String error = unmatchedSymbol(lineNumber + 1, col + 1, character, ' ');
							return error;
						}

						char poppedSymbol = symbolStack.pop();

						if (isMatchingSymbol(poppedSymbol, character)) {
							// continue reading
						} else {
							// Show ERROR
							char symbolExpected = getMatchingSymbol(poppedSymbol);
							String error = unmatchedSymbol(lineNumber + 1, col + 1, character, symbolExpected);
							return error;
						}
					}
				}
			}

			lineCommentStarted = false;
			lineNumber++;
		}

		if (multilineCommentStarted)
			return unfinishedComment();

		if (symbolStack.isEmpty()) {
			return allSymbolsMatch();
		} else {
			return unmatchedSymbolAtEOF(getMatchingSymbol(symbolStack.pop()));
		}
	}

	private boolean isValidLeftSymbol(char character) { //identifies a left enclosing character
		return character == '(' || character == '{' || character == '[';
	}

	private boolean isValidRightSymbol(char character) { //identifies a left enclosing character
		return character == ')' || character == '}' || character == ']';
	}

	private boolean isMatchingSymbol(char leftCharacter, char rightCharacter) {//compares the left and right enclosing characters
		if (leftCharacter == '(')
			return rightCharacter == ')';
		if (leftCharacter == '{')
			return rightCharacter == '}';
		if (leftCharacter == '[')
			return rightCharacter == ']';
		return false;
	}

	private char getMatchingSymbol(char leftCharacter) {
		if (leftCharacter == '(')
			return ')';
		if (leftCharacter == '{')
			return '}';
		if (leftCharacter == '[')
			return ']';
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