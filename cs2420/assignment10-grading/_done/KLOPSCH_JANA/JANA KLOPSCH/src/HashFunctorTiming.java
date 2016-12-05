package assignment10;

import java.util.Random;

/**
 * Timing class for QuadProbeHashTable and ChainingHashTable.
 * @author Jana Klopsch (u0854469)
 * Last Modified 11-9-16
 *
 */
public class HashFunctorTiming {
	
	public static final int BILLION = 1000_000_000;
	static int stringLength = 500;
	
	// Create a random string [a-z] of specified length
	/**
	 * Note: method is from assignment04
	 * Creates a random string [a-z] of specified length
	 * @param length - number of characters in String
	 * @return - generated String
	 */
	public static String randomString(int length)
	{
		Random rand = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char)('a' + (rand.nextInt(26)));// This will throw a null pointer! Find the bug and squash it!
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
	
	public static void timeHashFunctors(int tableSize){
		
		long avgCollisions = 0;
		int iterations = 1000;

		Random rand = new Random();
		//BadHashFunctor badhashFunction = new BadHashFunctor();
		//MediocreHashFunctor medHashFunctor = new MediocreHashFunctor();
		GoodHashFunctor goodHashFunctor = new GoodHashFunctor();
				
		//QuadProbeHashTable quadTable = new QuadProbeHashTable(tableSize, badhashFunction);
		//QuadProbeHashTable quadTable = new QuadProbeHashTable(tableSize, medHashFunctor);
		QuadProbeHashTable quadTable = new QuadProbeHashTable(tableSize, goodHashFunctor);
		
		for(int data = 0; data < iterations; data++){
			int wordLength = rand.nextInt(tableSize*2) + 1; //Words have max length of tableSize*2
			String word = randomString(wordLength);

			quadTable.add(word);
			avgCollisions += quadTable.collisions;
			
		}

		// Print out average time in seconds
		avgCollisions /= iterations;
		
		System.out.println(avgCollisions);
	}
	
	public static void timeHashTable(int tableSize){
		long startTime = 0;
		long endTime = 0;
		long averageTime = 0;
		int iterations = 1000;

		Random rand = new Random();
		//BadHashFunctor badhashFunction = new BadHashFunctor();
		//MediocreHashFunctor medHashFunctor = new MediocreHashFunctor();
		GoodHashFunctor goodHashFunctor = new GoodHashFunctor();
		
		//QuadProbeHashTable quadTable = new QuadProbeHashTable(tableSize, badhashFunction);
		//QuadProbeHashTable quadTable = new QuadProbeHashTable(tableSize, medHashFunctor);
		//QuadProbeHashTable quadTable = new QuadProbeHashTable(tableSize, goodHashFunctor);
		
		//ChainingHashTable chainTable = new ChainingHashTable(tableSize, badhashFunction);
		//ChainingHashTable chainTable = new ChainingHashTable(tableSize, medHashFunctor);
		ChainingHashTable chainTable = new ChainingHashTable(tableSize, goodHashFunctor);
		
		for(int data = 0; data < iterations; data++){
			int wordLength = rand.nextInt(tableSize*2) + 1; //Words have max length of tableSize*2
			String word = randomString(wordLength);

			startTime = System.nanoTime();
			//quadTable.add(word);
			chainTable.add(word);
			endTime = System.nanoTime() - startTime;

			averageTime += endTime;
		}

		// Print out average time in seconds
		averageTime /= iterations;
		double time = averageTime /(double) BILLION;
		
		System.out.println(time);
	}
	
	
	public static void main(String[] args){
		
//		for(int data = 100; data <= 1000; data += 100){
//			timeHashTable(data);
//		}
		
		for(int data = 100; data <= 1000; data += 100){
			timeHashFunctors(data);
		}
		
	}

}
