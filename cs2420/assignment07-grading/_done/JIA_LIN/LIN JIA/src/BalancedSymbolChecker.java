package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author LinJia
 * uid u1091732
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		HashMap<Character, Character> charPair = new HashMap<Character, Character>();//build hushmap to store the pairs of brackets
		charPair.put('(', ')');
		charPair.put('[', ']');
		charPair.put('{', '}');
		@SuppressWarnings("resource")
		Scanner input = new Scanner(new File(filename));//read the file
		LinkedListStack<Character> myStack = new LinkedListStack<Character>();
		int lineNumber = 1;
		boolean openCommentFound = false;//the flag to control total comment 
		boolean singleQuotates = false;//the flag to control single quote
		boolean DoubleQuotates = false;//the flag to control double quote
		boolean starQuotates = false;//the flag to control block comment


		while (input.hasNextLine()) {
			String line = input.nextLine();

			for (int i = 0; i < line.length(); i++) {
				char currentChar = line.charAt(i);

				if (i + 1 <= line.length()) {
					if (openCommentFound == false) { //no comments right now
						
						if (charPair.containsKey(currentChar)) {//if we got the right side bracket
							myStack.push(currentChar);
						} 
						else if (charPair.containsValue(currentChar)) {//if we got left side bracket
							if (!myStack.isEmpty() && charPair.get(myStack.peek()) == currentChar) {
								myStack.pop();
							}
							else if (myStack.isEmpty()) {// no more bracket in stack
								char empty = ' ';
								return unmatchedSymbol(lineNumber, i + 1, currentChar, empty);

							} 
							else {// cannot find the right pair
								return unmatchedSymbol(lineNumber, i + 1, currentChar, charPair.get(myStack.peek()));
							}
							

						}
						/*
						 * conditions that we can set the flag to true
						 */
						if(currentChar == '/' && i + 1 < line.length() && currentChar == line.charAt(i + 1)){
							break;
						}
						if(line.charAt(i) == '/' && line.charAt(i + 1) == '*'&& i + 1< line.length()){
							starQuotates = true;
							i = i+1;
						}
						if(currentChar == '\'' && line.charAt(i-1) != '\\'&& i > 0){
							singleQuotates = true;
						}
						if(currentChar == '"' && line.charAt(i-1) != '\\'&& i > 0){
							DoubleQuotates = true;
						}
						
						
					}
					/*
					 * conditions we can set the flag to false
					 */
					
					else if(currentChar == '\'' && line.charAt(i-1) != '\\' && singleQuotates == true && i > 0){
							singleQuotates = false;
					}
					else if(currentChar == '"' && line.charAt(i-1) != '\\' && DoubleQuotates == true && i > 0){
							DoubleQuotates = false;
					}
					else if(line.charAt(i) == '*' && line.charAt(i + 1) == '/'&& starQuotates == true&& i + 1< line.length()){
							starQuotates = false;
							i = i + 1;
					}
						
					
					if(singleQuotates == false && DoubleQuotates == false && starQuotates == false){
						openCommentFound = false;// when all the comment closed 
					}
					else{
						openCommentFound = true;

					}


				}
				
			}

			lineNumber++;
		}
		if (openCommentFound) {//still has comment open
			return unfinishedComment();
		}
		if (!myStack.isEmpty()) {//stack still has brackets need to close
			return unmatchedSymbolAtEOF(charPair.get(myStack.peek()));
		}

		return allSymbolsMatch();//all match!!!

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

//if (currentChar == '/' && i + 1 < line.length() && currentChar == line.charAt(i + 1)) {
//	break;
//}
//
//if (currentChar == '\'' && line.charAt(i-1) != '\\') {
//	
//	openCommentFound = !openCommentFound;
//	
//	
//} 
//
//if (currentChar == '"' && line.charAt(i-1) != '\\')  {
////	openCommentFound = !openCommentFound;
////	for(int j = line.length()-1; j > 0 ; j--){
////		if (line.charAt(j) == '"'){
//			openCommentFound = !openCommentFound;
//			
//
////		}
////	}
//
//	
//}
//
//if (line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
//	openCommentFound = true;
//} 
//if (line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
//	openCommentFound = false;
//}
