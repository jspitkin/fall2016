package assignment07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Andy Dao, uID: u0692334
 */
public class BalancedSymbolChecker {

    private String ERROR_IN_STACK = "No error in stack."; // Default to this current no error in stack string
    private char symbolRead = ' '; // Instantiate it to a empty character
    private char symbolExpected = ' '; // Instantiate it to a empty character

    /**
     * Returns a message indicating whether the input file has unmatched
     * symbols. (Use the methods below for constructing messages.) Throws
     * FileNotFoundException if the file does not exist.
     */
    public String checkFile(String filename) throws FileNotFoundException {
	LinkedListStack<Character> stack = new LinkedListStack<>(); // The elements pushed to stack
	LinkedListStack<Character> symbolExpectedStack = new LinkedListStack<>(); // The elements we expect to pop with it's matched symbol

	int lineNum = 0; // lineNum, column will be used to display where the error is

	String caseStatus = "commentOperatorsOFF"; // For switch chase in order to figure out what we should do with the symbols

	// Read in the file
	try (Scanner input = new Scanner(new FileReader(filename))) {
	    // Is ready if the buffer is not empty
	    while (input.hasNextLine()) {

		lineNum++; // Increment line number for each read line
		int column = 0; // Start column at 0 for each new line read in the file

		String lineRead = input.nextLine(); // Read the line and apply to a string to evaluate
		char[] lineToCharArray = lineRead.toCharArray(); // Convert string to char[] array to character evaluation

		// Traverse through each character 1 by 1
		for (int index = 0; index < lineToCharArray.length; index++) {

		    column++; // Each column is a character
		    symbolRead = lineToCharArray[index];

		    // First make sure we don't go out of our range
		    if (index != lineToCharArray.length - 1) {

			// If the index equals the symbol '/' and the next character is also '/'
			if (symbolRead == '/' && lineToCharArray[index + 1] == '/') {
			    break; // Just break out of this for loop since we ignore commented out lines
			}
			// Check for block comments (multi-line comments)
			else if (symbolRead == '/' && lineToCharArray[index + 1] == '*'
				&& !caseStatus.equals("commentOperatorsON")) {
			    caseStatus = "commentOperatorsON"; // We know we are using a block comment, so String to ON
			}
			// Check for the end portion of the block comment
			else if (symbolRead == '*' && lineToCharArray[index + 1] == '/'
				&& caseStatus.equals("commentOperatorsON")) {
			    caseStatus = "commentOperatorsOFF"; // Switch to off since we know we have ended the block comment
			}
			// Ignore anything in Strings. If we see the first '"' symbol, turn caseStatus = stringON
			else if (symbolRead == '"' && !caseStatus.equals("stringON")) {
			    caseStatus = "stringON";
			}
			// When we reach another '"' symbol, make sure it doesn't have a '\' symbol before it
			else if (symbolRead == '"'
				&& (lineToCharArray[index - 1] != '\\' || caseStatus.equals("stringON"))) {
			    caseStatus = "commentOperatorsOFF";
			}
			// This just makes sure that it's just a character. If so, break out for symbol checking
			else if (symbolRead == '\'' && lineToCharArray[index + 2] == '\'') {
			    break;
			}
			// Check if index is at a escape sequence such as \n or \t
			else if (symbolRead == '\\') {
			    index++;
			    continue;
			}
		    }

		    // Balance symbol checking while reading the file
		    if (caseStatus.equals("commentOperatorsOFF")) {
			ERROR_IN_STACK = unbalancedSymbolChecker(stack, symbolExpectedStack, symbolRead, lineNum,
				column);

			if (!ERROR_IN_STACK.equals("No error in stack.")) {
			    return ERROR_IN_STACK;
			}
		    }

		}
	    }
	    // NO MORE LINES TO READ IN FILE, CHECK FOR OTHER ERRORS
	    // CHECK FOR UNFINISHED COMMENTS
	    if (caseStatus.equals("commentOperatorsON")) {
		return unfinishedComment();
	    }
	    // If caseStatus is stringON, that means that symbols match since everything in the string is ignored
	    else if (caseStatus.equals("stringON")) {
		return allSymbolsMatch();
	    }

	    // If the stack is not empty, we know there's something wrong
	    if (!stack.isEmpty()) {
		//		symbolExpected = symbolExpectedStack.peek();
		char last = stack.pop();
		switch (last) {
		case '[':
		    symbolRead = '[';
		    return unmatchedSymbolAtEOF(']');

		case '{':
		    symbolRead = '{';
		    return unmatchedSymbolAtEOF('}');

		case '(':
		    symbolRead = '(';
		    return unmatchedSymbolAtEOF(')');

		default:
		    break;
		}
	    }
	    else {
		return allSymbolsMatch(); // We made it to the end! No errors
	    }
	    input.close();
	} catch (FileNotFoundException e) {
	    throw new FileNotFoundException();
	}
	return allSymbolsMatch();
    }

    /**
     * Symbol checker that utilizes on stack. It checks for balanced and/or
     * unbalanced symbols (without matching).
     * 
     * @param stack
     *            - the stack object in its current state (size, elements in the
     *            stack)
     * @param symbolExpectedStack
     *            - the symbol expected but was not
     * @param column
     *            - the column in where the unmatched symbol was located
     * @param lineNum
     *            - the line in where the unmatched symbol was located
     */
    private String unbalancedSymbolChecker(LinkedListStack<Character> stack,
	    LinkedListStack<Character> symbolExpectedStack, char symbolRead, int lineNum, int column) {
	// Check for any of these opening symbols at the current char in the stack
	if (symbolRead == '(' || symbolRead == '{' || symbolRead == '[') {
	    // Check which symbol it was with switch case
	    switch (symbolRead) {
	    case '(':
		stack.push(symbolRead); // push the character onto stack
		symbolExpectedStack.push(')'); // Push the balanced symbol to another stack 
		break;

	    case '{':
		stack.push(symbolRead);
		symbolExpectedStack.push('}');
		break;

	    case '[':
		stack.push(symbolRead);
		symbolExpectedStack.push(']');
		break;

	    default:
		break;
	    }
	}

	// Check for these closing symbols at the current char in the stack
	if (symbolRead == ')' || symbolRead == '}' || symbolRead == ']') {

	    // If the stack is empty, we know there it's balanced symbol can't be popped from stack
	    if (stack.isEmpty()) {
		return ERROR_IN_STACK = unmatchedSymbol(lineNum, column, symbolRead, ' ');
	    }

	    symbolExpected = symbolExpectedStack.peek();

	    // Check for the balanced symbol on the stack
	    switch (symbolRead) {
	    case ')':
		// If the top of the stack doesn't match the symbol it's suppose to balance with...
		if (stack.peek() != '(') {
		    ERROR_IN_STACK = unmatchedSymbol(lineNum, column, symbolRead, symbolExpected);
		}
		else {
		    // Else, we can pop the symbol both from the stack
		    stack.pop();
		    symbolExpectedStack.pop();
		    break;
		}
	    case '}':
		if (stack.peek() != '{') {
		    ERROR_IN_STACK = unmatchedSymbol(lineNum, column, symbolRead, symbolExpected);
		}
		else {
		    stack.pop();
		    symbolExpectedStack.pop();
		    break;
		}
	    case ']':
		if (stack.peek() != '[') {
		    ERROR_IN_STACK = unmatchedSymbol(lineNum, column, symbolRead, symbolExpected);
		}
		else {
		    stack.pop();
		    symbolExpectedStack.pop();
		}
		break;

	    default:
		break;
	    }

	}
	return ERROR_IN_STACK; // The message of the error in the stack
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