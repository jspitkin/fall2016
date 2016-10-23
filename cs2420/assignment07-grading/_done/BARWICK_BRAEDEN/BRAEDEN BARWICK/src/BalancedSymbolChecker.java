package assignment07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Braeden Barwick u0959391
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		//brace for ugly code D:
		try (FileReader reader = new FileReader(filename)){
			
			char character = 0;
			boolean inSingleComment = false;
			boolean inComment = false;
			boolean checkComment = false;
			boolean ignoreBrackets = false;
			boolean inString = false;
			boolean inCharacter = false;
			boolean currentCharLiteral = false;
			int column = 0;
			int row = 1;
			int intChar;
			
			//loops until the file ends (break)
			while (true){

				//get next character
				intChar = reader.read();
				//read() returns -1 if at the end of the file
				if(intChar == -1)
					break;
				
				character = (char) intChar;
				column++;
				
				//skip one character if it is defined as a literal
				if(character == '\\'){
					currentCharLiteral = true;
					
					intChar = reader.read();
					if(intChar == -1)
						break;
					
					character = (char) intChar;
					column++;
				}
				
				//iterate through comments
				if(checkComment){
					if(character == '/'){
						inComment = false;
						checkComment = false;
						intChar = reader.read();
						if(intChar == -1)
							break;
						
						character = (char) intChar;
						column++;
					}		
				}
				
				//iterate through comments/find new-line characters
				if(character == '\n'){
					inSingleComment = false;
					row++;
					column = 0;
					intChar = reader.read();
					if(intChar == -1)
						break;
					
					character = (char) intChar;
					column++;
				}
				
				//check for end to comment block
				if(inComment){
					if(character == '*'){
						checkComment = true;
					}
				}
				
				//check for end to string literal, ignoring any escaped " marks
				if(inString){
					if(character == '\"' && !currentCharLiteral){
						inString = false;
						intChar = reader.read();
						if(intChar == -1)
							break;
						
						character = (char) intChar;
						column++;
					}
				}
				
				//check for end to character literal
				if(inCharacter){
					if(character == '\''){
						inCharacter = false;
						intChar = reader.read();
						if(intChar == -1)
							break;
						
						character = (char) intChar;
						column++;
					}
				}
				
				currentCharLiteral = false;
				
				//if no longer ignoring any symbols due to comments or literals
				if (!inComment && !inSingleComment && !inString && !inCharacter){
							
					//skip one character if it is defined as a literal
					if(character == '\\'){
						currentCharLiteral = true;
					}
					
					//checks for /* */ comment block and // comment line, but ignores singular / backslashes
					if(character == '/'){
						intChar = reader.read();
						if(intChar == -1)
							break;
						character = (char) intChar;
						column++;
						
						if(character == '/'){
							inSingleComment = true;
							ignoreBrackets = true;
						}
						else if(character == '*'){
							inComment = true;
							ignoreBrackets = true;
						}
					}
					
					//checks for a string literal
					if(character =='\"'){
						ignoreBrackets = true;
						inString = true;
					}
					
					//checks for a character literal
					if(character == '\''){
						ignoreBrackets = true;
						inCharacter = true;
					}
					
					//only move on if no comment block/line or literal was found
					if(!ignoreBrackets) {
						//push opening symbols onto the stack
						if(character == '(' || character == '{' || character == '[')
							stack.push(character);
						
						//check closing symbols against the stack, and return the proper error message if they don't match
						else if(character == ')' || character == '}' || character == ']'){
							//if the stack has no more opening brackets, the 'expected' character is a blank space
							char expected = 32;
							
							if(!stack.isEmpty()){
								if(stack.peek() == '{')
									expected = '}';
								
								else if(stack.peek() == '(')
									expected = ')';
								
								else if(stack.peek() == '[')
									expected = ']';
							}
							
							if(character != expected)
								return unmatchedSymbol(row, column, character, expected);
							else
								stack.pop();
						}
					}
				}
				ignoreBrackets = false;
			}
			//if the file ends while in a /* */ comment block
			if(inComment)
				return unfinishedComment();
			
			//checks a non-empty stack for the correct End of File unmatched symbol message
			if(!stack.isEmpty()){
				if(stack.peek() == '{')
					return unmatchedSymbolAtEOF('}');
				if(stack.peek() == '(')
					return unmatchedSymbolAtEOF(')');
				if(stack.peek() == '[')
					return unmatchedSymbolAtEOF(']');
			}
			reader.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		//if the file ends with no open comments or unmatched symbols, the file is symbol-balanced
		return allSymbolsMatch();
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
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