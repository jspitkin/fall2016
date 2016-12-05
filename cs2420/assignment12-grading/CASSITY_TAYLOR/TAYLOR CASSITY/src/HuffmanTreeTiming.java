package assignment12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import assignment11.PriorityQueue;
import utilities.OutputPrint;

public class HuffmanTreeTiming {
	static int ITER = 1;
	static double[] sizes = { 5000, 10000, 15000, 20000, 22000};
	static String[] files = { "5k.txt", "10k.txt", "15k.txt", "20k.txt", "22k.txt" };
	static OutputPrint printer = new OutputPrint();
	static HuffmanTree ht;

	public static void main(String[] args) throws IOException {

		printer.clearFile("output.txt");
		// Warmup
		for (int i = 0; i < 999999; i++)
			;

		printer.printToFile("output.txt", "Sizes", sizes);

		addTime();

		System.out.println("Done");
	}

	private static void addTime() throws IOException {

		generateRandomFiles();
		System.out.println("Random generation done");
		printer.printToFile("output.txt", "Random", time());
		System.out.println("Random done");

		generateRepeatingFiles();
//		System.out.println("Repeating generation done");
//		printer.printToFile("output.txt", "Repeating", time());
//		System.out.println("Repeating done");

		// printer.printToFile("output.txt", "findMin", findMin());
		// System.out.println("findMin done");

	}
	// to time:
	// add
	// delete
	// findmin

	private static double[] time() {

		double[] times = new double[sizes.length];

		//run per testing size
		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double perSizeTime = 0;
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				//New instance
				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for(int j = 0; j < ITER; j++) {
					compressFile(files[x], String.format("output_%s.txt", files[x]));
				}
				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < sizes[x]; j++){
					String.format("output_%s.txt", files[x]);
				};
				
				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				perSizeTime += (finalTime - startTime - middle);
				System.out.println(sizes[x] + " of ITER " + i + " done.");
			}
			times[x] = perSizeTime / ITER;
			System.out.println(sizes[x] + " done");

		}

		return times;
	}

	public static void generateRandomFiles() throws IOException {
		for (int i = 0; i < files.length; i++) {
			
			FileWriter out = new FileWriter(files[i], true); // the true will
			
			Random rand = new Random();
			for (int j = 0; j < sizes[i]; j++)
				out.write((char)rand.nextInt(26) + 'a');// appends the string to the file
			out.close();
		}
	}
	
	public static void generateRepeatingFiles() throws IOException {
		for (int i = 0; i < files.length; i++) {
			
			FileWriter out = new FileWriter(files[i], true); // the true will
			
			Random rand = new Random();
			char x = (char) (rand.nextInt(26) + 'a');
			
			for (int j = 0; j < sizes[i]; j++)
				out.write(x);// appends the string to the file
			out.close();
		}
	}

	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree.dot");
	}
}
