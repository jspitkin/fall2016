package assignment10;

import utilities.OutputPrint;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.print.attribute.standard.Media;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import assignment08.SpellChecker;
import utilities.OutputPrint;

public class HasherTiming {

	static int ITER = 500;
	static String[] files = { "500.txt", "1000.txt", "2000.txt", "4000.txt", "8000.txt", "16000.txt" };
	static QuadProbeHashTable QPT;
	static ChainingHashTable HPT;
	static GoodHashFunctor ghf = new GoodHashFunctor();
	static BadHashFunctor bhf = new BadHashFunctor();
	static MediocreHashFunctor mhf = new MediocreHashFunctor();
	static OutputPrint printer = new OutputPrint();
	static SpellChecker SP = new SpellChecker();
	static List<String> outWords;

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		printer.clearFile("output.txt");
		// Warmup
		for (int i = 0; i < 999999; i++)
			;

		double[] sizes = { 500, 1000, 2000, 4000, 8000, 16000 };
		printer.printToFile("output.txt", "Sizes", sizes);

		addTime();

		System.out.println("Done");
	}

	private static void addTime() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

//		printer.printToFile("output.txt", "Good", good());
//		System.out.println("good done");
//		printer.printToFile("output.txt", "Med", med());
//		System.out.println("med done");
//		printer.printToFile("output.txt", "Bad", bad());
//		System.out.println("bad done");
//
// 
		printer.printToFile("output.txt", "quadGet", quadGet());
		System.out.println("quadGet done");
		
		printer.printToFile("output.txt", "chainGet", chainGet());
		System.out.println("chainGet done");
		
//		printer.printToFile("output.txt", "chain", chain());
//		System.out.println("chain done");
//		
//		printer.printToFile("output.txt", "good hash", gHash());
//		System.out.println("good hash done");
//		
//		printer.printToFile("output.txt", "med hash", mHash());
//		System.out.println("med hash done");
//		
//		printer.printToFile("output.txt", "bad hash", bHash());
//		System.out.println("bad hash done");
		
		
		//sound player for when codes is finished
//		AudioInputStream audioIn = AudioSystem.getAudioInputStream(HasherTiming.class.getResource("CHIMES.wav"));
//		Clip clip = AudioSystem.getClip();
//		clip.open(audioIn);
//		clip.start();

	}

	private static double[] quad() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				QPT = new QuadProbeHashTable(10, ghf);

				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					QPT.add(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
			
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}
	
	private static double[] quadGet() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				QPT = new QuadProbeHashTable(10, ghf);
				
				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					QPT.add(outWords.get(j));
				}
				
				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					QPT.contains(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
			
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}

	private static double[] chain() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double ftime = 0;
			double middle = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				HPT = new ChainingHashTable(10, ghf);

				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					HPT.add(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += HPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}

	private static double[] chainGet() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double ftime = 0;
			double middle = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				HPT = new ChainingHashTable(10, ghf);
				for (int j = 0; j < outWords.size(); j++) {
					HPT.add(outWords.get(j));
				}
				
				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					HPT.contains(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += HPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}

	
	private static double[] good() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				QPT = new QuadProbeHashTable(10, ghf);

				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					QPT.add(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}

	private static double[] med() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				QPT = new QuadProbeHashTable(10, mhf);

				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					QPT.add(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}

	private static double[] bad() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				QPT = new QuadProbeHashTable(10, bhf);

				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					QPT.add(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}
	
	private static double[] gHash() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				QPT = new QuadProbeHashTable(10, ghf);

				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					ghf.hash(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
			
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}
	private static double[] mHash() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {


				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					mhf.hash(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
			
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}
	private static double[] bHash() {

		double[] times = new double[files.length * 2 + 1];
		int[] coll = new int[files.length];

		for (int x = 0; x < files.length; x++) {
			double startTime = 0;
			double midTime = 0;
			double finalTime = 0;
			double middle = 0;
			double ftime = 0;
			int collisions = 0;

			File words = new File(files[x]);
			outWords = SP.readFromFile(words);
			// Run ITER times to average it
			for (int i = 0; i < ITER; i++) {

				// Start timer
				startTime = System.nanoTime();

				// Run for list size
				for (int j = 0; j < outWords.size(); j++) {
					bhf.hash(outWords.get(j));
				}

				// Find the time for running the forloop
				midTime = System.nanoTime();
				for (int j = 0; j < outWords.size(); j++)
					outWords.get(j);

				// End timer, remove forloop time
				finalTime = System.nanoTime();
				middle = (finalTime - midTime);
				ftime += (finalTime - startTime - middle);
				collisions += QPT.getCollisions();
			}
			times[x] = ftime / ITER;
			coll[x] = collisions / ITER; System.out.println(files[x] + " done");
			
		}
		int i = 0;
		for (int x = files.length + 1; x < files.length * 2 + 1; x++)
			times[x] = coll[i++];

		return times;
	}
}
