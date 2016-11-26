package assignment10;

import java.util.Random;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
public class HashTableTiming {

	public static void main(String[] args){
		HashTableTimingTest();
	}
	
	
	
	public static void HashTableTimingTest() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;
		BadHashFunctor a = new BadHashFunctor();
		MediocreHashFunctor a2 = new MediocreHashFunctor();
		GoodHashFunctor a3 = new GoodHashFunctor();
		ChainingHashTable abc2 = new ChainingHashTable(10, a);

		QuadProbeHashTable abc = new QuadProbeHashTable(10, a2);
		for (int increase = 0; increase <= 15000; increase += 1000) {

			long totalTime = 0;

			// TIME IT!
			long start = System.nanoTime();
			for (int i = 0; i < increase; i++) {
				Random b = new Random();
				abc2.add(randomString(b.nextInt(100)));
				//abc2.add(randomString(5));
			}

			// will increase the increase by 10 and change the random string
			// length by 10 each time through the loop
			long stop = System.nanoTime();
			totalTime += stop - start;

			double averageTime = totalTime;
			System.out.println(increase + "\t" + averageTime);
			//System.out.println(increase + "\t" + abc2.collisions);
		}
	}

	public static String randomString(int length) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			// ASCII values a-z,A-Z are contiguous (52 characters)
			Random r = new Random();
			char randomChar = (char) ((r.nextInt(26) + 'a'));// This will throw
																// a null
																// pointer! Find
																// the bug and
																// squash it!

			stringBuilder.append(randomChar);

		}
		return stringBuilder.toString();
	}

}
