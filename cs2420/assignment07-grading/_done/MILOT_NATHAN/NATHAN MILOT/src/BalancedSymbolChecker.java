package assignment07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Nathan Milot u1063587
 * @since Oct 11, 2016
 */
public class BalancedSymbolChecker {
	
	// Member Variables
	private LinkedListStack<Character> stack;
	
	/**
	 * A private SymbolItem to be used to add an item to the stack and keep track of its row and column
	 * optional object to keep track of each symbols location
	 * @author Nathan Milot u1063587
	 * @since Oct 11, 2016
	 */
	@SuppressWarnings("unused")
	private class SymbolItem{
		private char data;
		private int row;
		private int column;
		
		/**
		 * Constructs a SymbolItem
		 * @param data - the symbol
		 * @param row - the row
		 * @param column - the column
		 */
		public SymbolItem(char data, int row, int column){
			this.data = data;
			this.row = row;
			this.column = column;
		}
		
		/**
		 * Returns the character in the object in string form
		 */
		@Override
		public String toString(){
			return data + "";
		}
	}
	
	/**
	 * Default constructor
	 */
	public BalancedSymbolChecker(){
		stack = new LinkedListStack<>();
	}
	
	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		stack.clear();
		int lineNumber = 0;
		boolean ignoreSection = false;
		try {
			String currentLine = "";
			while((currentLine = reader.readLine()) != null){
				lineNumber++;
				int colNumber = 0;
				boolean ignoreLine = false;
				
				for(int i = 0; i < currentLine.length(); i++){
					colNumber++;
					char c = currentLine.charAt(i);
					
					if(!ignoreLine){
						//Check ignore line aka "//"
						if(i > 0 && c =='/' && currentLine.charAt(i - 1) == '/')
							ignoreLine = true;
						//Check ignore section aka '' and "" and /* */
						if(i > 0 && c =='*' && currentLine.charAt(i - 1) == '/')
							ignoreSection = true;
						else if(i > 0 && c =='/' && currentLine.charAt(i - 1) == '*')
							ignoreSection = false;
						
						if(!ignoreSection && c == '\"')
							ignoreSection = true;
						else if(ignoreSection && c == '\"' && currentLine.charAt(i - 1) != '\\')
							ignoreSection = false;
						
						if(!ignoreSection && c == '\'')
							ignoreSection = true;
						else if(ignoreSection && c == '\'' && currentLine.charAt(i - 1) != '\\')
							ignoreSection = false;
					}
					
					if(!ignoreSection && !ignoreLine){
						switch(c){
						case '(':
						case '[':
						case '{':
							stack.push(c);					
							break;
						case ')':
						case ']':
						case '}':
							if(stack.isEmpty())
								return unmatchedSymbol(lineNumber, colNumber, c, ' ');
							Character tmpItem = stack.pop();
							if(tmpItem != oppositeSymbol(c))
								return unmatchedSymbol(lineNumber, colNumber, c, oppositeSymbol(tmpItem));
							break;
						}
					}
				}
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(ignoreSection)
			return unfinishedComment();
		if(!stack.isEmpty())
			return unmatchedSymbolAtEOF(oppositeSymbol(stack.peek()));
		
		
		return allSymbolsMatch();
	}

	/**
	 * Returns the closer of the symbol
	 * @param item - symbol to be switched
	 * @return - the closer of the symbol
	 */
	private char oppositeSymbol(char item){
		char result = ' ';
		switch(item){
		case '(':
			result = ')';
			break;
		case '[':
			result = ']';
			break;
		case '{':
			result = '}';
			break;
		case ')':
			result = '(';
			break;
		case ']':
			result = '[';
			break;
		case '}':
			result = '{';
			break;
		default:
			result = ' ';
		}
		return result;
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