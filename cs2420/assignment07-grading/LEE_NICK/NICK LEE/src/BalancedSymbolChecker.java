package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched. Does not read inside java style comments,
 * double or single quotes, or escaped quote characters.
 * 
 * @author Nickolas Lee
 * Created: Oct 18, 2016
 */
public class BalancedSymbolChecker {

	LinkedListStack<Character> stack;

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		stack = new LinkedListStack<Character>();
		int lineNumber = -1;
		int colNumber = -1;
		char symbolRead = '?';
		char symbolExpected = '?';
		char previousLetter = '?';
		char letter = '?';
		char stackLetter = '?';
		boolean quote = false;
		boolean comment = false;
		boolean reachedEnd = false;
		boolean extraBrace = false;
		
		// read file
		ArrayList<String> input = readFile(filename);

		// analyze file
		forLine: // Labels the outer for loop so can break out of it below
			for(String line : input){ // for each line
				lineNumber++; // effectively starts counting at zero

				for(int let = 0; let < line.length(); let++){ // for each letter
					colNumber = let + 1; 
					letter = line.charAt(let);
					symbolRead = letter;
					
					if(stack.size() != 0){ // prevents exception on empty stack
						stackLetter = stack.peek();
					}

					if(previousLetter == '/' && letter == '/'){ // whole line comment
						break;
					}

					if(previousLetter == '/' && letter == '*'){ // opening comment
						comment = true;
					}

					if(previousLetter == '*' && letter == '/'){ // closing comment
						comment = false;
					}

					if(previousLetter != '\\' && letter == '\"' || letter == '\''){ // ignore double and single quotes and escaped quotes
						if(!quote){ 
							quote = true;
						}
						else if(quote){
							quote = false;
							continue; // skip checking the second quote char
						}
					}

					if(quote || comment){
						previousLetter = letter;
						continue;
					}
					// no more comments after this point
					
					if(letter == '(' || letter == '[' || letter == '{'){
						stack.push(letter);
					}
					else if(letter == ')'){ 
						if(stack.size() != 0 && stackLetter == '('){ // checking for empty stack prevents exception in pop
							stack.pop();
						}
						else if(stack.size() == 0){
							symbolExpected = ' ';
							extraBrace = true;
							break forLine;
						}
						else{ // stack not empty and character in the stack does not match
							break forLine;
						}
					}
					else if(letter == ']'){
						if(stack.size() != 0 && stackLetter == '['){
							stack.pop();
						}
						else if(stack.size() == 0){
							symbolExpected = ' ';
							extraBrace = true;
							break forLine;
						}
						else{
							break forLine;
						}
					}
					else if(letter == '}'){
						if(stack.size() != 0 && stackLetter == '{'){
							stack.pop();			
						}
						else if(stack.size() == 0){
							symbolExpected = ' ';
							extraBrace = true;
							break forLine;
						}
						else{
							break forLine;
						}
					}
					previousLetter = letter;
				}
				
				if(lineNumber >= input.size()){ // both should be zero indexed 
					reachedEnd = true;
				}
			}

		// expect the complement of the character left on the stack
		if(!reachedEnd && !extraBrace){
			if(stackLetter == '('){
				symbolExpected = ')';
			}
			else if(stackLetter == '['){
				symbolExpected = ']';
			}
			else if(stackLetter == '{'){
				symbolExpected = '}';
			}
		}

		// report outcome
		String message = "empty";
		if(stack.size() == 0 && !extraBrace){ 
			message = allSymbolsMatch();
		}
		else if(stack.size() == 1){
			message = unmatchedSymbolAtEOF(symbolExpected);
		}
		else if(comment){
			// then never found the end of the comment, need a */
			message = unfinishedComment();
		}
		else{ 
			message = unmatchedSymbol(++lineNumber, colNumber, symbolRead, symbolExpected);
		}

		return message;
	}

	/**
	 * Opens and extracts the data from a file into a list of strings. 
	 * One String per line. If the file does not exist it prints an error message. 
	 * 
	 * @param filename
	 */
	public ArrayList<String> readFile(String filename) { 
		ArrayList<String> toBeAdded = new ArrayList<String>();

		try (Scanner fileIn = new Scanner(new File(filename))) {
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				toBeAdded.add(line);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			return null;
		} 

		return toBeAdded;
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