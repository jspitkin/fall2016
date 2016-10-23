package assignment07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Makenzie Peacock (u0873188)
 */
public class BalancedSymbolChecker {
	LinkedListStack<Character> symbolStack = new LinkedListStack<Character>();

	/**
	 * Checks if a file has all of its symbols matched.
	 * 
	 * @param filename - the name of the file in string format.
	 * 
	 * @return a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		String result = null;
		int lineNumber = 0;
		int columnNumber;


		Scanner fileScanner = new Scanner(new FileReader(filename));

		while(fileScanner.hasNextLine()){
			lineNumber++;
			char previous = ' ';
			String nextLine = fileScanner.nextLine();
			for(int index = 0; index < nextLine.length(); index++){
				columnNumber = index + 1;
				char charAtIndex = nextLine.charAt(index);

				//handles comments
				if(charAtIndex == '/' && previous == '/')
					break;

				//handles block comments
				else if(charAtIndex == '*' && previous == '/'){
					if(!hasIgnoreStarted()){
						symbolStack.push(charAtIndex);
						previous = charAtIndex;
						continue;
					}
				}
				else if(charAtIndex == '/' && previous == '*'){
					if(hasIgnoreStarted() && hasIgnoreEnded(previous)){
						symbolStack.pop();
						previous = charAtIndex;
						continue;
					}
				}

				//handles string and char literals
				else if((charAtIndex == '"'|| charAtIndex == '\'') && previous != '\\'){
					if(!hasIgnoreStarted()){
						symbolStack.push(charAtIndex);
						previous = charAtIndex;
						continue;
					}
					else if(hasIgnoreStarted() && hasIgnoreEnded(charAtIndex)){
						symbolStack.pop();
						previous = charAtIndex;
						continue;
					}
				}



				//handles parentheses, curly brackets, and hard brackets
				if (canSkip()){
					previous = charAtIndex;
					continue;
				}
				else{
					if(isLeftSide(charAtIndex)){
						symbolStack.push(charAtIndex);
					}
					else if(isRightSide(charAtIndex)){
						if(!symbolStack.isEmpty()){
							char rightSymbol = getPeekRightSymbol();

							if(rightSymbol != charAtIndex){
								result = unmatchedSymbol(lineNumber, columnNumber, charAtIndex, rightSymbol);
								break;
							}
						}
						else{
							result = unmatchedSymbol(lineNumber, columnNumber, charAtIndex, ' ');
							break;
						}
					}
				}

				previous = charAtIndex;
			}
			if(result != null)
				break;
		}

		if(result == null){
			if(!symbolStack.isEmpty() && symbolStack.peek() == '*'){
				result = unfinishedComment();
			}

			else if(!symbolStack.isEmpty()){
				result = unmatchedSymbolAtEOF(getPeekRightSymbol());
			}
			else{
				result = allSymbolsMatch();
			}
		}

		fileScanner.close();
		symbolStack.clear();
		return result;
	}

	/**
	 * A helper method that gets the top of the stack's right half.
	 * 
	 * @return (, {, or [ depending on what symbol is at the top of the stack.
	 */
	private char getPeekRightSymbol(){
		char lastSymbol = symbolStack.pop();
		char rightSymbol = ' ';

		if(lastSymbol == '{') {
			rightSymbol = '}';
		}
		else if(lastSymbol == '('){
			rightSymbol = ')';
		}
		else if(lastSymbol == '['){
			rightSymbol = ']';
		}

		return rightSymbol;
	}

	/**
	 * A helper method to determine if a symbol is the first instance of a pair.
	 * 
	 * @param symbol - the current character
	 * 
	 * @return True if the parameter is the first instance of a pair (i.e. ", '). False if otherwise.
	 */
	private boolean isLeftSide(char symbol){
		boolean result = false;

		if (symbol == '(' || symbol == '{' || symbol == '[')
			result = true;

		return result;

	}

	/**
	 * A helper method to determine if a symbol is the second instance of a pair
	 * 
	 * @param symbol - the current character
	 * 
	 * @return True if the parameter is the second instance of a pair (i.e. ", '). False if otherwise.
	 */
	private boolean isRightSide(char symbol){
		boolean result = false;

		if (symbol == ')' || symbol == '}' || symbol == ']')
			result = true;

		return result;
	}

	/**
	 *  A helper method that determines if a literal string, literal char, or block comment has begun.
	 * 
	 * @return True if the current character is the first instance of a block comment, literal string, or literal char symbol. False if otherwise.
	 */
	private boolean hasIgnoreStarted(){
		boolean result = false;

		if(!symbolStack.isEmpty() && (symbolStack.peek() == '"' || symbolStack.peek() == '\'' || symbolStack.peek() == '*'))
			result = true;

		return result;
	}

	/**
	 * A helper method that determines if a literal string, literal char, or block comment has ended.
	 * 
	 * @param symbol - the current character
	 * 
	 * @return True if the current character is the second instance of a block comment, literal string, or literal char symbol. False if otherwise.
	 */
	private boolean hasIgnoreEnded(char symbol) {
		boolean result = false;

		if(!symbolStack.isEmpty() && symbolStack.peek() == symbol)
			result = true;

		return result;
	}

	/**
	 * A helper method that determines if the current character can be ignored.
	 * 
	 * @return True if the current character is in between a literal string, literal char, or block comment. False if otherwise.
	 */
	private boolean canSkip(){
		return !symbolStack.isEmpty() && (symbolStack.peek() == '"' || symbolStack.peek() == '\'' || symbolStack.peek() == '*');
	}

	/**
	 * Prints out an error message entailing where the file has an unmatched symbol.
	 * 
	 * @param lineNumber - Integer that indicates what line the error is located.
	 * @param colNumber - Integer that indicates what column the error is located.
	 * @param symbolRead - The current character.
	 * @param symbolExpected - The symbol that is needed to match with the symbol at the top of the stack.
	 * 
	 * @return an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Prints out an error message entailing that there is an unmatched symbol at the end of the file.
	 * 
	 * @param symbolExpected - The symbol that is needed to match with the symbol at the top of the stack.
	 * 
	 * @return an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Prints out an error message entailing that there is an unfinished block comment in the file. 
	 *
	 * @return an error message for a file that ends with an open /* comment.
	 */
	private String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Prints out a completion message.
	 * 
	 * @return a message for a file in which all symbols match.
	 */
	private String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}