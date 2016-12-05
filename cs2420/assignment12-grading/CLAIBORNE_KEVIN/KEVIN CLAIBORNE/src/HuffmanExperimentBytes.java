package assignment12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HuffmanExperimentBytes {
	public static void main(String[] args) {
		int size = 10000;
		ArrayList<String> stringList;
		Random rand = new Random();

		while (size <= 1000000) {
			stringList = new ArrayList<String>();
			for (int i = 0; i < size; i++) {
				stringList.add(randomString(rand.nextInt(15)) + " ");
				if (i % 10 == 0) {
					stringList.add("\n");
				}
			}
			FileWriter writer;
			try {
				writer = new FileWriter("experiment_output.txt");
				for (String s : stringList) {
					writer.write(s);
				}
				writer.close();
			} catch (IOException e) {
			}
			HuffmanTree t = new HuffmanTree();

			t.compressFile(new File("experiment_output.txt"), new File("experiment_output_compressed.txt"));
			
			Long origBytes = new File("experiment_output.txt").length();
			Long newBytes = new File("experiment_output_compressed.txt").length();
			
			System.out.println(size + "\t" + origBytes + "\t" + newBytes);
			

			size += 5000;
		}
	}

	public static String randomString(int length) {
		Random rand = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {

			char randomChar = (char) ('A' + (rand.nextInt(58)));
			stringBuilder.append(randomChar);
		}
		return stringBuilder.toString();
	}
}
