package assignment12;

import java.io.File;

/**
 * Copy of CompressionDemo Class for Question 1 of Assignment12 Analysis Document
 * 
 * @author Daxton Wilson u0264580
 */

public class Question_1_Experiment {
	  
	  public static void compressFile(String infile, String outfile) {
	    HuffmanTree t = new HuffmanTree();

	    t.compressFile(new File(infile), new File(outfile));
	    
//	    t.huffmanToDot("huffman_tree.dot"); Commented out to not build .DOT files of 10,000 characters
	  }
	  
	  public static void decompressFile(String infile, String outfile) {
	    HuffmanTree t = new HuffmanTree();

	    t.decompressFile(new File(infile), new File(outfile));
	  }
	  
	  public static void main(String[] args) {
	    compressFile("random10000Characters.txt", "compressed10000Rand.txt");
	    decompressFile("compressed10000Rand.txt", "decompressed.txt");
	    compressFile("oneCharacterTimes10000.txt", "compressedOne10000.txt");
	    decompressFile("compressedOne10000.txt", "decompressed.txt");
	    compressFile("random10000Alphabet.txt", "compressedRandom10000Alphabet.txt");
	    decompressFile("compressedRandom10000Alphabet.txt", "decompressed.txt");
	    compressFile("alphabetTimes400.txt", "compressedAlphabetTimes400.txt");
	    decompressFile("compressedAlphabetTimes400.txt", "decompressed.txt");
	  }
}
