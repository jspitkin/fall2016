package assignment06;

import java.util.ArrayList;

public class DoublyLinkedTester {
	
	 /**
	  * a test client
	  * @param args
	  */
    public static void main(String[] args) {
    	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    	long time;
    	//Is the running time of the addFirst method O(c) as expected?
    	System.nanoTime();
    	list.addFirst(5);
    	time=System.nanoTime();
    	System.out.println("addFirst 1 - "+time*1000000);
    	System.nanoTime();
    	list.addFirst(4);
    	time=System.nanoTime();
    	System.out.println("addFirst 2 - "+time*1000000);
    	System.nanoTime();
    	list.addFirst(3);
    	time=System.nanoTime();
    	System.out.println("addFirst 3 - "+time*1000000);
    	System.nanoTime();
    	list.addFirst(2);
    	time=System.nanoTime();
    	System.out.println("addFirst 4 - "+time*1000000);
    	System.nanoTime();
    	list.addFirst(1);
    	time=System.nanoTime();
    	System.out.println("addFirst 5 - "+time*1000000);
    	System.nanoTime();
    	list.addFirst(0);
    	time=System.nanoTime();
    	System.out.println("addFirst 6 - "+time*1000000);
    	
    	//How does the running time of addFirst(item) for DoublyLinkedList compare to add(0, item) for ArrayList?
    	System.nanoTime();
    	list.add(0,11);
    	time=System.nanoTime();
    	System.out.println("add(0,_) 1 - "+time*1000000);
    	System.nanoTime();
    	list.add(0,10);
    	time=System.nanoTime();
    	System.out.println("add(0,_) 2 - "+time*1000000);
    	System.nanoTime();
    	list.add(0,9);
    	time=System.nanoTime();
    	System.out.println("add(0,_) 3 - "+time*1000000);
    	System.nanoTime();
    	list.add(0,8);
    	time=System.nanoTime();
    	System.out.println("add(0,_) 4 - "+time*1000000);
    	System.nanoTime();
    	list.add(0,7);
    	time=System.nanoTime();
    	System.out.println("add(0,_) 5 - "+time*1000000);
    	System.nanoTime();
    	list.add(0,6);
    	time=System.nanoTime();
    	System.out.println("add(0,_) 6 - "+time*1000000);
    	
    	//Is the running time of the get method O(N) as expected?
    	for(int i = 11; i < 25; i++){
    		list.addLast(i);
    	}
    	
    	System.nanoTime();
    	list.get(list.size()/2);
    	time=System.nanoTime();
    	System.out.println("get25 - "+time*1000000);
    	
    	for(int i = 24; i < 50; i++){
    		list.addLast(i);
    	}
    	
    	System.nanoTime();
    	list.get(list.size()/2);
    	time=System.nanoTime();
    	System.out.println("get50 - "+time*1000000);
    	
    	for(int i = 49; i < 100; i++){
    		list.addLast(i);
    	}
    	
    	System.nanoTime();
    	list.get(list.size()/2);
    	time=System.nanoTime();
    	System.out.println("get100 - "+time*1000000);
    	
    	for(int i = 99; i < 200; i++){
    		list.addLast(i);
    	}
    	
    	System.nanoTime();
    	list.get(list.size()/2);
    	time=System.nanoTime();
    	System.out.println("get200 - "+time*1000000);
    	
    	for(int i = 199; i < 400; i++){
    		list.addLast(i);
    	}
    	
    	System.nanoTime();
    	list.get(list.size()/2);
    	time=System.nanoTime();
    	System.out.println("get400 - "+time*1000000);
    	
    	for(int i = 399; i < 800; i++){
    		list.addLast(i);
    	}
    	
    	System.nanoTime();
    	list.get(list.size()/2);
    	time=System.nanoTime();
    	System.out.println("get800 - "+time*1000000);
    	
    	//How does the running time of get(i) for DoublyLinkedList compare to get(i) for ArrayList?
    	Integer[] arrayList = list.toArray();
    	int eger;

    	System.nanoTime();
    	eger = arrayList[25];
    	time=System.nanoTime();
    	System.out.println("getArray25 - "+time*1000000);
    	System.nanoTime();
    	eger = arrayList[50];
    	time=System.nanoTime();
    	System.out.println("getArray50 - "+time*1000000);
    	System.nanoTime();
    	eger = arrayList[100];
    	time=System.nanoTime();
    	System.out.println("getArray100 - "+time*1000000);
    	System.nanoTime();
    	eger = arrayList[200];
    	time=System.nanoTime();
    	System.out.println("getArray200 - "+time*1000000);
    	System.nanoTime();
    	eger = arrayList[400];
    	time=System.nanoTime();
    	System.out.println("getArray400 - "+time*1000000);
    	System.nanoTime();
    	eger = arrayList[800];
    	time=System.nanoTime();
    	System.out.println("getArray800 - "+time*1000000);
    	
    	//Is the running time of the remove method O(N) as expected?
    	System.nanoTime();
    	list.remove(0);
    	time=System.nanoTime();
    	System.out.println("remove0 - "+time*1000000);
    	System.nanoTime();
    	list.remove(24);
    	time=System.nanoTime();
    	System.out.println("remove25 - "+time*1000000);
    	System.nanoTime();
    	list.remove(49);
    	time=System.nanoTime();
    	System.out.println("remove50 - "+time*1000000);
    	System.nanoTime();
    	list.remove(99);
    	time=System.nanoTime();
    	System.out.println("remove100 - "+time*1000000);
    	System.nanoTime();
    	list.remove(199);
    	time=System.nanoTime();
    	System.out.println("remove200 - "+time*1000000);
    	System.nanoTime();
    	list.remove(399);
    	time=System.nanoTime();
    	System.out.println("remove400 - "+time*1000000);
    	System.nanoTime();
    	list.remove(795);
    	time=System.nanoTime();
    	System.out.println("remove800 - "+time*1000000);
    	
    	//How does the running time of remove(i) for DoublyLinkedList compare to remove(i) for ArrayList?
    	ArrayList<Integer> aList = new ArrayList<Integer>();
    	for(int i = 399; i < 800; i++){
    		aList.add(i);
    	}
    	System.nanoTime();
    	aList.remove(0);
    	time=System.nanoTime();
    	System.out.println("remove0 - "+time*1000000);
    	System.nanoTime();
    	aList.remove(24);
    	time=System.nanoTime();
    	System.out.println("remove25 - "+time*1000000);
    	System.nanoTime();
    	aList.remove(49);
    	time=System.nanoTime();
    	System.out.println("remove50 - "+time*1000000);
    	System.nanoTime();
    	aList.remove(99);
    	time=System.nanoTime();
    	System.out.println("remove100 - "+time*1000000);
    	System.nanoTime();
    	aList.remove(199);
    	time=System.nanoTime();
    	System.out.println("remove200 - "+time*1000000);
    	System.nanoTime();
    	aList.remove(399);
    	time=System.nanoTime();
    	System.out.println("remove400 - "+time*1000000);
    	System.nanoTime();
    	aList.remove(795);
    	time=System.nanoTime();
    	System.out.println("remove800 - "+time*1000000);
    }
}