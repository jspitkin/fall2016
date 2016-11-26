package assignment10;

import java.util.ArrayList;
import java.util.Random;
/**
 * Hash table time test
 * 
 * @author Jiwon Nam
 *
 */
public class HashSetTimer {

	public static void main(String[] args) {
		HashFunctor badFunctor = new BadHashFunctor();
		HashFunctor medFunctor = new MediocreHashFunctor();
		HashFunctor goodFunctor = new GoodHashFunctor();
		hashTest(badFunctor);
	}

	public static void hashTest(HashFunctor functor) {
		long start, end;
		int N = 1000;
		int loop = 100;
		
		int initCapacity = 7;
		QuadProbeHashTable qHash = new QuadProbeHashTable(initCapacity, functor);
		ChainingHashTable cHash = new ChainingHashTable(initCapacity, functor);
		qHash.setLoadFactor(0.5);
		cHash.setLamda(1);
		int[][] numCol = new int[10][10];
		long[][] timeCheck = new long[10][10];
		int loadCount = 0;
		int sizeCount = 0;
		// depending on loadFactor
		// collision count, run - time
		
		// change load factor 
		for(double loadFactor = 0.5; loadFactor <= 0.5; loadFactor += 0.1) {				
			sizeCount = 0;
			int lamda = (int)(loadFactor * 100);
			for (int j = N; j <= N * 10 ; j += N) {
				int totalCollision = 0;
				long totalTime = 0;
				ArrayList<String> list = generateRandomString(j);
				for (int i = 0; i < loop; i++) {
					
					qHash = new QuadProbeHashTable(initCapacity, functor);
					qHash.setLoadFactor(loadFactor);
					cHash.setLamda(lamda);
					start = System.nanoTime();
//					qHash.addAll(list);
					cHash.addAll(list);
					end = System.nanoTime();		
					
					totalCollision += qHash.numOfCollision();
					totalTime += end - start;
					qHash.clear();
				}
//				System.out.println(totalCollision / loop);
//				numCol[loadCount][sizeCount] = totalCollision / loop;
				timeCheck[loadCount][sizeCount] = totalTime / loop;	
				sizeCount++;
			}	
			loadCount++;
			
		}
		System.out.println("number of Collisions");
		for(int i = 1; i <= 10; i++) {
			System.out.println("at load Factor" + ((double)i * 0.1));
			for(int j = 0; j < 10; j++) {
				System.out.println("size : " + ((j + 1) * 1000));
//				System.out.println(numCol[i - 1][j]);
				System.out.println(timeCheck[i - 1][j]);
			}
		}
		
		// run - time
		
		
		qHash = new QuadProbeHashTable(initCapacity, functor);
		qHash.setLoadFactor(0.5);	// select best load factor
		// run-time
	}
	
	public static ArrayList<String> generateRandomString(int size) {
		ArrayList<String> list = new ArrayList<String> ();
		
		for(int i = 0; i < size; i++) {
			Random ranLength = new Random();
			String word = "";
			word += "" + i;
			
			list.add(word);
		}
		
		return list;
	}
}
