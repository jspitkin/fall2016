package assignment10;

import java.util.ArrayList;

public class HashTester {
	
	public static void main(String[] args){
		QuadProbeHashTable hash = new QuadProbeHashTable(3, new MediocreHashFunctor());
		hash.add("hello");
		hash.add("yo");
		hash.add("hi");
		hash.add("blue");
		hash.add("trump");
		hash.add("blue");
		hash.add("fellow");
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add("hello");
		arrList.add("yo");
		arrList.add("blue");
		
		System.out.println(hash.containsAll(arrList));
	
	}

}
