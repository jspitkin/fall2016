package assignment12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 
 * @author Matthew Wilson (u0499184)
 * 
 * Timing experiment for Huffman Compression
 *
 */

public class Timing {
	  
	  public static void compressFile(String infile, String outfile, String dotFile) {
	    HuffmanTree t = new HuffmanTree();

	    t.compressFile(new File(infile), new File(outfile));
	    
	    t.huffmanToDot(dotFile);
	  }
	  
	  public static void decompressFile(String infile, String outfile) {
	    HuffmanTree t = new HuffmanTree();

	    t.decompressFile(new File(infile), new File(outfile));
	  }
	  

	
	public static void timeHuffman() {
		final int SIZE = 100_000;
		System.out.println("HUFFMAN COMPRESSION TIMING");
		try(FileWriter fw = new FileWriter(new File("huff.csv"))) {
			String header = "Ratio, Frequency, Unique" + "\n";
			System.out.print(header); 
			fw.write(header);
			
			
		
			
			char startAscii = 48;
			int increment = 5;
			
			for (int i = 1; i <= 10; i++) {
				
				//SETUP 
				
				char[] chars = new char[SIZE];
				int j = 1;
				int unique = increment*i;
//				int endRange = 48 + extra;
				
				int frequency = SIZE / unique;
				char currentAscii = startAscii;
				
				for (int v = 0; v < SIZE-1;) {
					int x = 0;
					while (x < frequency) {
						//lazy man's solution to index out of bound
						chars[Math.min(SIZE-1, v)] = currentAscii;
						v++;
						x++;
					}
					currentAscii++;
					
				}
				try(FileWriter tFile = new FileWriter(new File("time.txt"))) {
//					System.out.println(chars);
					tFile.write(chars);
					tFile.close();
				}
				
				compressFile("time.txt", "time_compress.txt", "time.dot");
				File ogFile = new File("time.txt");
				File compFile = new File("time_compress.txt");
				float ratio = (float)compFile.length() / ogFile.length();
				
				//HUFFMAN STUFF
				
				
				
				//OUTPUT
				String log = ""+ ratio + "," + frequency + "," + unique + "\n";
				fw.write(log);
				System.out.print(log);
				
				
			}
			
			}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("START");
		timeHuffman();
		System.out.println("STOP");
	}

}
