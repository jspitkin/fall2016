//William Frank
//u1067292
package assignment07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author ??
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {


		ArrayList<char[]> results = new ArrayList<char[]>();
		try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while(input.ready()) {
				results.add(input.readLine().toCharArray());
			}
			input.close();
		} catch(FileNotFoundException e) {
			throw e;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		boolean ignoreForComment = false, ignoreForString = false, ignoreForChar = false, skipLine = false;

		LinkedListStack<Character> charStack = new LinkedListStack<Character>();

		for (int lineNumber = 0; lineNumber < results.size(); lineNumber++) {
			char[] line = results.get(lineNumber);
			int columnNumberAdjusted = 0;
			skipLine = false;
			
			for (int columnNumber = 0; columnNumber < line.length; columnNumber++) {
				columnNumberAdjusted++;
				
				char letter = line[columnNumber];

				if (!(ignoreForComment || ignoreForString || ignoreForChar))
				{

					switch (letter)
					{
					case '/':

						if (columnNumber < line.length -1)
						{
							if (line[columnNumber + 1] == '/')
							{
								skipLine = true;
							}
							else if (line[columnNumber + 1] == '*')
							{
								ignoreForComment = true;
							}
						}

						break;
						
					case '[':
						charStack.push('[');
						break;

					case ']':
						if (charStack.size() == 0)
						{
							return unmatchedSymbol(lineNumber + 1, columnNumberAdjusted, line[columnNumber], ' ');
						}
						else if (charStack.peek() == '[')
						{
							charStack.pop();
							break;
						}
						else
						{
							return unmatchedSymbol(lineNumber + 1, columnNumberAdjusted, line[columnNumber], symbolSwitcher(charStack.peek()));
						}

					case '{':
						charStack.push('{');
						break;

					case '}':
						if (charStack.size() == 0)
						{
							return unmatchedSymbol(lineNumber + 1, columnNumberAdjusted, line[columnNumber], ' ');
						}
						else if (charStack.peek() == '{')
						{
							charStack.pop();
							break;
						}
						else
						{
							return unmatchedSymbol(lineNumber + 1, columnNumberAdjusted, line[columnNumber], symbolSwitcher(charStack.peek()));
						}

					case '(':
						charStack.push('(');
						break;

					case ')':
						if (charStack.size() == 0)
						{
							return unmatchedSymbol(lineNumber + 1, columnNumberAdjusted, line[columnNumber], ' ');
						}
						else if (charStack.peek() == '(')
						{
							charStack.pop();
							break;
						}
						else
						{
							return unmatchedSymbol(lineNumber + 1, columnNumberAdjusted, line[columnNumber], symbolSwitcher(charStack.peek()));
						}
					case '\'':
						ignoreForChar = true;
						break;
					case '"':
						ignoreForString = true;
						break;
					}
				}
				else
				{
					if (ignoreForString && line[columnNumber] == '"' && line[columnNumber - 1] != '\\')
					{
						ignoreForString = false;
					}
					else if (ignoreForChar && line[columnNumber] == '\'')
					{
						ignoreForChar = false;
					}
					else if (columnNumber < line.length -1)
					{
						if (line [columnNumber] == '*' && line[columnNumber + 1] == '/')
						{
							ignoreForComment = false;
						}
						
					}
				}
				
				if (skipLine)
				{
					break;
				}
			}
		}


		if (ignoreForComment == true)
		{
			return unfinishedComment();
		}
		else if (charStack.size() > 0)
		{
			return unmatchedSymbolAtEOF(symbolSwitcher(charStack.peek()));
		}
		else
		{
			return allSymbolsMatch();
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
	
	private char symbolSwitcher(char symbol) {
		
		switch (symbol)
		{
		case '[':
			return ']';
		case ']':
			return '[';
		case '{':
			return '}';
		case '}':
			return '{';
		case '(':
			return ')';
		case ')':
			return '(';
		}
		
		return symbol;
	}
}