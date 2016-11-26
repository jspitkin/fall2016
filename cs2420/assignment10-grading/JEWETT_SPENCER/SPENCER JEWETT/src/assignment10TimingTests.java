package assignment10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Timing tests for Assignment 10
 * 
 * @author Spencer Jewett (U0677872)
 * @since 11/10/2016
 *
 */
public class assignment10TimingTests {

	private static int iteration_amount = 100; //Number of tests to be completed in a set size.
	private static int max_power = 20;
	
	public static ArrayList<String> createWordList(ArrayList<String> list){
		ArrayList<String> result = new ArrayList<String>();
		File document = new File("dictionary.txt");
		try (Scanner fileInput = new Scanner(document)) {

			while (fileInput.hasNext()) {
				String s = fileInput.next();
				if (!s.equals("")) {
					result.add(s);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File " + document + " cannot be found.");
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000); //Used to get the machine going.
		
			for(int power = 10; power <= max_power; power++) { //Test sizes from 2^10 to 2^20 where "power" is the exponent.
				
				int size = (int)Math.pow(2, power); //Size of data set being created. 
				
				//Averaging out over several tests.
				long medicoreHashFunctorTime = 0;
				long badHashFunctorTime = 0;
				long goodHashFunctorTime = 0;
				
				ChainingHashTable testChainTable;
				BadHashFunctor badHF = new BadHashFunctor();
				MediocreHashFunctor medHF = new MediocreHashFunctor();
				GoodHashFunctor goodHF = new GoodHashFunctor();
				int medCollisions = 0;
				int badCollisions = 0;
				int goodCollisions = 0;
				
				ArrayList<String> testerList = new ArrayList<String>();
				for (int i = 0; i < power; i+=2){
					testerList = createWordList(testerList);
				}
		
				
				for (int tests = 0; tests < iteration_amount; tests++) {
					
					for(String subject : testerList){
					long start1 = System.nanoTime();
					badHF.hash(subject);
					long stop1 = System.nanoTime();
					badHashFunctorTime += stop1 - start1;
					
					long start2 = System.nanoTime();
					medHF.hash(subject);
					long stop2 = System.nanoTime();
					medicoreHashFunctorTime += stop2 - start2;
					
					long start3 = System.nanoTime();
					goodHF.hash(subject);
					long stop3 = System.nanoTime();
					goodHashFunctorTime += stop3 - start3;
					}
					
					testChainTable = new ChainingHashTable(size/2, badHF);
					testChainTable.addAll(testerList);
					badCollisions += testChainTable.collisionCount();
					testChainTable.clear();
					
					testChainTable = new ChainingHashTable(size/2, medHF);
					testChainTable.addAll(testerList);
					medCollisions += testChainTable.collisionCount();
					testChainTable.clear();
					
					testChainTable = new ChainingHashTable(size/2, goodHF);
					testChainTable.addAll(testerList);
					goodCollisions += testChainTable.collisionCount();
					testChainTable.clear();
					
				}
				
				double averageTimeBadHashFunctor = badHashFunctorTime / (double)iteration_amount;
				double averageTimeMediocreHashFunctor = medicoreHashFunctorTime / (double)iteration_amount;
				double averageTimeGoodHashFunctor = goodHashFunctorTime / (double)iteration_amount;
				int averageCollisionsBadHash = badCollisions / iteration_amount;
				int averageCollisionsMediocreHash = medCollisions / iteration_amount;
				int averageCollisionsGoodHash = goodCollisions / iteration_amount;
				
				System.out.println(size + "\t" + averageTimeBadHashFunctor  + "\t" + averageTimeMediocreHashFunctor + "\t" + averageTimeGoodHashFunctor + "\t"
									+ averageCollisionsBadHash + "\t" + averageCollisionsMediocreHash + "\t" + averageCollisionsGoodHash); //print this data to the console.
			}
			
			iteration_amount = 1000;
			max_power = 20;
			
			//QUESTION 6 TIMING TESTS
			long startTime2 = System.nanoTime();
			while (System.nanoTime() - startTime2 < 1000000000); //Used to get the machine going.
				
				// Contains Timing
				for(int power2 = 10; power2 <= max_power; power2++) { //Test sizes from 2^10 to 2^20 where "power" is the exponent.
					//2915 is the dictionary
					int size2 = (int)Math.pow(2, power2); //Size of data set being created. 
					
					//Averaging out over several tests.
					long quadProbeTotalTime = 0;
					long chainingTotalTime = 0;
					int collisionsQuadProbe = 0;
					int collisionsChaining = 0;
					GoodHashFunctor testFunctor = new GoodHashFunctor();
					ChainingHashTable chainTester = new ChainingHashTable(size2, testFunctor);
					QuadProbeHashTable quadTester = new QuadProbeHashTable(size2, testFunctor);
					
					ArrayList<String> testerList2 = new ArrayList<String>();
					for (int i = 0; i < power2; i+=2){
						testerList2 = createWordList(testerList2);
					}

					
					for (int tests = 0; tests < iteration_amount; tests++) {
												
						//timing portion for question 4.
						long start4 = System.nanoTime();
						quadTester.addAll(testerList2);
						long stop4 = System.nanoTime();
						quadProbeTotalTime += stop4 - start4;
						collisionsQuadProbe += quadTester.collisionCounter();
						quadTester.clear();
						
						long start5 = System.nanoTime();
						chainTester.addAll(testerList2);
						long stop5 = System.nanoTime();
						chainingTotalTime += stop5 - start5;
						collisionsChaining += chainTester.collisionCount();
						chainTester.clear();
						
					}
					double averageTimeQuadProbe = quadProbeTotalTime / (double)iteration_amount;
					double averageTimeChaining = chainingTotalTime / (double)iteration_amount;
					int averageCollisionsQuadProbe = collisionsQuadProbe / iteration_amount;
					int averageCollisionsChaining = collisionsChaining / iteration_amount;
					
					System.out.println(size2 + "\t" + averageTimeQuadProbe + "\t" + averageTimeChaining + "\t" + averageCollisionsQuadProbe + "\t" + averageCollisionsChaining); //print this data to the console.
				}

	}
}