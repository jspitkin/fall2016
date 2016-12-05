package assignment10;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class used to test collisons and runtime for adding items to a different hash tables
 * @author Andrew Worley: u0651238
 */
public class QuadProbeAndSeperateChainTimer {
	
	private static QuadProbeHashTable qpHashTable;
	private static ChainingHashTable scHashTable;
	private static BadHashFunctor badHash = new BadHashFunctor();
	private static MediocreHashFunctor medHash = new MediocreHashFunctor();
	private static GoodHashFunctor goodHash = new GoodHashFunctor();
	private static ArrayList<String> stringList = new ArrayList<String>();
	private static long start;
	private static long end;
	private static long total;
	private static int capacity = 100;
	private static int listSize = 10000;
	private static final int ITERATIONS = 10;
	
	public static void main (String[]args) {
		//create the list of strings
		for (int index = 0; index < listSize; index++) {
			String input = "";
			for (int i = 0; i < 10; i++) {
				input += ""+(char) (new Random().nextInt(122) + 33);
			}
			stringList.add(input);
		}
		
		while (System.nanoTime() - start < 1_000_000_000);
		
//		qpHashTable = new QuadProbeHashTable(capacity, badHash);
//		qpHashTable = new QuadProbeHashTable(capacity, medHash);
		qpHashTable = new QuadProbeHashTable(capacity, goodHash);
		
//		scHashTable = new ChainingHashTable(capacity, badHash);
//		scHashTable = new ChainingHashTable(capacity, medHash);
		scHashTable = new ChainingHashTable(capacity, goodHash);
		
		for (int i = 0; i < ITERATIONS; i++) {
			total = 0;
			qpHashTable.clear();
			scHashTable.clear();
			
			for (int j = 0; j < stringList.size(); j++) {
				String item = stringList.get(j);
				
				start = System.nanoTime();
//				qpHashTable.add(item);
				scHashTable.add(item);
				end = System.nanoTime()-start;
				
				total += end;
			}
			System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t" + qpHashTable.getCollisons());
//			System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t" + scHashTable.getCollisons());
		}
		
		
	}

}
