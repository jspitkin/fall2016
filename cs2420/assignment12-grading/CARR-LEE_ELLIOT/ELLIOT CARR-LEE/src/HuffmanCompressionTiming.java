package assignment12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;

import assignment11.PriorityQueue;

/**
 * Timing Test for Huffman Compression. 
 * 
 * @author Elliot Carr-Lee, u0549837
 */
public class HuffmanCompressionTiming {
	
	public static void main(String[] args){
		frequencyTest();
//		HuffmanTree huffnPuff = new HuffmanTree();
//		int[] testSetSizes = {4000,8000, 16000, 32000, 64000,128000};
//		ArrayList<File> testSets = new ArrayList<File>();
//		for (int i = 0; i < testSetSizes.length; i++) {
//			for (int j = 0; j < 5; j++) {
//				//testSets.add(new File( "src/assignment12/timingTests/test" + i + " " +  j));
//			}
//		}
//		double[] timeAddFirstResults = new double[testSetSizes.length];
//		char d;
//		File currentFile = null;
//		ArrayList<Character> charsToWrite;
//		for (int i = 0; i < testSetSizes.length; i++) {
//			int charLimit = 49;
//				charsToWrite = new ArrayList<Character>();
//				for (int charVariations = 0; charVariations < 10; charVariations++){
//				for (int c = 48; c < charLimit; c++) {
//					char e = (char) c;
//					charsToWrite.add((Character) e);
//				}
//				charLimit+=7;
//					currentFile = null;
//					try {
//						currentFile = new File("src/assignment12/timingTests/test" + i + " " +  charVariations);
//						FileWriter writer = new FileWriter(currentFile);
//						int charsWritten = 0;
//						do{
//						for (Character f : charsToWrite) {
//							writer.write(f);
//							if (charsWritten == testSetSizes[i])
//								break;
//						charsWritten++;
//						}
//						}while(charsWritten != testSetSizes[i]);
//						writer.close();
//						charsToWrite.clear();
//						File compressFile = new File (currentFile.getAbsolutePath() + "compressed");
//						File decompressedFile = new File (currentFile.getAbsolutePath() + "decompressed");
//						huffnPuff.compressFile(currentFile, compressFile);
//						if (i == 5)
//							huffnPuff.huffmanToDot(currentFile.getAbsolutePath() + "huffndot");
//						huffnPuff.decompressFile(compressFile , decompressedFile);
//						if (!HuffmanTreeTest.verify(currentFile.getAbsolutePath(), decompressedFile.getAbsolutePath()))
//							throw new IllegalStateException();
//						decompressedFile.delete();
//						currentFile.delete();
//					} catch (IOException m) {
//						// TODO Auto-generated catch block
//						m.printStackTrace();
//					}
//				}
//				
//
//	}
}
	
	
	
	public static void frequencyTest(){
		HuffmanTree huffnPuff = new HuffmanTree();
		int[] testSetSizes = {128000};
		ArrayList<File> testSets = new ArrayList<File>();
		char d;
		File currentFile = null;
		ArrayList<Character> charsToWrite = new ArrayList<Character>();
		int charLimit = 119;
		for (int c = 48; c < charLimit; c++) {
			char e = (char) c;
			charsToWrite.add((Character) e);
		}
		for (int i = 0; i < testSetSizes.length; i++) {
				for (int frequencyVariations = 0; frequencyVariations < 5; frequencyVariations++){
					currentFile = null;
					try {
						currentFile = new File("src/assignment12/timingTests/testFrequency" + i + " " +  frequencyVariations);
						FileWriter writer = new FileWriter(currentFile);
						int charsWritten = 0;
						do{
						for (int p = 0; p < charsToWrite.size(); p++) {
							Character foureight = (Character)(char)48;
							if (charsToWrite.get(p).equals(foureight)){
								for (int n = 0; n < ((testSetSizes[i])/(5-frequencyVariations))-100; n++){
									writer.write(charsToWrite.get(p));
									charsWritten++;
								}
								charsToWrite.remove(charsToWrite.get(p));
							}
								
							else {
								writer.write(charsToWrite.get(p));
								charsWritten++;
							}
							if (charsWritten >= testSetSizes[i])
								break;
						}
						}while(charsWritten < testSetSizes[i]);
						charsToWrite.add(0, (char)48);
						writer.close();
						File compressFile = new File (currentFile.getAbsolutePath() + "compressed");
						File decompressedFile = new File (currentFile.getAbsolutePath() + "decompressed");
						huffnPuff.compressFile(currentFile, compressFile);
						huffnPuff.decompressFile(compressFile , decompressedFile);
						if (!HuffmanTreeTest.verify(currentFile.getAbsolutePath(), decompressedFile.getAbsolutePath()))
							throw new IllegalStateException();
//						decompressedFile.delete();
//						currentFile.delete();
					} catch (IOException m) {
						// TODO Auto-generated catch block
						m.printStackTrace();
					}
				}
				

	}

}
	
	public static void timefindMin(){

}

}
