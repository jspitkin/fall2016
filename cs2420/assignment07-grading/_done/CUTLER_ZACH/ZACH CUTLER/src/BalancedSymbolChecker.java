package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Zachary Cutler, u1025642
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		int currentRow = 0;
		int currentColumn = 0;
		Scanner scanLine;
		LinkedListStack<Character> list = new LinkedListStack<Character>();
		File thisFile = new File(filename);
		scanLine = new Scanner(thisFile);
		boolean quote = false;
		boolean comment = false;
		while (scanLine.hasNextLine()) {
			currentRow++;
			currentColumn = 0;
			String currentLine = scanLine.nextLine();
			char[] charArray = currentLine.toCharArray();
			char prev = ' ';
			for(char current : charArray){ 
				currentColumn++;
				if ((current == '{' || current == '[' || current == '(') && !comment && !quote) {
					list.push(current);
				}
				else if (current == '}' && !comment && !quote) {
					if(list.isEmpty()){
						return unmatchedSymbol(currentRow, currentColumn, current, ' ');
					}
					if (list.peek() == '{') {
						list.pop();
					} else {
						return unmatchedSymbol(currentRow, currentColumn, current, swap(list.peek()));
					}
				} 
				else if (current == ']' && !comment && !quote) {
					if(list.isEmpty()){
						return unmatchedSymbol(currentRow, currentColumn, current, ' ');
					}
					if (list.peek() == '[') {
						list.pop();
					} else {
						return unmatchedSymbol(currentRow, currentColumn, current, swap(list.peek()));
					}
				} 
				else if (current == ')' && !comment && !quote) {
					if(list.isEmpty()){
						return unmatchedSymbol(currentRow, currentColumn, current, ' ');
					}
					if (list.peek() == '(') {
						list.pop();
					} else {
						return unmatchedSymbol(currentRow, currentColumn, current, swap(list.peek()));
					}
				} 
				else if (prev == '/' && current == '/' && !quote) {
					break;
				}
				else if ((prev == '/' && current == '*' && !comment) || (prev == '*' && current == '/') && !quote) {
					if(comment){
						comment = false;
					}
					else{
						comment = true;
					}
					
				}
				else if(current == '"' && !comment && prev != '\\'){
					if(quote){
						quote = false;
					}
					else{
						quote = true;
					}
					
				}
				else if(Character.toString(current).equals("'") && !comment && prev != '\\'){
					if(quote){
						quote = false;
					}
					else{
						quote = true;
					}
				}
				prev = current;
			}
		} 
		if(comment){
			return unfinishedComment();
		}
		else if(list.isEmpty()){
			return allSymbolsMatch();
		}
		else{
			return unmatchedSymbolAtEOF(swap(list.peek()));
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
	/**
	 * helper method which swaps the inputted character if it is either { [ or (, used to switch it around
	 * when peeking, returns blank if any other character is put in.
	 * @param c - type of bracket to be swapped
	 * @return swapped bracket
	 */
	private char swap(char c){
		if(c == '{'){
			return '}';
		}
		else if(c == '['){
			return ']';
		}
		else if(c == '('){
			return ')';
		}
		else if(c == '}'){
			return '{';
		}
		else if(c == ']'){
			return '[';
		}
		else if(c == ')'){
			return '(';
		}
		return ' ';
	}
}