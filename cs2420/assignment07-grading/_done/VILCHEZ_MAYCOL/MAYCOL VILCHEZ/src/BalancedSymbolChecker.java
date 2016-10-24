package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 */
/**
 * @author maycol vilchez u0832923
 */
public class BalancedSymbolChecker {

	private LinkedListStack<Character> stack = new LinkedListStack<>();

	private boolean slashComment = false; // ( // )
	private boolean starComment = false; // ( /*....*/ ) open and close comment
	private boolean doubleQuoteComment = false; // ( " )
	private boolean singleQuoteComment = false; // ( ' )

	private int countDoubleQuote = 0; // count double quote
	private int countSingleQuote = 0; // count single quote

	private int countCharacters = 0; // count characters ( (, [, { )

	private char current; // use as index data

	private String quote = ""; // use to add to then compare

	private int col; // keep track of index for every line
	
	private char expected;// use to get the expected symbol if error exist

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	@SuppressWarnings("resource")
	public String checkFile(String filename) throws FileNotFoundException {

		if (filename == null) {

			throw new FileNotFoundException();

		}

		allSymbolsMatch();

		int lineNum = 1;

		// read file
		Scanner file = new Scanner(new File(filename));

		String readLine = "";

		while (file.hasNextLine()) {

			// get line
			readLine = file.nextLine();

			// check if start comment ( /* ) is close if not return unfinished
			// comment
			if (!(file.hasNextLine()) && starComment == true) {

				return unfinishedComment();

			}

			if (!(checkSymbol(readLine, lineNum).equals(allSymbolsMatch()))) {

				return checkSymbol(readLine, lineNum);

			}

			lineNum++;

		}

		// check if some character ( (, [, { ) does not have match
		if (countCharacters == 1) {

			return unmatchedSymbolAtEOF(current);

		}

		return allSymbolsMatch();

	}

	/**
	 * Get line run to every index by .charAt if open symbol found and is not
	 * surrounded by comments push and increment countCharacter. if close symbol
	 * found and is not surrounded by comments and has a match pop and decrement
	 * countCharacter; if doesn't match return the error. if stack is empty
	 * return unmachedSymbol
	 */
	private String checkSymbol(String fileString, int lineNum) {

		col = 1;

		quote = "";

		for (int index = 0; index < fileString.length(); index++) {

			current = fileString.charAt(index);

			starAndSlashComment(fileString, index);

			doubleQuoteComment();

			singleQuoteComment();

			// if current is either ( (, [, {) add to stack, only if there's not
			// comments between
			if (current == '(' || current == '[' || current == '{') {

				addToStack();

			}

			// if there are no comments between either pop or return error
			// if any comments is true skip
			if (slashComment == false && starComment == false && doubleQuoteComment == false
					&& singleQuoteComment == false) {

				if (current == ')' || current == ']' || current == '}') {

					expected = ' ';

					if (stack.isEmpty()) {

						return unmatchedSymbol(lineNum, col, current, expected);

					}

					else if (stack.peek() == '(' && current != ')') {

						expected = ')';

						return unmatchedSymbol(lineNum, col, current, expected);

					}

					else if (stack.peek() == '[' && current != ']') {

						expected = ']';

						return unmatchedSymbol(lineNum, col, current, expected);

					}

					else if (stack.peek() == '{' && current != '}') {

						expected = '}';

						return unmatchedSymbol(lineNum, col, current, expected);

					}

					// if there are no errors pop
					stack.pop();

					countCharacters--;

				}

			}

			col++;

		}

		// as new line set comments false, but not ( /* ) since it can be type
		// in new lines
		slashComment = false;

		singleQuoteComment = false;

		doubleQuoteComment = false;

		return

		// after each line and with no errors set allSymbolsMatch.
		allSymbolsMatch();

	}

	/**
	 * check for single quote comment and set any ( (, [, { ) as not counted
	 */
	public void singleQuoteComment() {

		if (current == '\'' && slashComment == false && starComment == false) {

			countSingleQuote++;

			if (countSingleQuote % 2 == 0) {

				singleQuoteComment = false;

			} else {

				singleQuoteComment = true;

			}

		}

	}

	/**
	 * check for double quote comment and set any ( (, [, { ) as not counted
	 */
	public void doubleQuoteComment() {

		if (current == '\\' && doubleQuoteComment == true) {

			quote += current;

		}

		if (current == '"' && doubleQuoteComment == true) {

			quote += current;

		}

		if (quote.equals("\\\"") && doubleQuoteComment == true) {

			countDoubleQuote++;

			quote = "";

		}

		if (current == '"' && slashComment == false && starComment == false) {

			countDoubleQuote++;

			if (countDoubleQuote % 2 == 0) {

				doubleQuoteComment = false;

			} else {

				doubleQuoteComment = true;

			}

		}

	}

	/**
	 * check if comments shows; if So set comments to true. check ( /* ) to set
	 * comments true and ( * / ) to set comments false
	 */
	public void starAndSlashComment(String fileString, int index) {

		// checks if last index of the line ends in comment if so skip this to
		// avoid indexOutOfBoundException
		if (fileString.charAt(fileString.length() - 1) != '/') {

			if (current == '/' && fileString.charAt(index + 1) == '/') {

				slashComment = true;

			}

			if (current == '/' && fileString.charAt(index + 1) == '*') {

				starComment = true;

			}

			if (current == '*' && fileString.charAt(index + 1) == '/') {

				starComment = false;

			}

		}

		// if line ends with */ set comments false. or if at current index and
		// next index is */ set comment to false.
		// to show as closing comment.
		if (fileString.charAt(fileString.length() - 1) == '/' && fileString.charAt(fileString.length() - 2) == '*'
				|| current == '*' && fileString.charAt(index + 1) == '/') {

			starComment = false;

		}

	}

	/**
	 * if No comments around ( (, [, { ) add to stack increment countCharacter
	 */
	public void addToStack() {

		if (slashComment == false && starComment == false && doubleQuoteComment == false
				&& singleQuoteComment == false) {

			stack.push(current);

			countCharacters++;

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