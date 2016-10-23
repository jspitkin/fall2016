package assignment07;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class used to time the features of LinkedListStack
 * 
 * @author Gabe Brodbeck u0847035
 */

public class LinkedListStackTimer {
	
	
	public static void main(String[] args){
	int[] nNums= {1000,5000,10000,20000,30000,40000,50000,60000, 70000,80000,90000,100000};
	
	int numberOfTests=100;
	Random r= new Random();
	LinkedListStack<Integer> list= new LinkedListStack();
	
	
	for(int i=0;i<nNums.length;i++){
		
		
	long[] ans= new long[numberOfTests];
	
	for(int m=0;m<nNums[i];m++){
		
		list.push(new Integer(m));
	
	}
	for(int j=0;j<numberOfTests;j++){
		
			long startTime=System.nanoTime();
		list.push(new Integer(j));
		
		long stopTime=System.nanoTime();
		
		ans[j]=stopTime-startTime;
		
	}
	System.out.println(nNums[i]+"\t"+calculateAverageInChunks(ans));
	}
	System.out.println();
	System.out.println();
	
	for(int i=0;i<nNums.length;i++){
		
		
	long[] ans= new long[numberOfTests];
	
	for(int m=0;m<nNums[i];m++){
		
		list.push(new Integer(m));
	
	}
	for(int j=0;j<numberOfTests;j++){
		
			long startTime=System.nanoTime();
		list.pop();
		
		long stopTime=System.nanoTime();
		
		ans[j]=stopTime-startTime;
		
	}
	System.out.println(nNums[i]+"\t"+calculateAverageInChunks(ans));
	}
	System.out.println();
	System.out.println();
	for(int i=0;i<nNums.length;i++){
		
		
	long[] ans= new long[numberOfTests];
	
	for(int m=0;m<nNums[i];m++){
		
		list.push(new Integer(m));
	
	}
	for(int j=0;j<numberOfTests;j++){
		
			long startTime=System.nanoTime();
		list.peek();
		
		long stopTime=System.nanoTime();
		
		ans[j]=stopTime-startTime;
		
	}
	System.out.println(nNums[i]+"\t"+calculateAverageInChunks(ans));
	}
	
	}
	public static long calculateAverageInChunks(long[] times){
		int numberToSplit=100;
		long[] smallerAverages=new long[times.length/numberToSplit];
		int trackSpot=0;
		
		for(int i=0;i<times.length;i+=numberToSplit){
			long segmentTotal=0;
			for(int j=i;j<numberToSplit+i;j++){
		segmentTotal+=times[j];	
			
			}
			smallerAverages[trackSpot]= segmentTotal/numberToSplit;
			/*if(smallerAverages[trackSpot]==0){
				System.err.println("Not small enough divisions");
			}*/
		trackSpot++;	
		}
		
		long smallTotal=0;
		for(int i=0;i<smallerAverages.length;i++){
			smallTotal+=smallerAverages[i];
		}
		return smallTotal/smallerAverages.length;
		
	}
}
	
