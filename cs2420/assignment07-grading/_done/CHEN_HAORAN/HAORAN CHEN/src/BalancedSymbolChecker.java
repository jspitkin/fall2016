package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Haoran Chen
 * @uid u1060286
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		LinkedListStack<Character> stack = new LinkedListStack();
		String s;
		int lineNumber = 0;
		File f = new File(filename);
		Scanner scan = new Scanner(f);
		while(scan.hasNextLine()){			
			s = scan.nextLine(); 
			lineNumber ++;
			for(int i = 0; i < s.length() ; i++){
				char c = s.charAt(i);
				if(i==0 || i!=0 && s.charAt(i-1)!='\\'){
					if(c=='"'){
						if((stack.size()!=0 && stack.peek()!='*' && stack.peek()!='"' && stack.peek()!='\'') || stack.size()==0){
							stack.push(c);
						}
						else if(stack.size()!=0 && stack.peek()=='"'){
							stack.pop();
						}
					}
					if(c=='\''){
						if((stack.size()!=0 && stack.peek()!='*' && stack.peek()!='"' && stack.peek()!='\'') || stack.size()==0){
							stack.push(c);
						}
						else if(stack.size()!=0 && stack.peek()=='\''){
							stack.pop();
						}
					}
					if(c=='/'){
						if (i<s.length()-1 && s.charAt(i+1)=='/'){
							break;
						}
						if (i<s.length()-1 && s.charAt(i+1)=='*'&&stack.size()!=0&&stack.peek()!='*'){
							stack.push('*');
						}
					}
					if (stack.size()!=0 && stack.peek()=='*'){
						if(c=='*'&&i<s.length()-1 && s.charAt(i+1)=='/'){
							stack.pop();
						}
					}
					if((stack.size()!=0 && stack.peek()!='*' && stack.peek()!='"' && stack.peek()!='\'') || stack.size()==0){
						if (c=='(' || c=='{' || c=='['){
							stack.push(c);
						}
						
						if (c==')'){
							if(stack.size()==0){
								return unmatchedSymbol(lineNumber, i+1, c, ' ');
							}
							else if (stack.peek()=='('){
								stack.pop();
							}
							else if (stack.peek()=='{'){
								return unmatchedSymbol(lineNumber, i+1, c, '}');
							}
							else if (stack.peek()=='['){
								return unmatchedSymbol(lineNumber, i+1, c, ']');
							}
						}
						if (c=='}'){
							if(stack.size()==0){
								return unmatchedSymbol(lineNumber, i+1, c, ' ');
							}
							else if (stack.peek()=='{'){
								stack.pop();
							}
							else if (stack.peek()=='('){
								return unmatchedSymbol(lineNumber, i+1, c, ')');
							}
							else if (stack.peek()=='['){
								return unmatchedSymbol(lineNumber, i+1, c, ']');
							}
						}
						if (c==']'){
							if(stack.size()==0){
								return unmatchedSymbol(lineNumber, i+1, c, ' ');
							}
							else if (stack.peek()=='['){
								stack.pop();
							}
							else if (stack.peek()=='{'){
								return unmatchedSymbol(lineNumber, i+1, c, '}');
							}
							else if (stack.peek()=='('){
								return unmatchedSymbol(lineNumber, i+1, c, ')');
							}
						}
					}
				}
			}
		}
		if(stack.size()!=0){
			if(stack.peek()=='*'){
				return this.unfinishedComment();
			}
			if(stack.peek()=='('){
				return this.unmatchedSymbolAtEOF(')');
			}
			if(stack.peek()=='{'){
				return this.unmatchedSymbolAtEOF('}');
			}
			if(stack.peek()=='['){
				return this.unmatchedSymbolAtEOF(']');
			}
		}
		return this.allSymbolsMatch();	
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