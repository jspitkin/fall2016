package assignment12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;


/**
 * 
 * @author Prathusha Boppana, u0778008
 *
 */
public class HuffmanExperiments {	

	public static void main(String[] args) {
		HuffmanTree tree = new HuffmanTree();
		tree.compressFile(new File("words"), new File("wordsComp"));
		File compFile = new File("wordsComp");
		double compBytes = (compFile.length()/1024);
		System.out.println("compressed file bytes: " + compBytes);
		
		tree.decompressFile(new File("wordsComp"), new File("wordsDecomp"));
		File decompFile = new File("wordsDecomp");
		double decompBytes = (decompFile.length()/1024);
		System.out.println("decompressed file bytes: " + decompBytes);
		
	}

}
