//Cole Cruz
package assignment07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Cole Cruz
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		File dataFile = new File(filename);
		if (!dataFile.exists()) {
			throw new FileNotFoundException();
		}
		String sourceCode = "";
		try (FileReader reader = new FileReader(dataFile); BufferedReader br = new BufferedReader(reader)) {
			String line;
			while ((line = br.readLine()) != null) {
				sourceCode = sourceCode.concat(line + (char) -1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		char character, nextCharacter, topCharacter = ' ';
		int lineNumber = 1, colNumber = 0;
		boolean ignore = false, ignoreLine = false, ignoreSingleQuote = false, ignoreDoubleQuote = false,
				ignoreNext = false;
		for (int index = 0; index < sourceCode.length(); index++) {
			if (stack.size() > 0) {
				topCharacter = stack.peek();
			}
			character = sourceCode.charAt(index);
			if (ignoreNext) {
				ignoreNext = false;
			} else if (character == (char) -1) {
				lineNumber++;
				colNumber = 0;
				if (ignoreLine) {
					ignoreLine = false;
				}
			} else if (character == (char) 92) {
				ignoreNext = true;
			} else if ((character == (char) 39 || character == (char) 34) && !ignoreLine && !ignore) {
				switch (character) {
				case (char) 39:
					ignoreSingleQuote = !ignoreSingleQuote;
					break;
				case (char) 34:
					ignoreDoubleQuote = !ignoreDoubleQuote;
					break;
				}
			} else if (ignoreLine || ignoreSingleQuote || ignoreDoubleQuote) {
			} else if (ignore) {
				if ((character == '*') && (index + 1 < sourceCode.length())) {
					nextCharacter = sourceCode.charAt(index + 1);
					if (nextCharacter == '/') {
						ignore = false;
					}
				}
			} else if ((character == '/') && (index + 1 < sourceCode.length())) {
				nextCharacter = sourceCode.charAt(index + 1);
				if (nextCharacter == '/') {
					ignoreLine = true;
				} else if (nextCharacter == '*') {
					ignore = true;
				}
			} else if ((character == '(') || (character == '{') || (character == '[')) {
				stack.push(character);
			} else if ((character == ')') || (character == '}') || (character == ']')) {
				if (stack.isEmpty()) {
					return unmatchedSymbol(lineNumber, colNumber, character, ' ');
				}
				stack.pop();
				if ((character == ')') && (topCharacter == '(')) {
				} else if ((character == '}') && (topCharacter == '{')) {
				} else if ((character == ']') && (topCharacter == '[')) {
				} else {
					switch (topCharacter) {
					case '(':
						return unmatchedSymbol(lineNumber, colNumber, character, ')');
					case '{':
						return unmatchedSymbol(lineNumber, colNumber, character, '}');
					case '[':
						return unmatchedSymbol(lineNumber, colNumber, character, ']');
					}
				}
			}
			colNumber++;
		}
		if (ignore) {
			return unfinishedComment();
		}
		if (stack.isEmpty()) {
			return allSymbolsMatch();
		}
		switch (topCharacter) {
		case '(':
			return unmatchedSymbolAtEOF(')');
		case '{':
			return unmatchedSymbolAtEOF('}');
		case '[':
			return unmatchedSymbolAtEOF(']');
		}
		return "ERROR";
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