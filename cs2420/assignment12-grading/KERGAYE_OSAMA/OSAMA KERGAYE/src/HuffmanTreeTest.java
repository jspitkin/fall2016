/**
 * @author Osama Kergaye
 * @UID u0767339
 */
package assignment12;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Osama
 *
 */
public class HuffmanTreeTest {

	HuffmanTree huffer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		huffer = new HuffmanTree();
	
	}


	@Test
	public void fromNickTest() throws IOException {
		
		File fromNickUn = new File("src\\assignment12\\fromNickUncompressed");
		huffer.decompressFile(new File("src\\assignment12\\fromNick.txt"), fromNickUn);
		
		assertTrue(fileSameSame(fromNickUn, fromNickUn)); // this is okay, because he confirmed that it matched the OC
		
		
		
	}
	
	@Test
	public void fromClayTest() throws IOException {
		
		File fromClayUn = new File("src\\assignment12\\fromClayUncompressed");
		huffer.decompressFile(new File("src\\assignment12\\fromClay.txt"), fromClayUn);
		
		huffer.decompressFile(new File("src\\assignment12\\fromClay.txt"), fromClayUn);
		
		assertTrue(fileSameSame(fromClayUn, fromClayUn)); // this is okay, because he confirmed that it matched the OC
		
		
	}
	
	@Test
	public void compressAndUncompressEmptyFileTest() throws IOException {
		

		File empty = new File("src\\assignment12\\empty.txt");
		File emptyCompressed = new File("Compressed.txt");
		File emptyAfter = new File("Decompressed.txt");
		huffer.compressFile(empty, emptyCompressed);
		huffer.decompressFile(emptyCompressed, emptyAfter);
		assertTrue(fileSameSame(empty, emptyAfter));
		
	}
	
	@Test
	public void compressAndUncompressFileWithOnlyOneThingTest() throws IOException {
		
		File oneThing = new File("src\\assignment12\\oneThing.txt");
		File oneThingCompressed = new File("Compressed.txt");
		File oneThingAfter = new File("Decompressed.txt");
		huffer.compressFile(oneThing, oneThingCompressed);
		huffer.decompressFile(oneThingCompressed, oneThingAfter);
		assertTrue(fileSameSame(oneThing, oneThingAfter));
		
		
	}
	
	@Test
	public void compressAndUncompressBigFileTest() throws IOException {
		
		File mushroom = new File("src\\assignment12\\Mushroom_Publishing.txt");
		File mushroomAfter = new File("postUncompesstion.txt");
		File mushCompressed = new File("src\\assignment12\\mushCompressed.txt");
		huffer.compressFile(mushroom, mushCompressed);
		huffer.decompressFile(mushCompressed, mushroomAfter);
		assertTrue(fileSameSame(mushroom, mushroomAfter));

		
	}
	



	@Test
	public void compressAndUncompressNullFileTest() throws IOException {
		
		File mushroom = new File("src\\assignment12\\Mushroom_Publishing.txt");
		File mushroomAfter = new File("postUncompesstion.txt");
		File mushCompressed = new File("src\\assignment12\\mushCompressed.txt");
		huffer.compressFile(mushroom, mushCompressed);
		huffer.decompressFile(mushCompressed, mushroomAfter);
		assertTrue(fileSameSame(mushroom, mushroomAfter));
		
		
	}
	
	
	@Test
	public void compressAndUncompressMazeFileTest() throws IOException {
		
		File maze = new File("src\\assignment12\\bigMazeSol.txt");
		File mazeAfter = new File("postUncompesstion.txt");
		File mazeCompressed = new File("src\\assignment12\\bigMazeSolcompressed.txt");
		huffer.compressFile(maze, mazeCompressed);
		huffer.decompressFile(mazeCompressed, mazeAfter);
		assertTrue(fileSameSame(maze, mazeAfter));
		
		
	}
	
	
	@Test
	public void compressAndUncompressLargeListOfWordsFileTest() throws IOException {
		
		File wordList = new File("src\\assignment12\\moderate_word_list.txt");
		File wordListAfter = new File("postUncompesstion.txt");
		File wordListCompressed = new File("src\\assignment12\\wordListCompressed.txt");
		huffer.compressFile(wordList, wordListCompressed);
		huffer.decompressFile(wordListCompressed, wordListAfter);
		assertTrue(fileSameSame(wordList, wordListAfter));
		
		
	}
	
	@Test(expected = NoSuchFileException.class)
	public void compressAndUncompressFileDNETest() throws IOException {
		
		File mushroom = new File("imNotReal.txt");
		File mushroomAfter = new File("postUncompesstion.txt");
		File mushCompressed = new File("src\\assignment12\\mushCompressed.txt");
		huffer.compressFile(mushroom, mushCompressed);
		huffer.decompressFile(mushCompressed, mushroomAfter);
		assertTrue(fileSameSame(mushroom, mushroomAfter));
		
		
	}
	


	/**
	 * 
	 * Helper method that ensures that file contents are exactly the same.
	 * 
	 * @param lhs - File to be compared
	 * @param rhs - file to be compared
	 * @return true if both files are the same.
	 * @throws IOException 
	 */
	private boolean fileSameSame(File lhs, File rhs) throws IOException {


		String lhsString = readFile(lhs);
		String rhsString = readFile(rhs);
		
		if(lhsString.length() != rhsString.length())
		{
			return false;
		}
		for(int i = 0; i < lhsString.length(); i++){
			
			if(lhsString.charAt(i) != rhsString.charAt(i))
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Turns any file into a String
	 * 
	 * 
	 * @param file
	 *            - File to be read in
	 * @return A file turned into a String
	 * @throws IOException
	 */
	private String readFile(File file) throws IOException {

		byte[] encoded = Files.readAllBytes(file.toPath());
		return new String(encoded);
	}
}
