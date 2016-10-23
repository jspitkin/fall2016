package assignment07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Scott Krstyen U0760822
 */
public class BalancedSymbolChecker {
	
	int lineNumber = 1;
	int colNumber = 1;
	char symbolRead;
	char symbolOpener;
	char symbolExpected;
	char tempChar;
	
	String result;
	BufferedReader br;
	LinkedListStack<Character> stack;

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 * @throws IOException 
	 */
	public String checkFile(String filename) throws IOException {
		
		colNumber = 1;
		lineNumber = 1;
		
		stack = new LinkedListStack<Character>();
		
		File file = new File(filename);
	
		br = new BufferedReader(new FileReader(filename));
		
		while(br.ready()){
			symbolRead = (char) br.read();
			
			switch(symbolRead){
			
			case('('):
				pushAndIncrement(symbolRead);
				break;
				
			case('{'):
				pushAndIncrement(symbolRead);
				break;
				
			case('['):
				pushAndIncrement(symbolRead);
				break;
				
			case(')'):
				result = popCompareAndIncrement(symbolRead);
				if(result != null){
				return result;
				}
				break;
				
			case('}'):
				result = popCompareAndIncrement(symbolRead);
				if(result != null){
					return result;
				}
	  			break;
	  			
			case(']'):
				result = popCompareAndIncrement(symbolRead);
				if(result != null){
					return result;
				}
	  			break;
	  			
			case('\n'):
				lineNumber++;
				colNumber = 1;
				break;
					
			case('/'):
				result = checkForCommentsAndIncrement(symbolRead);
				if(result != null){
					return result;
				}
				break;
				
			case('\''):
				skipStringOrCharacterLiteral(symbolRead);
				break;	
				
			case('"'):
				skipStringOrCharacterLiteral(symbolRead);
				break;
				
			default:
				colNumber++;
				break;
			}
			
		}
		
		if(stack.isEmpty()){
			return allSymbolsMatch();
		}else{
			return unmatchedSymbolAtEOF(getSymbolExpected((char) stack.pop()));
		}
		
		
	}
	
	/**
	 * Pushes the given symbol onto the stack.
	 * @param symbol
	 */
	private void pushAndIncrement(char symbol){
		stack.push(symbol);
		colNumber++;
	}
	
	/**
	 * If stack is not empty, pops and compares the elements. If stack is empty or symbol is not the expected, 
	 * then this method will return a certain error.
	 * @param symbolRead
	 * @return If no error this will return null, else it will return the error string.
	 */
	private String popCompareAndIncrement(char symbolRead){
		
		
		if(stack.isEmpty()){
			return unmatchedSymbol(lineNumber, colNumber, symbolRead, ' ');
		}
		
		symbolOpener = stack.pop();
		symbolExpected = getSymbolExpected(symbolOpener);
		
		
		
		if(symbolRead != symbolExpected){
			return unmatchedSymbol(lineNumber, colNumber, symbolRead, symbolExpected);
		}
		
		colNumber++;
		return null;
	}
	
	/**
	 * Will check to see if and what type of comment this is, and will skip through until the end of the comment.
	 * 
	 * @param symbolRead
	 * @return Will either return null, an error, or an all symbols match string depending on result.
	 * @throws IOException
	 */
	private String checkForCommentsAndIncrement(char symbolRead) throws IOException{
		colNumber++;
		tempChar = (char) br.read();
		colNumber++;
		if(tempChar == '/'){
			boolean lineCommentAlive = true;
			while(lineCommentAlive){
				symbolRead = (char) br.read();
				if(symbolRead == '\n'){
					lineCommentAlive = false;
				}
				colNumber++;
				if(br.ready() == false){
					if(stack.isEmpty()){
						return allSymbolsMatch();
					}else{
						return unmatchedSymbolAtEOF(getSymbolExpected((char) stack.pop()));
					}
				}
			}
			lineNumber++;
			colNumber = 1;
		}else if(tempChar == '*'){ 
			boolean blockCommentAlive = true;
			while(blockCommentAlive){
				symbolRead = (char) br.read();
				colNumber++;
				if(symbolRead == '*'){
					tempChar = (char) br.read();
					colNumber++;
					if(tempChar == '/'){
						blockCommentAlive = false;
					}else if(symbolRead == '\n'){
						lineNumber++;
						colNumber = 1;
					}
					
				}else if(symbolRead == '\n'){
					lineNumber++;
					colNumber = 1;
				}
				if(br.ready() == false){
					return unfinishedComment();
				}
			}
		
		}
		return null;
	}
	
	/**
	 * Will skip to the end of a string or character literal, ignoring the inside.
	 * @param symbolRead
	 * @throws IOException
	 */
	private void skipStringOrCharacterLiteral(char symbolRead) throws IOException{
		colNumber++;
		
		switch(symbolRead){
		
		case('"'):
			
			boolean doubleQuoteAlive = true;
			while(doubleQuoteAlive){
				symbolRead = (char) br.read();
				colNumber++;
				if(symbolRead == '"'){
					doubleQuoteAlive = false;
				}else if (symbolRead == '\\'){
					symbolRead = (char) br.read();
					colNumber++;
				}
			}
			
			break;
			
		case('\''):
			
			boolean characterLiteralAlive = true;
			while(characterLiteralAlive){
				symbolRead = (char) br.read();
				colNumber++;
				if(symbolRead == '\''){
					characterLiteralAlive = false;
				}else if (symbolRead == '\\'){
					symbolRead = (char) br.read();
					colNumber++;
				}
			}
			break;
		
			
		}
	}

	/**
	 * Will return the symbol that is expected depending on the symbolOpener input.
	 * @param symbolOpener
	 * @return symbolExpected
	 */
	private char getSymbolExpected(char symbolOpener) {
		switch(symbolOpener){
			case('('): return ')';
			case('{'): return '}';
			case('['): return ']';
			default: return 'a';		
		}
		
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