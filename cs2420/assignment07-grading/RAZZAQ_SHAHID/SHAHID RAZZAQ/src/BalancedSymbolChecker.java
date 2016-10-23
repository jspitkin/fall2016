package assignment07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Shahid Bilal Razzaq
 * u0996062
 */
public class BalancedSymbolChecker {
	//Member Variables
	LinkedListStack<Object> symbolStack = new LinkedListStack<Object>();
	private int lineCount = 1;
	private int columnCount;
	private String returnMessage;

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {

		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(filename));
			Scanner fileScanner = new Scanner(fileReader);
			symbolScanner(fileScanner);

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}

		return returnMessage;
	}

	/**
	 * This Method checks the symbols for proper matching
	 * in the input file converted to a scanner object
	 * 
	 * @param s input file as a scanner object
	 */
	public void symbolScanner(Scanner symbolScanner) {
		//Set up some conditional Variables
		boolean skip = false;
		boolean commentStart = false;
		boolean terminate = false;
		//Loop Through the file, while it has a next line
		while (symbolScanner.hasNextLine()) {

			columnCount = 1;
			//convert the scanner line to a String so we can iterate through it 
			String stringLine = symbolScanner.nextLine();

			//Iterate Through string line checking symbols
			for (int i = 0; i < stringLine.length(); i++) {
				//assign the next char to a variable and run through checks
				char symbolToCheck = stringLine.charAt(i);

				//Symbol checks:
				//if symbol read is not on stack, it pushes it on to stack, else tries to match, if it cant, then returns an error
				//there are exceptions for comments, single and double quotes
				if ((commentStart == false) && symbolToCheck == '"') {
					if (symbolStack.isEmpty()) {
						symbolStack.push('"');
						skip = true;
					} else if ((!symbolStack.peek().equals('"'))) {
						symbolStack.push('"');
						skip = true;
					} else {
						symbolStack.pop();
						skip = false;
					}
				} else if ((commentStart == false) && symbolToCheck == '\\') {
					i++;
					columnCount++;

				} else if ((commentStart == false) && symbolToCheck == '\'') {
					if (symbolStack.isEmpty()) {
						symbolStack.push('\'');
						skip = true;
					} else if (!symbolStack.peek().equals('\'')) {
						symbolStack.push('\'');
						skip = true;
					} else {
						symbolStack.pop();
						skip = false;
					}

				} else if (symbolToCheck == '*' && commentStart == true && skip == false) {
					skip = true;

				} else if ((symbolToCheck == '/')) {

					if (symbolStack.isEmpty()) {
						symbolStack.push('/');
						commentStart = true;
					}

					else if (!symbolStack.peek().equals('/')) {
						symbolStack.push('/');
						commentStart = true;
					} else if (symbolStack.peek().equals('/') && skip == false) {
						symbolStack.pop();
						commentStart = false;
						break;
					} else if (skip == true) {
						if (stringLine.charAt(i - 1) == '*') {
							symbolStack.pop();
							commentStart = false;
							skip = false;
						}

					}

				} else if ((skip == false) && symbolToCheck == '(') {
					symbolStack.push('(');
				} else if ((skip == false) && symbolToCheck == '[') {
					symbolStack.push('[');
				} else if ((skip == false) && symbolToCheck == '{') {
					symbolStack.push('{');

				} else if ((skip == false) && symbolToCheck == ')') {
					if (symbolStack.isEmpty()) {
						returnMessage = unmatchedSymbol(lineCount, columnCount, ')', ' ');
						terminate = true;
						break;
					}

					else if (symbolStack.peek().equals('(')) {
						symbolStack.pop();
					} else {
						returnMessage = unmatchedSymbol(lineCount, columnCount, ')', symbolExpected(symbolStack.pop()));
						terminate = true;
						break;
					}

				} else if ((skip == false) && symbolToCheck == ']') {
					if (symbolStack.isEmpty()) {
						returnMessage = unmatchedSymbol(lineCount, columnCount, ']', ' ');
						terminate = true;
						break;
					} else if (symbolStack.peek().equals('[')) {
						symbolStack.pop();
					} else {
						returnMessage = unmatchedSymbol(lineCount, columnCount, ']', symbolExpected(symbolStack.pop()));
						terminate = true;
						break;
					}

				} else if ((skip == false) && symbolToCheck == '}') {
					if (symbolStack.isEmpty()) {
						returnMessage = unmatchedSymbol(lineCount, columnCount, '}', ' ');
						terminate = true;
						break;
					}

					else if (symbolStack.peek().equals('{')) {
						symbolStack.pop();
					} else {
						returnMessage = unmatchedSymbol(lineCount, columnCount, '}', symbolExpected(symbolStack.pop()));
						terminate = true;
						break;
					}

				}

				//incrememnt the column count
				columnCount++;

			}
			//If we get a terminate command, we terminate the scanner loop, otherwise we increment the line count
			if (terminate == true) {
				break;
			} else {
				lineCount++;
			}

		}

		// do final checks and return the proper message
		
		if (!symbolStack.isEmpty() && (terminate == false) && commentStart == false) {
			returnMessage = unmatchedSymbolAtEOF(symbolExpected(symbolStack.peek()));
		} else if (symbolStack.isEmpty() && terminate == false && commentStart == false) {
			returnMessage = allSymbolsMatch();
		} else if (commentStart == true) {
			returnMessage = unfinishedComment();
		}

	}

	/**
	 * Helper Method to check for proper matching symbol.
	 * @param input a char symbol
	 * @return the closing symbol for the input symbol
	 */
	public char symbolExpected(Object input) {
		char inputSymbol = (char) input;
		if (inputSymbol == '(') {
			return ')';
		} else if (inputSymbol == '[') {
			return ']';
		} else if (inputSymbol == '{') {
			return '}';
		}else{
			return ' ';
		}
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