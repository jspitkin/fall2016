package assignment10;

import java.util.Random;

/**
 * 
 * @author Chase Stephens
 * 
 * Time tests for hash tables. 
 *
 */


public class HashSetTimeTests {

	public static void main(String[] args) {

		Random rand = new Random(65416515);

		BadHashFunctor badFunc = new BadHashFunctor();
		GoodHashFunctor upTownFunc = new GoodHashFunctor();
		MediocreHashFunctor medFunc = new MediocreHashFunctor();
		
//		ChainingHashTable chainTable = new ChainingHashTable(100, upTownFunc);
//		ChainingHashTable chainTable = new ChainingHashTable(100, medFunc);
		ChainingHashTable chainTable = new ChainingHashTable(100, badFunc);
		
		QuadProbeHashTable quadTable = new QuadProbeHashTable(100, upTownFunc);

		for (int size = 100000; size < 1000000; size += 50000) {
			
			long totalChainTime = 0;
			long totalQuadTime = 0;
			
			chainTable.clear();
			quadTable.clear();

			// generates random string to add
			for (int i = 0; i < size; i++) {
				String str = "";
				int length = rand.nextInt(15);
				for (int j = 0; j < length; j++) {
					str += rand.nextInt(100);
				}
				long startChainTime = System.nanoTime();
				chainTable.add(str);
				long stopChainTime = System.nanoTime();
				
//				long startQuadTime = System.nanoTime(); 
//				quadTable.add(str);
//				long stopQuadTime = System.nanoTime();
				
				totalChainTime += (stopChainTime - startChainTime);
//				totalQuadTime += (stopQuadTime - startQuadTime);
				}

			System.out.println("Size" + "\t" + size  + "\t" + 
			"\t" + "average chain time" + "\t" + totalChainTime/(size) + "\t" + "average quad time" + 
					"\t" + totalQuadTime/(size));
		}
	}

}
