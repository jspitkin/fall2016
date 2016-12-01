package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FunctorCollisionsExperiment {
	private static HashFunctor goodHash = new GoodHashFunctor();
	private static HashFunctor mediocreHash = new MediocreHashFunctor();
	private static HashFunctor badHash = new BadHashFunctor();
	private static char[] charArray;
	
	public static void main(String[] args) {
		
		charArray = new char[26];
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < 26; i++){
			charArray[i] = alphabet.charAt(i);
		}

		int i = 0;
		while (i < 1000000000) { // empty block
	    	i++;
	    }
		
		functorCollisions();
	}    
	
	private static void functorCollisions(){
		int TESTS = 10;
		int exp = 2;
		int ITERATIONS = 1000;
		long totalCollisions;
		
		ArrayList<ArrayList<String>> stringLists = new ArrayList<ArrayList<String>>();
		QuadProbeHashTable goodTable = new QuadProbeHashTable(7, goodHash);
		QuadProbeHashTable okTable = new QuadProbeHashTable(7, mediocreHash);
		QuadProbeHashTable badTable = new QuadProbeHashTable(7, badHash);
		
		try(FileWriter fw = new FileWriter(new File("functorCollisions.tsv"))) {
			int[] collectionSize = new int[TESTS];

			for (int i = 0; i < TESTS; i++) {
				int n = (int) Math.pow(2, exp);
				ArrayList<String> newList = generateRandomStringArray(10, n);
				stringLists.add(newList);
				collectionSize[i] = n;
				exp++;
			}
			
			for(int i = 0; i < 1000000; i++){}
			
			System.out.println("Tests initiated badFunctor:");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				totalCollisions = 0;
				for (int j = 0; j < ITERATIONS; j++) {					
					badTable.addAll(stringLists.get(i));
					totalCollisions += badTable.getCollisions();
					badTable.clear();
				}
				System.out.println((i+1)+":\t"+ (totalCollisions/ITERATIONS) +"\t"+ collectionN);
				fw.write((totalCollisions/ITERATIONS) +"\t"+ collectionN + "\n");
			}
			fw.write("newData" + "\n");
			
			for(int i = 0; i < 1000000; i++){}
			
			System.out.println("Tests initiated mediocreHash:");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				totalCollisions = 0;
				for (int j = 0; j < ITERATIONS; j++) {
					okTable.addAll(stringLists.get(i));
					totalCollisions += okTable.getCollisions();
					okTable.clear();
				}
				System.out.println((i+1)+":\t"+ (totalCollisions/ITERATIONS) +"\t"+ collectionN);
				fw.write((totalCollisions/ITERATIONS) +"\t"+ collectionN + "\n");
			}
			fw.write("newData" + "\n");
			
			for(int i = 0; i < 1000000; i++){}

			System.out.println("Tests initiated goodHash:");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				totalCollisions = 0;
				for (int j = 0; j < ITERATIONS; j++) {
					goodTable.addAll(stringLists.get(i));
					totalCollisions += goodTable.getCollisions();
					goodTable.clear();
				}
				System.out.println((i+1)+":\t"+ (totalCollisions/ITERATIONS) +"\t"+ collectionN);
				fw.write((totalCollisions/ITERATIONS) +"\t"+ collectionN + "\n");
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FunctorCollisionsCharter charter = new FunctorCollisionsCharter();
		charter.createChart("functorCollisions.tsv", "functorCollisionChart.png");
		System.out.println("Tests complete.");
	}
	
	/**
	 * generates an arraylist of random strings
	 * @param maxStringLength the maximum length of the strings in the array (minimum length is 3)
	 * @param arraySize
	 * @return
	 */
	private static ArrayList<String> generateRandomStringArray(int maxStringLength, int arraySize){
		ArrayList<String> retArray = new ArrayList<String>();
		
		for(int i = 1; i <= arraySize; i++){
			String string = generateRandomString(maxStringLength);
			//ensure that only unique strings are added to the list
			while(retArray.contains(string))
				string = generateRandomString(maxStringLength);
			retArray.add(string);
		}
		
		return retArray;
	}
	
	/**
	 * creates a random string between 3 and the given maxLength
	 * @param maxLength the maximum size the string can be
	 * @return a random string
	 */
	private static String generateRandomString(int maxLength){
		//minimum string length is 3
		int length = (int) (Math.random()*(maxLength-3))+4;
		String newString = "";
		
		for(int i = 0; i < length; i++){
			newString += charArray[(int)(Math.random()*26)];
		}
		
		return newString;
	}
}
