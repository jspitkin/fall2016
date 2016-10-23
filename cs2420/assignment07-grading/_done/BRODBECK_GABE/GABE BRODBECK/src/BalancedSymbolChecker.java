package assignment07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Gabe Brodbeck u0847035
 */
public class BalancedSymbolChecker {
	LinkedListStack<Character> stack= new LinkedListStack<Character>();
	boolean lastWasCommentSignifier=false;
	boolean lastWasEscape=false;
	boolean lastWasStar=false;
	boolean inString=false;
	boolean inComment=false;
	boolean inChar=false;
	int row;
	int col;;
	char charAt;
	boolean shouldEnd=false;
	String answer;
	ArrayList<String> results = new ArrayList<String>();
			
	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		try{
			FileReader freader=new FileReader(filename);
		}catch(FileNotFoundException e){
			throw new FileNotFoundException();
		}
		
		
		try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while(input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		answer=allSymbolsMatch();
		for(row=0;row<results.size();row++){
			
			for(col=0;col<results.get(row).length();col++){
				
				charAt=results.get(row).charAt(col);
				
				if(!inComment&&!inString&&!inChar){
				checkCharForRegularCode();
				if(shouldEnd==true){
					return answer;
				}
				}
				//----
				else if(inComment){
					checkCharForInsideComment();
				}
				//----
				else if(inString){
					checkCharForInsideString();
				}
				//---
				else if(inChar){
					checkCharForInsideChar();
				}
				
	
			}
			
			
		}
		
		if(inComment){
			answer= unfinishedComment();
		}
		else if(!stack.isEmpty()){
			char topOf=stack.pop();
			if(topOf=='{'){
			answer= unmatchedSymbolAtEOF('}');
			}
			if(topOf=='['){
			answer= unmatchedSymbolAtEOF(']');
				}
			if(topOf=='('){
			answer= unmatchedSymbolAtEOF(')');
				}
		}
		
		results.clear();
		stack.clear();
		setAllLastsFalse();
		return answer;
	}
	/**
	 * Checks for all relevant cases inside comments.
	 * All characters in comments toggle a clear of last char data except * and /
	 * Finding star results in lastWasStar being set to true
	 * Finding / results in a check of lastWasStar and if lastWasStar is true it changes insideComment to false;
	 */
	private void checkCharForInsideComment(){
		if(charAt=='*'){
			lastWasStar=true;
		}
		else if(charAt=='/'){
			if(lastWasStar){
				inComment=false;
			}
		}
		else{
			setAllLastsFalse();
		}
	}
	/**
	 * Checks for all relevant cases inside a character declaration like char x= 'x'; 
	 * These cases are the dicovery of a ' or a \
	 * If the 
	 */
	private void checkCharForInsideChar(){
		if(charAt=='\''){
			 if(!lastWasEscape){
				 inChar=false;
			 }
			 else{
				 setAllLastsFalse();
			 }
		}
		else if(charAt=='\\'){
			lastWasEscape=true;
		}
		else{
			setAllLastsFalse();
		}
		
		
	}
	/**
	 *  Checks all relevant cases for a character inside a string.
	 *  These cases both occur when finding "
	 *  if the last char was not \ then it ends being inside a string
	 *  If it is then it ignores the " and resets last char info.
	 */
	private void checkCharForInsideString(){
		if(charAt=='"'){
			if(!lastWasEscape){
				inString=false;
			}
			else{
				setAllLastsFalse();
			}
		}
		else if(charAt=='\\'){
			lastWasEscape=true;
		}
	}
	/**
	 *  Checks for all cases relevant to code which is not inside a comment, inside a string, or part of a character declaration.
	 *  these cases include discorvering a /, a *, a ", a ', or one of the bracket characters.
	 *  All other cases just result in the reset of last character related info.
	 */
	private void checkCharForRegularCode(){
	
			//Checks related to /
			if((charAt=='/')){
				if(lastWasCommentSignifier){
					col=results.get(row).length();
				}
				else{
					lastWasCommentSignifier=true;
				}
			}
			//checks * for /* cases
			else if((charAt=='*')){
				if(lastWasCommentSignifier){
					inComment=true;
				}
			}		
			//checks related to ", only can start strings
			else if((charAt=='"')){
				inString=true;
			}
			//checks related to ', only can start strings
			else if((charAt=='\'')){
				inChar=true;
			}
			
			//checks related to {,[,(
			else if(charAt=='{'||charAt=='['||charAt=='('){
				stack.push(charAt);
				setAllLastsFalse();
			}
			//checks incoming symbols to see if pair is made.
			else if(charAt=='}'||charAt==']'||charAt==')'){
				
			checkForPairs();
			setAllLastsFalse();
			}
			else{
				setAllLastsFalse();
			}
			
		
	}
	/**
	 * Sets lastWasCommentSignifier,lastWasEscape, and lastWasStar to be false. 
	 */
	private void setAllLastsFalse(){
		 lastWasCommentSignifier=false;
		 lastWasEscape=false;
		 lastWasStar=false;
	}
	/**
	 * Checks if charAt is correct for the top of the stack and errors if not the case. 
	 * Can only be called if charAt is },], or ).
	 */
	private void checkForPairs(){
		
			if(stack.isEmpty()){
				shouldEnd=true;
				answer= unmatchedSymbol(row+1,col+1,charAt,' ');
			}else{
			char charTop=stack.pop();
			if(charTop=='{'&&charAt!='}'){
				shouldEnd=true;
				answer= unmatchedSymbol(row+1,col+1,charAt,'}');
			}
			else if(charTop=='('&&charAt!=')'){
				shouldEnd=true;
				answer=unmatchedSymbol(row+1,col+1,charAt,')');
			}
			else if(charTop=='['&&charAt!=']'){
				shouldEnd=true;
				answer= unmatchedSymbol(row+1,col+1,charAt,']');
			}
		
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