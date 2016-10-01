package assignment04;

import java.util.Random;

/**
 * Timing class for the getLargestAnagram method.
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class GetLargestAnagramGroupTimer {

	private static final int MIN_SAMPLE_SIZE = 1024;
	private static final int MAX_SAMPLE_SIZE = 1048576;
	private static final int BILLION = 1000000000;
	
	public static void main(String[] args) {
		
		Random rng = new Random();
		
		for(int sampleSize = MIN_SAMPLE_SIZE; sampleSize < MAX_SAMPLE_SIZE + 1; sampleSize *=2){
			String[] toSearch = new String[sampleSize];
			for(int arrIdx = 0; arrIdx < sampleSize; arrIdx++){
				toSearch[arrIdx] = AnagramTester.randomString(rng.nextInt(15), rng);
			}
			
			long startTime;
			long stopTime;
			
			startTime = System.nanoTime();
			AnagramUtil.getLargestAnagramGroup(toSearch);
			stopTime = System.nanoTime();
			
			long totalTimeNano = stopTime - startTime;
			
			double totalTimeSeconds = totalTimeNano/ (double) BILLION;
			
			System.out.println("" + sampleSize + "\t" + totalTimeSeconds);
		}

	}

}
