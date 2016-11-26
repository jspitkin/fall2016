package assignment10;

import java.util.Random;

public class HashCollisionsTest {
	
	public static void main(String [] args) {
		
		BadHashFunctor badFunc = new BadHashFunctor();
		MediocreHashFunctor midFunc = new MediocreHashFunctor();
		GoodHashFunctor goodFunc = new GoodHashFunctor();
		
		QuadProbeHashTable q1 = new QuadProbeHashTable(10000, goodFunc);
		ChainingHashTable c1 = new ChainingHashTable(100, midFunc);
		String selectChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
	    Random rand = new Random();
	    
	    for(int i = 0; i < 1500; i++) {
	    	for(int stringL = 0; stringL < 7; stringL++) {
	    		sb.append(selectChars.charAt(rand.nextInt(selectChars.length())));
	    	}
	    	String result = sb.toString();
	    	q1.add(result);
	    	//c1.add(result);
	    }
	    
		System.out.println(q1.getCollisions());
		}
	}
