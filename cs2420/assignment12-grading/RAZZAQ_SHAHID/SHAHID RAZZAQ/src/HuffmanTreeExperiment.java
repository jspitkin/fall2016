package assignment12;

import java.io.File;

/**
 * Experiment Class for Huffman Tree
 * 
 * @author ShahidBilal u0996062
 */
public class HuffmanTreeExperiment {

	/*
	 * Methodology For Compression Experiment: Test a variety of files with the
	 * following content, and then test file size using file.length() method
	 * Criteria for Files:
	 * 1.File with 100 varying chars 
	 * 2.File with 100 similar chars 
	 * 3.File with 100 Same Char We are testing for frequency of
	 * characters, and how it affects compression.. the file size will be set to
	 * a standard of 100 chars, so to have a fair comparison. Chars can be
	 * anything from letters (upper/lower case) to symbols and spaces. Final
	 * test will be for a file containing an "everyday" writing type paragraph
	 * to simulate a real world scenario
	 * 
	 * THIS EXPERIMENT REQUIRES THE FOLLOWING FILES: (included with assign)
	 * 1."1_Original_Varying_Chars.txt"
	 * 2."1_Original_Similar_Chars.txt"
	 * 3."1_Original_Same_Chars.txt"
	 * 4."1_Original_EveryDay_Paragraph.txt"
	 */
	public static void main(String[] args) {
		//Test File 1 with varying chars
		compressFile("1_Original_Varying_Chars.txt","1_Compressed_Varying_Chars.txt");
		File varyingFileCompressed = new File("1_Compressed_Varying_Chars.txt");
		File varyingFileOriginal = new File("1_Original_Varying_Chars.txt");
		System.out.println("Original Size of Varying File: "+ varyingFileOriginal.length() +" bytes");
		System.out.println("Compressed Size of Varying File: "+ varyingFileCompressed.length() +" bytes");
		System.out.println();
		
		//Test File 2 with Similar Chars:
		compressFile("1_Original_Similar_Chars.txt", "1_Compressed_Similar_Chars.txt");
		File similarFileOriginal = new File("1_Original_Similar_Chars.txt");
		File similarFileCompressed = new File("1_Compressed_Similar_Chars.txt");
		System.out.println("Original Size of similar file: "+ similarFileOriginal.length() +" bytes");
		System.out.println("Compressed Size of similar file: " + similarFileCompressed.length()+" bytes");
		System.out.println();

		//Test File 3 with same chars:
		compressFile("1_Original_Same_Chars.txt", "1_Compressed_Same_Chars.txt");
		File sameCharsOriginal = new File("1_Original_Same_Chars.txt");
		File sameCharsCompressed = new File("1_Compressed_Same_Chars.txt");
		System.out.println("Original Size of same Char file: "+ sameCharsOriginal.length() +" bytes");
		System.out.println("Compressed Size of same Char file: " + sameCharsCompressed.length()+" bytes");
		System.out.println();
		
		//Test File 4 -- real world every day paragraph file
		compressFile("1_Original_EveryDay_Paragraph.txt", "1_Compressed_EveryDay_Paragraph.txt");
		File eDPOriginal = new File("1_Original_EveryDay_Paragraph.txt");
		File eDPCompress = new File("1_Compressed_EveryDay_Paragraph.txt");
		System.out.println("Original Size of everyday paragraph file: "+ eDPOriginal.length() +" bytes");
		System.out.println("Compressed Size of everyday paragraph file: " + eDPCompress.length()+" bytes");
		System.out.println();
	}

	/**
	 * method used to generate a compressed file
	 * this method was provided by the instructor
	 * i removed the generateDotFile line to have
	 * it better suit this experiment without generating
	 * lots of unneeded files.
	 * @param infile -- input file
	 * @param outfile -- output file
	 */
	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();
		t.compressFile(new File(infile), new File(outfile));

	}

}
