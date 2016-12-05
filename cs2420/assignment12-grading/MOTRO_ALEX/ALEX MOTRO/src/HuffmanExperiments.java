package assignment12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class HuffmanExperiments {

	public static void main(String[] args) throws FileNotFoundException {
		HuffmanTree t = new HuffmanTree();
		Random rand = new Random();
		FileOutputStream writer;
		File file = new File("experiment.txt");
		File compressedFile = new File("compressed.txt");
		
		int repetitions = 100;
		int fileSize = 10000;
		
		for(int N = 10; N <= 150; N += 10) {
			
			double total = 0;
			for(int i = 1; i <= repetitions; i++) {
				
				try {
					writer = new FileOutputStream(file);
					for(int j = 1; j <=fileSize; j++) {
						writer.write(1+rand.nextInt(N));
					}
					writer.close();
					
				} catch (IOException e) {
					System.err.println("IO Error");
				}
				
				t.compressFile(file, compressedFile);
				
				
				total += ((double)file.length())/((double)compressedFile.length());
			}
			double average = total/repetitions;
			System.out.println(average);
			
		}

	}

}
