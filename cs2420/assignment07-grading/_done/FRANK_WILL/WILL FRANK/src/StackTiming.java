package assignment07;

public class StackTiming {

	public static void main(String[] args) {

		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		
		int maxSize = 1000000;
		int sampleSize = 100000;
		
		float startTime, totalTimePush = 0, totalTimePeek = 0, totalTimePop = 0;
		
		System.out.println("\t Push \t Peek \t Pop");
		
		for (int size = 0; size < maxSize ; size++) {
			stack.push(size);
			
			if (size%10000 == 0)
			{
			
				for (int i = 0; i < sampleSize; i++)
				{
					startTime = System.nanoTime();
					stack.push(i);
					totalTimePush += System.nanoTime() - startTime;

					startTime = System.nanoTime();
					stack.peek();
					totalTimePeek += System.nanoTime() - startTime;

					startTime = System.nanoTime();
					stack.pop();
					totalTimePop += System.nanoTime() - startTime;

				}
				
				System.out.println(size + "\t" + totalTimePush/sampleSize + "\t" + totalTimePeek/sampleSize + "\t" + totalTimePop/sampleSize);
				
				totalTimePush = 0;
				totalTimePeek = 0; 
				totalTimePop = 0;
			}
			
		}
		

	}

}
