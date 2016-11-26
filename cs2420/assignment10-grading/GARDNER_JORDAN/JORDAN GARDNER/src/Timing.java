package assignment10;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * @author Jordan Gardner 
 * Timing code for Assign 10
 *
 */
public class Timing {

	private static Random rand;

	public static void main(String[] args) {

		for (int size = 10_00; size <= 110_00; size += 10_00) {

			int ITER_COUNT = size;
			// QuadProbeHashTable tableg= new QuadProbeHashTable(ITER_COUNT,new
			// BadHashFunctor());
			// QuadProbeHashTable tablem= new QuadProbeHashTable(ITER_COUNT,new
			// MediocreHashFunctor());
			// QuadProbeHashTable tableb= new QuadProbeHashTable(ITER_COUNT,new
			// BadHashFunctor());
			//
			ChainingHashTable tableg = new ChainingHashTable(ITER_COUNT,
					new BadHashFunctor());
			// ChainingHashTable tablem= new ChainingHashTable(ITER_COUNT,new
			// MediocreHashFunctor());
			// ChainingHashTable tableb= new ChainingHashTable(ITER_COUNT,new
			// BadHashFunctor());
			ArrayList<String> list = new ArrayList<String>();
			
			for (int i = 0; i < size; i++) {
				
				SecureRandom random = new SecureRandom();
				
				list.add(new BigInteger(i, random).toString(i));
			}

			rand = new Random();
			
			rand.setSeed(System.currentTimeMillis());
			
			long start = System.nanoTime();
			for (int iter = 0; iter < ITER_COUNT; iter++) {

				tableg.clear();
//				tablem.clear();
//				tableb.clear();

				for (String s : list) {
				
					tableg.add(s);
//					tablem.add(s);
//					tableb.add(s);
				}
			}
			long stop = System.nanoTime();

			double averageTime = (stop - start) / 5_000;// can play around with
														// time here, depending
														// on what your looking
														// for
			// double averageTime=(start-stop)/-1;
			System.out.println(size + "\t" + averageTime / 1000);

		}

	}

}
