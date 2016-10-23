package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Hyrum Johnson
 */
public class BalancedSymbolChecker {
	int lineNumber = 0;
	int columnNumber = 0;
	char symbolRead = 0;
	char symbolExpected = 0;
	boolean charBool = false;

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */

	public String checkFile(String filename) throws FileNotFoundException {
		// Counters
		

		LinkedListStack<Character> listOfStrings = new LinkedListStack<Character>();
		LinkedListStack<Character> listOfChars = new LinkedListStack<Character>();

		// check to see if there is a file.
		if (new File(filename).isFile() != true) {
			throw new FileNotFoundException();
		}

		// Get input
		File inputFile = new File(filename);
		Scanner newFile = new Scanner(inputFile);

		// While loop starts here
		while (newFile.hasNextLine()) {

			// Set currentLine add 1 to Lines.
			String currentLine = newFile.nextLine();
			lineNumber++;
			columnNumber = 0;

			// Loops through all lines.
			for (int i = 0; i < currentLine.length(); i++) {

				// Add one to column.
				columnNumber++;

				// get char.
				char selectedChar = currentLine.charAt(i);

				// This is where we checks for everything.
				if (i != currentLine.length() - 1) {

					// Check / and /
					if (currentLine.charAt(i) == '/' && currentLine.charAt(i + 1) == '/') {
						break;

						// Check / and *
					} else if (currentLine.charAt(i) == '/' && currentLine.charAt(i + 1) == '*' && charBool != true) {
						charBool = true;

						// Check * and /
					} else if (currentLine.charAt(i) == '*' && currentLine.charAt(i + 1) == '/' && charBool == true) {
						charBool = false;

						// Check " and CharBool.
					} else if (currentLine.charAt(i) == '"' && charBool != true) {
						charBool = true;

						// Check " and not \\
					} else if (currentLine.charAt(i) == '"'
							&& (currentLine.charAt(i - 1) != '\\' || charBool == true)) {
						charBool = false;

						// Check \ and \.
					} else if (currentLine.charAt(i) == '\'' && currentLine.charAt(i + 2) == '\'') {
						break;

						// Check \\.
					} else if (currentLine.charAt(i) == '\\') {
						i++;
						continue;
					}
				}

				// Will check Char if false.
				if (charBool != true) {

					// Check SelectedChar.
					if (selectedChar == '(' || selectedChar == '{' || selectedChar == '[') {

						// Push Value received.
						switch (selectedChar) {
						case '(':
							listOfStrings.push(selectedChar);
							listOfChars.push(')');
							break;
						case '{':
							listOfStrings.push(selectedChar);
							listOfChars.push('}');
							break;
						case '[':
							listOfStrings.push(selectedChar);
							listOfChars.push(']');
							break;
						default:
							break;
						}
					}

					// Here it checks the end symbol.
					if (selectedChar == ')' || selectedChar == '}' || selectedChar == ']') {
						if (listOfStrings.isEmpty()) {
							symbolRead = selectedChar;

							// Close new file.
							newFile.close();

							// Call unmatchedSymbol Method.
							return unmatchedSymbol(lineNumber, columnNumber, selectedChar, ' ');
						}

						symbolExpected = listOfChars.peek();

						switch (selectedChar) {
						case ']':

							if (listOfStrings.peek() != '[') {
								symbolRead = selectedChar;
								newFile.close();

								// Call unmatchedSymbol Method.
								return unmatchedSymbol(lineNumber, columnNumber, selectedChar, symbolExpected);
							} else {
								symbolRead = selectedChar;
								listOfStrings.pop();
								listOfChars.pop();
								break;
							}
						case '}':

							if (listOfStrings.peek() != '{') {
								symbolRead = selectedChar;
								newFile.close();

								// Call unmatchedSymbol Method.
								return unmatchedSymbol(lineNumber, columnNumber, selectedChar, symbolExpected);
							} else {
								symbolRead = selectedChar;
								listOfStrings.pop();
								listOfChars.pop();
								break;
							}
						case ')':

							if (listOfStrings.peek() != '(') {
								symbolRead = selectedChar;
								newFile.close();

								// Call unmatchedSymbol Method.
								return unmatchedSymbol(lineNumber, columnNumber, selectedChar, symbolExpected);
							} else {

								symbolRead = selectedChar;
								// Pop Both lists
								listOfChars.pop();
								listOfStrings.pop();
								break;
							}

						}
					}
				}
			}

		}

		// While loop ends here

		if (charBool == true) {
			newFile.close();
			return unfinishedComment();

		}

		if (charBool == true) {
			newFile.close();
			return allSymbolsMatch();
		}

		// Checks whether the stack is not empty.
		if (!listOfStrings.isEmpty()) {
			symbolExpected = listOfChars.peek();
			Character lastString = listOfStrings.peek();
			switch (lastString) {
			case '[':
				symbolRead = '[';
				newFile.close();
				return unmatchedSymbolAtEOF(symbolExpected);

			case '{':
				symbolRead = '{';
				newFile.close();
				return unmatchedSymbolAtEOF(symbolExpected);

			case '(':
				symbolRead = '(';
				newFile.close();
				return unmatchedSymbolAtEOF(symbolExpected);

			default:
				break;
			}

		}

		// checks whether the stack is empty.
		if (listOfStrings.isEmpty()) {
			newFile.close();
			return allSymbolsMatch();
		}

		newFile.close();
		return filename;
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