package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Amir Mohsenian
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException 
	{
		//LinkedListStack variable
		LinkedListStack<Character> stack1;
		stack1 = new LinkedListStack<>();

		//File variable
		File file1;
		file1 = new File(filename);
		
		//Character variable to determine if the symbol popped
		char symbolPopped;

		//Input scanner
		Scanner input;
		input = new Scanner(file1);

		int numLine;
		numLine =0;

		Boolean blockOpen;
		blockOpen = false;
		while(input.hasNext())
		{
			Boolean blockQuote;
			blockQuote= false;
			
			Boolean blockChar;
			blockChar= false;
			numLine += 1;
			String curr ;
			curr= input.nextLine();
			char[] characters ;
			characters= new char[curr.length()];
			
			
			curr.getChars(0, curr.length(), characters, 0);
			
			int index;
			index =1;
			while(index < characters.length || index == characters.length)
			{
				char charCurrent ;
				charCurrent= characters[index - 1];
				char after;
				char before; 
				if(index < characters.length)
				{
					after = characters[index];
				}
				else
				{
					after = '\u0000';
				}
				
				if(index > 2 || index ==2)
				{
					before = characters[index-2];
				}
				else
				{
					before = '\u0000';
				}

				if(after == '/' && !blockOpen && !blockQuote && charCurrent == '/')
				{
					break;
				}
				else if( after == '*' && !blockQuote && charCurrent == '/')
				{
					blockOpen = true;
				}
				else if(after == '/' && !blockQuote && charCurrent == '*')
				{
					if(before == '/')
					{
						blockOpen = true;
					}
					else
					{
						blockOpen = false;
					}
				}
				else if(!blockOpen && !blockChar && charCurrent == '"')
				{
					if(charCurrent == '"' && before == '\\' )
					{
						
					}
					else
					{
							blockQuote = !blockQuote;
					}
				}
				else if( !blockOpen && !blockQuote && charCurrent == '\'')
				{
					if (charCurrent == '\'' && before == '\\')
					{
						
					}
					else
					{
						blockChar = !blockChar;
					}
				}
				
				if( !blockQuote && !blockChar && !blockOpen)
				{
					if(charCurrent == '(' || charCurrent == '['|| charCurrent == '{' )
					{
						stack1.push(charCurrent);
					}
				
					else if (charCurrent == ')'  || charCurrent == ']' || charCurrent == '}')
					{
						
						if(stack1.size() > 0)
						{
						
							symbolPopped= stack1.pop();
						
							if(symbolPopped == '(')
							{
								if(!(charCurrent == ')'))
								{
									input.close();
									return unmatchedSymbol(numLine, index, charCurrent, ')');
								}
							}
							if(symbolPopped == '{')
							{
								if(!(charCurrent == '}'))
								{
									input.close();
									return unmatchedSymbol(numLine, index, charCurrent, '}');
								}
							}
							if(symbolPopped == '[')
							{
								if(!(charCurrent == ']'))
								{
									input.close();
									return unmatchedSymbol(numLine, index, charCurrent, ']');
								}
							}
						}
						else
						{
							input.close();
							return unmatchedSymbol(numLine, index, charCurrent, ' ');
						}
					}	
				}
				
				index = index+1;
			}
		}
		
		input.close();
		
		if(blockOpen)
		{
			return unfinishedComment();
		}
		if(!stack1.isEmpty())
		{
			symbolPopped = stack1.pop(); 
			
			if(symbolPopped == '(')
			{
				return unmatchedSymbolAtEOF(')');
			}
			
			if(symbolPopped == '[')
			{
				return unmatchedSymbolAtEOF(']');
			}
			
			if(symbolPopped == '{')
			{
				return unmatchedSymbolAtEOF('}');
			}
			
			if(symbolPopped == ' ')
			{
				return null;
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