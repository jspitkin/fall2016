package assignment12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class HuffmanCompressionTester {
	
	static long[] fileSizeCom = new long[11];
	static long[] fileSizeDecom = new long[11];

	public static void main(String[] args) throws IOException{
		for(int i = 0; i < 110; i = i + 10){
			compressionTests(i);
		}
	}

	private static void compressionTests(int uniqueChars) throws IOException{
		
		Random r = new Random();
		int raisedTo = 5;

		for (int k = 0; k < 11; k++) {
			
			File file = new File("File" + k + ".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for(int i = 0; i < Math.pow(2, raisedTo) - uniqueChars; i++){
				bw.write("A");
			}
			
			for(int i = 66; i < 66 + uniqueChars; i++){
				bw.write((char) i);
			}
			
			bw.close();
			
			compressFile(file.toString(), "File" + k +"Com.txt");
			decompressFile("File" + k + "Com.txt", "File" + k +"De.txt");
			
			File fileCom = new File("File" + k + "Com.txt");
			
			
			fileSizeCom[k] = fileCom.length();
			fileSizeDecom[k] = file.length();
			
			raisedTo++;
			
		}
		
		int indexPow = 5;
		
		System.out.println("Unique Chars Compressed, " + uniqueChars);
		
		for(int i = 0; i < 11; i++){
			System.out.println(Math.pow(2, indexPow) + ", " + fileSizeCom[i]);
			indexPow++;
		}
		
		indexPow = 5;
		
		System.out.println("Unique Chars Decompressed, " + uniqueChars);
		
		for(int i = 0; i < 11; i++){
			System.out.println(Math.pow(2, indexPow) + ", " + fileSizeDecom[i]);
			indexPow++;
		}


	}

	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree.dot");
	}

	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));
	}

}
