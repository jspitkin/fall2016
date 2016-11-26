package Assignment08;

import java.util.TreeSet;

/**
 * 
 * @author Yuhong Lin && Yixiong Qin
 *
 */
public class Timing {
	public static void main(String[] args) {
		System.out.println("Size \t Average Time");
		System.out.println("---------------------------");
		Test44();
	}

	public static void Test31() {

		for (int i = 1000, j = 0; i <= 10000 && j < 1000; i += 100, j++) {
			BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();

			long startTime, stopTime;

			for (int g = 0; g < 1000; g++) {
				test.add(g);
			}

			startTime = System.nanoTime();

			test.contains(j);

			stopTime = System.nanoTime();
			double averageTime = Math.abs((startTime - stopTime));

			System.out.println(i + "\t" + averageTime);
		}
	}

	public static void Test32() {

		for (int i = 1000, j = 0; i <= 10000 && j < 1000; i += 100, j++) {
			BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();

			long startTime, stopTime;

			int[] a = new int[1000];
			for (int f = 0; f < 1000; f++) {
				int g = randoms();
				test.add(g);
				a[f] = g;
			}

			startTime = System.nanoTime();

			
				test.contains(a[j]);
			
			stopTime = System.nanoTime();
			double averageTime = Math.abs((startTime - stopTime));

			System.out.println(i + "\t" + averageTime);
		}
	}

	public static int randoms() {
		int i = (int) (Math.random() * 1000);
		return i;

	}
	
	public static void Test41() {

		for (int i = 1000, j = 0; i <= 10000 && j < 1000; i += 100, j++) {
			TreeSet<Integer> test = new TreeSet<Integer>();

			long startTime, stopTime;

			
			startTime = System.nanoTime();

			
				test.add(randoms());
			
			stopTime = System.nanoTime();
			double averageTime = Math.abs((startTime - stopTime));

			System.out.println(i + "\t" + averageTime);
		}
	}
	
	public static void Test42() {

		for (int i = 1000, j = 0; i <= 10000 && j < 1000; i += 100, j++) {
			TreeSet<Integer> test = new TreeSet<Integer>();

			long startTime, stopTime;

			int[] a = new int[1000];
			for (int f = 0; f < 1000; f++) {
				int g = randoms();
				test.add(g);
				a[f] = g;
			}

			startTime = System.nanoTime();

			
				test.contains(a[j]);
			
			stopTime = System.nanoTime();
			double averageTime = Math.abs((startTime - stopTime));

			System.out.println(i + "\t" + averageTime);
		}
	}
	
	public static void Test43() {

		for (int i = 1000, j = 0; i <= 10000 && j < 1000; i += 100, j++) {
			BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();

			long startTime, stopTime;

			
			startTime = System.nanoTime();

			
				test.add(randoms());
			
			stopTime = System.nanoTime();
			double averageTime = Math.abs((startTime - stopTime));

			System.out.println(i + "\t" + averageTime);
		}
	}
	
	public static void Test44() {

		for (int i = 1000, j = 0; i <= 10000 && j < 1000; i += 100, j++) {
			BinarySearchTree<Integer> test = new BinarySearchTree<Integer>();

			long startTime, stopTime;

			int[] a = new int[1000];
			for (int f = 0; f < 1000; f++) {
				int g = randoms();
				test.add(g);
				a[f] = g;
			}

			startTime = System.nanoTime();

			
				test.contains(a[j]);
			
			stopTime = System.nanoTime();
			double averageTime = Math.abs((startTime - stopTime));

			System.out.println(i + "\t" + averageTime);
		}
	}
}
