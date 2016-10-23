package assignment07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Benjamin Allred u1090524
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException {
		File inputFile = new File(filename);
		
		//make sure file exists, throw exception if not
		if(!inputFile.exists())
		{
			throw new FileNotFoundException();
		}
		
		//setup
		BufferedReader br = new BufferedReader(new FileReader(filename));
		LinkedListStack<Character> list = new LinkedListStack<Character>();
		String[] symbolsPush = new String[] {"{", "[", "("}; //symbols to push
		String[] symbolsPop = new String[] {"}", "]", ")"}; //symbols to invoke a pop
		String line; //stores current line
		int lineNum = 0; //keeps track of line number
		boolean skipToEndComment = false; //used to resolve block comments
		
		try {
			//read one line at a time
			while((line = br.readLine()) != null)
			{
				lineNum++;
				//iterate through each character of that line
				for(int index = 0; index < line.length(); index++)
				{
					//skip to the end of a block comment
					if(skipToEndComment)
					{
						if(line.contains("*/"))
						{
							skipToEndComment = false;
							index = line.indexOf("*/") + 2;
						}
						else
						{
							index = line.length();
						}
					}
					//skip rest of line if "//" appears
					else if(line.charAt(index) == '/' && line.charAt(index + 1) == '/')
					{
						index = line.length();
					}
					//identify block comment and resolve
					else if(line.charAt(index) == '/' && line.charAt(index + 1) == '*')
					{
						if(line.contains("*/"))
						{
							index = line.indexOf("*/") + 2;
						}
						else
						{
							skipToEndComment = true;
							index = line.length();
						}
					}
					//skip characters inside ' '
					else if(line.charAt(index) == '\'')
					{
						do
						{
							index++;
							if(line.charAt(index) == '\'' && line.charAt(index - 1) == '\\' && line.charAt(index - 2) != '\\')
							{
								index++;
							}
						}
						while(line.charAt(index) != '\'');
					}
					//skip characters inside " "
					else if(line.charAt(index) == '\"')
					{
						do
						{
							index++;
							if(line.charAt(index) == '\"' && line.charAt(index - 1) == '\\' && line.charAt(index - 2) != '\\')
							{
								index++;
							}
						}
						while(line.charAt(index) != '\"');
					}
					//determine if character is a symbol
					else
					{
						boolean push = false;
						//determine if its something to be pushed (beginning symbol)
						for(int symInd = 0; symInd < symbolsPush.length; symInd++)
						{
							if(line.charAt(index) == symbolsPush[symInd].charAt(0))
							{
								list.push(line.charAt(index));
								symInd = symbolsPush.length;
								push = true;
							}
						}
						//if nothing gets pushed determine if its an ending symbol
						if(!push)
						{
							for(int symInd = 0; symInd < symbolsPop.length; symInd++)
							{
								if(line.charAt(index) == symbolsPop[symInd].charAt(0))
								{
									//avoid unhandled exception by checking if empty first
									if(list.isEmpty())
									{
										return unmatchedSymbol(lineNum, index + 1, line.charAt(index), ' ');
									}
									//if not empty determine if symbols match
									char tempChar = list.pop();
									boolean match = false;
									switch (symInd)
									{
									case 0 : match = (tempChar == '{') ? true : false; break;
									case 1 : match = (tempChar == '[') ? true : false; break;
									case 2 : match = (tempChar == '(') ? true : false; break;
									}
									//if they don't match call unmatchedSymbol
									if(!match)
									{
										int matchInd = 0;
										switch (tempChar)
										{
										case '{' : matchInd = 0; break;
										case '[' : matchInd = 1; break;
										case '(' : matchInd = 2; break;
										}
										return unmatchedSymbol(lineNum, index + 1, line.charAt(index), symbolsPop[matchInd].charAt(0));
									}
									//else let the program continue
								}
							}
						}
					}
				}				
			}
			br.close(); //close BufferedReader
		} catch (IOException e) {
			e.printStackTrace();
		}
		//determine if file had an unfinished block comment
		if(skipToEndComment)
		{
			return unfinishedComment();
		}
		//determine if there's an unmatched symbol at the end of the file
		if(!list.isEmpty())
		{
			char tempChar = list.pop();
			int matchInd = 0;
			switch (tempChar)
			{
			case '{' : matchInd = 0; break;
			case '[' : matchInd = 1; break;
			case '(' : matchInd = 2; break;
			}
			return unmatchedSymbolAtEOF(symbolsPop[matchInd].charAt(0));
		}
		//if everything passes then all symbols must match
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