package assignment07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Logan Terry u0973436
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		//File reading
		ArrayList<String> lines = new ArrayList<String>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while (input.ready()) {
				lines.add(input.readLine());
			}
			input.close();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//File processing
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		boolean isBlockComment = false;
		boolean isString = false;
		boolean isCharacter = false;
		for(int line = 0; line < lines.size(); line++){
			char[] chars = lines.get(line).toCharArray();
			for(int character = 0; character < chars.length; character++){
				
				//Ending is before start so that something like this "/*" doesn't cause an error within block comment.
				
				//Block comment end
				if(isBlockComment){
					if(character+1 < chars.length && chars[character] == '*' && chars[character+1] == '/'){
						stack.pop();
						isBlockComment = false;
						character++;
					}
					continue;
				}
				//String end
				if(isString){
					//Special case of something like this "he said \"Hi\"" resulting in the printout of - he said "Hi"
					if(character > 0 && chars[character] == '"' && chars[character-1] == '\\'){
						continue;
					}
					if(chars[character] == '"'){
						stack.pop();
						isString = false;
					}
					continue;
				}
				//Character end
				if(isCharacter){
					//Special case of '\'' signifying the character - '
					if(character > 0 && chars[character] == '\'' && chars[character-1] == '\\'){
						continue;
					}
					if(chars[character] == '\''){
						stack.pop();
						isCharacter = false;
					}
					continue;
				}
				
				//Block comment start
				if(character+1 < chars.length && chars[character] == '/' && chars[character+1] == '*'){
					stack.push('*');
					isBlockComment = true;
					character++;
					continue;
				}	
				//String start
				if(chars[character] == '"'){
					stack.push('"');
					isString = true;
					continue;
				}
				//Character start
				if(chars[character] == '\''){
					stack.push('\'');
					isCharacter = true;
					continue;
				}
				
				//Line comment
				if(character+1 < chars.length && chars[character] == '/' && chars[character+1] == '/'){
					break;
				}
				
				//Normal case
				switch(chars[character]){
				//Add to stack
				case '(':
				case '{':
				case '[':
					stack.push(chars[character]);
					break;
				//Check against stack
				case ')':
				case '}':
				case ']':
					if(stack.isEmpty()){
						return unmatchedSymbol(line+1, character+1, chars[character], ' ');
					}
					if(getPair(stack.peek()) != chars[character]){
						return unmatchedSymbol(line+1, character+1, chars[character], getPair(stack.peek()));
					}else{
						stack.pop();
					}
				}
			}
		}
		//End of file check
		if(!stack.isEmpty()){
			switch(stack.peek()){
			case '*':
				return unfinishedComment();
			default:
				return unmatchedSymbolAtEOF(getPair(stack.peek()));
			}
		}
		
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
	 * Returns the paired character to the input character.
	 * Eg '{' returns '}'.
	 * Returns null if the pair isn't handled.
	 * @param ch - The character to return the pair of
	 * @return - The paired character
	 */
	private char getPair(char ch){
		switch (ch){
		case '{':
			return '}';
		case '(':
			return ')';
		case '[':
			return ']';
		case '"':
			return '"';
		case '\'':
			return '\'';
		default:
			return 0;
		}
	}
	
}