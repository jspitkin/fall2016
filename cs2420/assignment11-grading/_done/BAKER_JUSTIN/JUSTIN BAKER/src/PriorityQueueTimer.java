package assignment11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import assignment03.BinarySearchSet;
import assignment07.LinkedListStack;

public class PriorityQueueTimer {

	static PriorityQueue<Integer> pq;

	private static int iter_count = 1000;
	static int M_size;
	
	public static void main(String[] args) {
		deleteMin();
//		findMin();
//		addWorst();
//		addBest();
//		addAverage();

	}
	
	public static void deleteMin(){
		System.out.println("deleteMin");
		System.out.println("Size(N)\t" + "get()Time");
		for (int exponent = 2; exponent < 13; exponent++) {
			M_size = (int) Math.pow(2, exponent);
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int i = 0; i < M_size; i++)
				al.add(i);
			deleteMinTimer(M_size, al);
		}
	}

	public static void deleteMinTimer(int size, ArrayList<Integer> al) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;
		for (int iteration = 0; iteration < iter_count; iteration++) {
			//Reconstruct
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for (int i = 0; i < size; i++)
				pq.add(al.get(i));
			//Time
			startTime = System.nanoTime();
			pq.deleteMin();
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}

	
	public static void findMin(){
		System.out.println("findMin");
		System.out.println("Size(N)\t" + "get()Time");
		for (int exponent = 2; exponent < 13; exponent++) {
			M_size = (int) Math.pow(2, exponent);
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int i = 0; i < M_size; i++)
				al.add(i);
			Collections.shuffle(al);
			findMinTimer(M_size, al);
		}
	}
	
	public static void findMinTimer(int size, ArrayList<Integer> al) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;
		for (int iteration = 0; iteration < iter_count; iteration++) {
			//Reconstruct
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for (int i = 0; i < size; i++)
				pq.add(al.get(i));
			//Time
			startTime = System.nanoTime();
			pq.findMin();
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}
	
	public static void addWorst(){
		System.out.println("addWorst");
		System.out.println("Size(N)\t" + "get()Time");
		for (int exponent = 2; exponent < 13; exponent++) {
			M_size = (int) Math.pow(2, exponent);
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int i = 0; i < M_size; i++)
				al.add(M_size-i);
			Collections.shuffle(al);
			addWorstTimer(M_size, al);
		}
	}
	
	public static void addWorstTimer(int size, ArrayList<Integer> al) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;
		for (int iteration = 0; iteration < iter_count; iteration++) {
			//Reconstruct
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for (int i = 0; i < size; i++)
				pq.add(al.get(i));
			//Time
			startTime = System.nanoTime();
			pq.add(0);
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}
	
	public static void addBest(){
		System.out.println("addBest");
		System.out.println("Size(N)\t" + "get()Time");
		for (int exponent = 2; exponent < 13; exponent++) {
			M_size = (int) Math.pow(2, exponent);
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int i = 0; i < M_size; i++)
				al.add(i);
			Collections.shuffle(al);
			addBestTimer(M_size, al);
		}
	}
	
	public static void addBestTimer(int size, ArrayList<Integer> al) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;
		for (int iteration = 0; iteration < iter_count; iteration++) {
			//Reconstruct
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for (int i = 0; i < size; i++)
				pq.add(al.get(i));
			//Time
			startTime = System.nanoTime();
			pq.add(size);
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}
	
	public static void addAverage(){
		System.out.println("addAverage");
		System.out.println("Size(N)\t" + "get()Time");
		for (int exponent = 2; exponent < 13; exponent++) {
			M_size = (int) Math.pow(2, exponent);
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int i = 0; i < M_size; i++)
				al.add(i);
			Collections.shuffle(al);
			int rnd = (int) Math.random()*M_size;
			addAverageTimer(M_size, al, rnd);
		}
	}
	
	public static void addAverageTimer(int size, ArrayList<Integer> al, int rnd) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;
		for (int iteration = 0; iteration < iter_count; iteration++) {
			//Reconstruct
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for (int i = 0; i < size; i++)
				pq.add(al.get(i));
			//Time
			startTime = System.nanoTime();
			pq.add(rnd);
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}
}
