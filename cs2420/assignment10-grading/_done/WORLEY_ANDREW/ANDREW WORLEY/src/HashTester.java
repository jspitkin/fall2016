package assignment10;

import java.util.ArrayList;

/**
 * Test various hash methods and find collisions and the
 * smallest difference between hash indices.
 * 
 * @author Andrew Worley: u0651238
 *
 */
public class HashTester {
	
	public static void main (String[] args) {
//		BadHashFunctor test = new BadHashFunctor();
//		MediocreHashFunctor test = new MediocreHashFunctor();
		GoodHashFunctor test = new GoodHashFunctor();
		
		int tableSize = 23;
		String[] testStrings = {"jello", 
								"zello",
								"kello",
								"vello",
								"fellow", 
								"hello", 
								"hollow", 
								"cello", 
								"mellow", 
								"bello", 
								"bellows"};
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for (String s : testStrings) {
			int index = test.hash(s) % tableSize;
			indices.add(index);
		}
		
		int smallestDiff = indices.size();
		
		//find smallest difference between all indices
		for (int i = 0; i < indices.size(); i++) {
			for (int j = i+1; j <indices.size(); j++) {
				if(Math.abs(indices.get(i) - indices.get(j)) < smallestDiff) {
					smallestDiff = Math.abs(indices.get(i) - indices.get(j));
				}
			}
		}
		
		//find collisions
		int collisons = 0;
		
		for (int i = 0; i < indices.size(); i++) {
			for (int j = i+1; j <indices.size(); j++) {
				if(indices.get(i) == indices.get(j)) {
					collisons++;
				}
			}
		}
		
		System.out.println("Backing array size: "+ tableSize);
		System.out.println("Smallest Difference between hash indices: "+ smallestDiff);
		System.out.println("Collisons: "+ collisons);
	}
}
