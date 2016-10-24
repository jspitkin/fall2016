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
 * @author Jared Nielson u0495206
 */
public class BalancedSymbolChecker {	
	
	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols.
	 * 
	 * @param filename - The name of the file to be tested. Assumes ASCII encoding in the file.
	 * @throws FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader (new File(filename)));
		
		LinkedListStack<Character> symbolStack = new LinkedListStack<Character>();
		LinkedListStack<Character> ignoreStack = new LinkedListStack<Character>();
		
		try{
			int lineNumber = 1;
			boolean ignoring = false;
			
			while(true){
				//Get the current line
				String line = reader.readLine();
				
				//If line is null we have reached the EoF and are done
				if(line == null){
					break;
				}
				
				//Iterate through the given line
				lineLoop:
				for(int charIdx= 0; charIdx < line.length(); charIdx++){
					char toCheck = line.charAt(charIdx);
					
					switch(toCheck){
					//Opening grouping symbols
					case '{':
					case '(':
					case '[':
						//If we aren't ignoring push the symbol on to the stack
						if(!ignoring){
							symbolStack.push((char) toCheck);							
						}
						break;
					//Closing grouping symbols
					case '}':
					case ')':
					case ']':
						//If we aren't ignoring
						if(!ignoring){
							//If we have something on the stack
							if(!symbolStack.isEmpty()){
								//Pop from the stack and compare return error message if it doesn't match expected
								char topSymbol = symbolStack.pop();
								if(topSymbol != matchingSymbol(toCheck)){
									reader.close();
									return unmatchedSymbol(lineNumber, charIdx + 1, toCheck, matchingSymbol(topSymbol));
								}															
							} else { 
								//We've had a closing symbol before any opening symbol return relevant message
								reader.close();
								return unmatchedSymbol(lineNumber, charIdx + 1, toCheck, ' ');
							}
						}
						break;
					//Single and double quotation handling
					case '"':
					case '\'':
						//If we aren't ignoring push the symbol onto the ignore stack and start ignoring
						if(!ignoring){
							ignoreStack.push(toCheck);
							ignoring = true;
						} else {
							//If we are ignoring peek at the symbol if it matches and is not preceded
							//by an escape character pop off the ignore stack and stop ignoring.
							if(ignoreStack.peek() == toCheck){
								if(charIdx > 0 && line.charAt(charIdx - 1) == '\\'){
									break;
								}
								ignoreStack.pop();
								ignoring = false;
							}
						}
						break;
					//Comment handling
					case '/':
						//If we aren't ignoring when we encounter a first slash check peek at the next character
						if(!ignoring && charIdx + 1 < line.length()){
							//If the next character is a slash skip to the next line
							if(line.charAt(charIdx + 1) == '/'){								
								break lineLoop;
							}
							//If it is a star push c for comment block onto the ignore stack
							if(line.charAt(charIdx + 1) == '*'){
								ignoring = true;
								ignoreStack.push('c');
							}
						} else {
							//Otherwise check to see we are ignoring,
							   if(!ignoreStack.isEmpty() && ignoreStack.peek() == 'c' && charIdx > 0){
								  //If we're in a comment block end the comment block
								   if(line.charAt(charIdx - 1) == '*'){
									   ignoreStack.pop();
									   ignoring = false;
								   }
							   }
						}
						break;
					}
					
				}
				lineNumber++;
			}
			reader.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		//Determine the appropriate message based on what is in the ignore stack
		if(!ignoreStack.isEmpty() && ignoreStack.peek() == 'c'){
				return unfinishedComment();
		}
		
		//If the ignore stack is empty check the symobolStack for unbalanced symbols
		if(!symbolStack.isEmpty()){
			return unmatchedSymbolAtEOF(matchingSymbol(symbolStack.pop()));
		}
		//If we've made it here everything is balanced
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
	
	/**
	 * Returns the matching symbol for a grouping character. Or the character itself
	 * if it is not a grouping symbol
	 */
	private char matchingSymbol(char givenSymbol){
		switch(givenSymbol){
		case '}' :
			return '{';
		case '{' :
			return '}';
		case '[' :
			return ']';
		case ']' :
			return '[';
		case '(' :
			return ')';
		case ')' :
			return '(';
		default :
			return givenSymbol;
		}
	}
	
}

