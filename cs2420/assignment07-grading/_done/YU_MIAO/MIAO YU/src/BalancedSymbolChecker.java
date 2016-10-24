package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Miao Yu
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		int lineNumber = 1;
		int colNumber = 1;
		char symbolRead;
		boolean isBlockComment = false;
		boolean isComment = false;
		boolean isString = false;
		boolean isChar = false;
		boolean isChecked = false;
		LinkedListStack<Character> stack = new LinkedListStack();
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine()){
			
			String line = scan.nextLine();
			for(int i=0;i<line.length();i++){

				if(i<line.length()-1){
					if(isComment==false && line.charAt(i)=='/'&&line.charAt(i+1)=='*'){
						isBlockComment = true;
					}
					if(isBlockComment == false && line.charAt(i)=='/'&&line.charAt(i+1)=='/'){
						isComment = true;
					}
				}
				if(isBlockComment == true){
					if(i<line.length()-1){
						if(line.charAt(i)=='*'&&line.charAt(i+1)=='/'){
							isBlockComment = false;
						}
					}
				}

				if(isBlockComment==false&&isComment == false){
					symbolRead = line.charAt(i);
					if(isChecked==true&&isChar==false&&symbolRead==34){
						isString = false;
					}
					if(isChecked==true&&isString==false&&symbolRead==39){
						isChar = false;
					}
					if(isChecked==false&&isChar==false&&symbolRead==34){
						isString = true;
						isChecked = true;						
					}
					if(isChecked==false&&isString==false&&symbolRead==39){
						isChar = true;
						isChecked = true;
					}
					if(i!=0&&line.charAt(i-1)==92&&symbolRead==34){
						isString=true;
					}
					if(i!=0&&line.charAt(i-1)==92&&symbolRead==39){
						isChar=true;
					}

					if(isString==false&&isChar==false){
						isChecked=false;
						if(symbolRead=='('||symbolRead=='['||symbolRead=='{'){
							stack.push(symbolRead);
						}
						if(stack.isEmpty()==false){
							if(symbolRead==')'&&stack.peek()!='('){
								if(stack.peek()=='['){
									return unmatchedSymbol(lineNumber, colNumber, symbolRead, ']');
								}
								if(stack.peek()=='{'){
									return unmatchedSymbol(lineNumber, colNumber, symbolRead, '}');
								}
							}
							if(symbolRead==']'&&stack.peek()!='['){
								if(stack.peek()=='('){
									return unmatchedSymbol(lineNumber, colNumber, symbolRead, ')');
								}
								if(stack.peek()=='{'){
									return unmatchedSymbol(lineNumber, colNumber, symbolRead, '}');
								}
							}
							if(symbolRead=='}'&&stack.peek()!='{'){
								if(stack.peek()=='('){
									return unmatchedSymbol(lineNumber, colNumber, symbolRead, ')');
								}
								if(stack.peek()=='['){
									return unmatchedSymbol(lineNumber, colNumber, symbolRead, ']');
								}					
							}
						}
						if(stack.isEmpty()==true){
							if(symbolRead=='}'||symbolRead==']'||symbolRead==')'){
								return unmatchedSymbol(lineNumber, colNumber, symbolRead, ' ');						
							}
						}
						
						if(symbolRead==')'&&stack.peek()=='('){
							stack.pop();

						}
						if(symbolRead==']'&&stack.peek()=='['){
							stack.pop();
						}
						if(symbolRead=='}'&&stack.peek()=='{'){
							stack.pop();
						}	
					}
		
				}

				colNumber++;

			}
			colNumber = 1;
			isComment = false;
			isString = false;
			isChar = false;
			lineNumber++;
		}
		if(isBlockComment == true){
			return unfinishedComment();
		}
		if(isBlockComment == false&&stack.isEmpty()==false){
			if(stack.peek()=='('){
				return unmatchedSymbolAtEOF(')');
			}
			if(stack.peek()=='['){
				return unmatchedSymbolAtEOF(']');
			}
			if(stack.peek()=='{'){
				return unmatchedSymbolAtEOF('}');
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
}