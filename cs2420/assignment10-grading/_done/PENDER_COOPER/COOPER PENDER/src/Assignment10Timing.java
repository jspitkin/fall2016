package assignment10;

import java.util.Random;

/**
 * This class is used to time several of
 * the methods of both a quadratic probing hash table,
 * and a separate chaining hash table.
 * 
 * @author Cooper Pender (u0843147)
 * 
 * Last Edited On: 11/5/16
 */
public class Assignment10Timing {

	private static final int iteration_amount = 300; //Number of tests to be completed in a set size.

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000); //Used to get the machine going.
		
			for(int power = 10; power <= 20; power++) { //Test sizes from 2^10 to 2^20 where "power" is the exponent.
				
				int size = (int)Math.pow(2, power); //Size of data set being created. 
				
				BadHashFunctor badFunc = new BadHashFunctor();
				MediocreHashFunctor midFunc = new MediocreHashFunctor();
				GoodHashFunctor goodFunc = new GoodHashFunctor();
				
				//Averaging out over several tests.
				long totalTime = 0;
				int totColl = 0;
			
				QuadProbeHashTable testQuad = new QuadProbeHashTable(size, goodFunc);
				ChainingHashTable chainTest = new ChainingHashTable(size, goodFunc);
				String selectChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				StringBuilder sb = new StringBuilder();
			    Random rand = new Random();
				
				for (int tests = 0; tests < size; tests++) {
					
					for(int stringL = 0; stringL < 7; stringL++) {
			    		sb.append(selectChars.charAt(rand.nextInt(selectChars.length())));
			    	}
			    	String result = sb.toString();

					long start = System.nanoTime();
					//testQuad.add(result);
					chainTest.add(result);
					long stop = System.nanoTime();
					totalTime += stop - start;
					totColl += testQuad.getCollisions();
				}
				double averageTime = (totalTime / (double)iteration_amount); //taking the average time for the set.
				System.out.println(size + "\t" + totColl); //print this data to the console.
		}
	}
}
	
