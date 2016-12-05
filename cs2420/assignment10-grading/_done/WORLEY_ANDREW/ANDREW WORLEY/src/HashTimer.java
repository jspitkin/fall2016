package assignment10;

import java.util.ArrayList;
import java.util.Random;

/**
 * class used to time hashing only
 * @author Andrew Worley: u0651238
 */
public class HashTimer {
	private static BadHashFunctor badHash = new BadHashFunctor();
	private static MediocreHashFunctor medHash = new MediocreHashFunctor();
	private static GoodHashFunctor goodHash = new GoodHashFunctor();
	private static ArrayList<String> stringList = new ArrayList<String>();
	private static long start;
	private static long end;
	private static long total;
	private static int listSize = 1000;
	private static final int TESTS = 10;
	private static final int ITERATIONS = 1000;
	
	public static void main(String[] args) {
		//create the list of strings
		for (int index = 0; index < listSize; index++) {
			String input = "";
			for (int i = 0; i < 10; i++) {
				input += ""+(char) (new Random().nextInt(122) + 33);
			}
			stringList.add(input);
		}
		
		for (int i = 0; i < TESTS; i++) {
			total = 0;
			
			for (int n = 0; n < ITERATIONS; n++) {
				for (int j = 0; j < stringList.size(); j++) {
					String item = stringList.get(j);
					
					start = System.nanoTime();
					badHash.hash(item);
//					medHash.hash(item);
//					goodHash.hash(item);
					end = System.nanoTime()-start;
					
					total += end;
				}
			}
			System.out.println((i+1)+":\t"+ (total/ITERATIONS));
		}
		
	}
}
