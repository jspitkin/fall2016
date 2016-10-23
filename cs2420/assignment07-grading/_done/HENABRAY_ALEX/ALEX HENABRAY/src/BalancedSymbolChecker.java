package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Alex Henabray (uID: u0795787), last updated: 10-12-16
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {

		int lineNumber = 0, colNumber;

		// Check that file exists. If not, throw FileNotFound exception.

		try(Scanner file = new Scanner(new File(filename))) {

			//Scanner currentLineScanner;
			LinkedListStack<Character> charStack = new LinkedListStack<Character>();

			while(file.hasNextLine()) {

				String currentLine = file.nextLine();
				colNumber = 1; // At the beginning of each line, the colNumber = 1.
				lineNumber++; // Increment the line number every time nextLine() is called.

				//currentLineScanner = new Scanner(currentLine);

				char[] charArray = currentLine.toCharArray();


				// Check for comments
				if(currentLine.contains("//") || currentLine.contains("/*") || currentLine.contains("\"") || 
						currentLine.contains("'")) {

					for(int index = 0; index < charArray.length; index++ ) {

						if(charArray[index] == '/') {

							// If the next character after '/' is '/', there is a comment in charArray.
							// Remove all of the characters from charArray[index] to charArray[charArray.length - 1]

							if( index != charArray.length - 1 ) {

								if(charArray[index + 1] == '/') {

									for(int removeIndex = index; removeIndex < charArray.length; removeIndex++ ) {
										charArray[removeIndex] = ' ';
									}				

								}
								else if(charArray[index + 1] == '*') {

									if(file.hasNextLine() == false) {
										file.close();
										return unfinishedComment();
									}

									for(int removeIndex = index; removeIndex < charArray.length; removeIndex++ ) {
										charArray[removeIndex] = ' ';
									}		

									// check symbols before comment on current line?

									while(file.hasNextLine() && currentLine.contains("*/") == false) {

										currentLine = file.nextLine();
										lineNumber++;
										colNumber = 1;

									}

									if(file.hasNextLine() == false) {
										return unfinishedComment();
									}
								}
							}
						}
						else if(charArray[index] == '"') {

							charArray[index] = ' ';

							int count = 1;

							for(int countIndex = index + 1; countIndex < charArray.length - 1; countIndex++) {

								if(charArray[countIndex] == '\"') {
									count++;
								}

							}

							if(count > 2) {

								int count2 = 0;
								for( int count2Index = index; count2Index < charArray.length; count2Index++	) {

									if(charArray[count2Index] == '\"') {
										count2++;

										if(count2 == 2) {
											charArray[count2Index] = ' ';
											break;
										}
										else {
											charArray[count2Index] = ' ';
										}

									}
									else {
										charArray[count2Index] = ' ';
									}

								}
							}
							else {
								
								charArray[index] = ' ';
								
								for(int index2 = index + 1; index2 < charArray.length - 1; index2++) {
									
									if(charArray[index2] == '\"') {
										charArray[index2] = ' ';
										break;
									}
									
									charArray[index2] = ' ';
								}
								
							}
						}
				
					else if(charArray[index] == '\'') {

						charArray[index] = ' ';
						charArray[index + 1] = ' ';
						charArray[index + 2] = ' ';
					}
				}
			}

			// for-each loop checks for (, [, {, ), ], }

			char poppedChar;

			for(char symbol : charArray) {

				if(symbol == '(' || symbol == '[' || symbol == '{' 
						|| symbol == ')' || symbol == ']' || symbol == '}') {

					if(symbol == '(') {

						charStack.push('(');

					}
					else if(symbol == '[') {

						charStack.push('[');

					}
					else if(symbol == '{') {

						charStack.push('{');

					}
					else if(symbol == ')') {

						if(charStack.isEmpty() == true) {
							return unmatchedSymbol(lineNumber, colNumber, ')', ' ');
						}

						poppedChar = charStack.pop();

						if(poppedChar == '[') {
							return unmatchedSymbol(lineNumber, colNumber, ')', ']');
						}
						else if(poppedChar == '{'){
							return unmatchedSymbol(lineNumber, colNumber, ')', '}');
						}

					}
					else if(symbol == ']') {

						if(charStack.isEmpty() == true) {
							return unmatchedSymbol(lineNumber, colNumber, ']', ' ');
						}

						poppedChar = charStack.pop();

						if(poppedChar == '(') {
							return unmatchedSymbol(lineNumber, colNumber, ']', ')');
						}
						else if(poppedChar == '{'){
							return unmatchedSymbol(lineNumber, colNumber, ']', '}');
						}

					}
					else if(symbol == '}') {

						if(charStack.isEmpty() == true) {
							return unmatchedSymbol(lineNumber, colNumber, '}', ' ');
						}

						poppedChar = charStack.pop();

						if(poppedChar == '(') {
							return unmatchedSymbol(lineNumber, colNumber, '}', ')');
						}
						else if(poppedChar == '['){
							return unmatchedSymbol(lineNumber, colNumber, '}', ']');
						}

					}


				}
				colNumber++;		
			}
		}

		// Check the stack at the end of the file

		if(file.hasNextLine() == false){

			if(charStack.isEmpty() == true)	 {

				file.close();
				return allSymbolsMatch();

			}
			else {

				file.close();

				char poppedChar = charStack.pop();

				if(poppedChar == '(') {
					return unmatchedSymbolAtEOF(')');
				}
				else if(poppedChar == '[' ) {
					return unmatchedSymbolAtEOF(']');
				}
				else {
					return unmatchedSymbolAtEOF('}');
				}
			}
		}
	}
	catch(FileNotFoundException e) {
		System.err.println(e.getMessage());
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
}