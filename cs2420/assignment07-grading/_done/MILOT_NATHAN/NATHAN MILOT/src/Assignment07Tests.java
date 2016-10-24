package assignment07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Class for Assignment07 with timing code
 * @author Nathan Milot u1063587
 * @since Oct 11, 2016
 */
public class Assignment07Tests {
	
	// Private Member Variables
	LinkedListStack<String> stringStack;
	BalancedSymbolChecker symbolChecker;
	// Timing Private Member Variables
	int iterMax = 100;
	int expMax = 20;
	boolean runTimingTests = true;
	
	@Before
	public void setUp() throws Exception {
		stringStack = new LinkedListStack<>();
		symbolChecker = new BalancedSymbolChecker();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// LinkedListStack Tests
	@Test
	public void linkedListStackSizePushPeekAndPopTests() {
		for(int i = 0; i < 100; i++){
			stringStack.push(i + "");
			assertTrue(stringStack.peek().equals(i + ""));
		}
		
		for(int i = stringStack.size() - 1; i >= 0; i--)
			assertTrue(stringStack.pop().equals(i + ""));
		
		assertEquals(0,stringStack.size());
	}

	// BalancedSymbolChecker Tests
	@Test
	public void balancedSymbolChecker(){
		String path = "";
		String finalPath = "";
		try{
			String testName = "Balanced Symbol Cheker Results";
			path = System.getProperty("user.dir");  
	        path.replace("\\\\", "/");  
	        path +=  "\\A7_examples";
	        File folder = new File(path);
	        if(!folder.exists())
	        	folder.mkdirs();
	        finalPath = path + "\\" + testName + ".txt";
			FileWriter fileWriter = new FileWriter(finalPath);
			for(int i = 1; i < 15; i++){
				String filename = path + "\\Class" + i + ".java";
				String result = "Class" + i + ".java results in the message \"" + symbolChecker.checkFile(filename) + "\"\n";
				fileWriter.write(result);
			}
			fileWriter.close();
		}catch(Exception e){
		}
		
		try {
			BufferedReader myResults = new BufferedReader(new FileReader(finalPath));
			BufferedReader expectedResults = new BufferedReader(new FileReader(path + "\\Expected Results.txt"));
			
			String testResult = "";
			while((testResult = myResults.readLine()) != null){
				assertEquals(expectedResults.readLine(), testResult);
			}
			myResults.close();
			expectedResults.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Timing Code
	// LinkedListStack Timing	
	@Test
	public void linkedListStackPeekTiming(){
		if(!runTimingTests)	return;
		int iterTmp = iterMax;
		iterMax = 100_000_000;
		try {
			String testName = "LinkedListStack_Peek";
			String path = System.getProperty("user.dir");  
	        path.replace("\\\\", "/");  
	        path +=  "/assignment07TimingData";
	        File folder = new File(path);
	        if(!folder.exists())
	        	folder.mkdirs();
	        path += "/" + testName + "_Timing.tsv";
			FileWriter fileWriter = new FileWriter(path);
			long totalTime;
			int size = 0;
			String header = testName + "\nSize\tAverage Time (nano seconds)\n";
			fileWriter.write(header);
			for(int exp = 10; exp <= expMax; exp++){
				totalTime = 0;
				size =(int) Math.pow(2, exp);
				for(int i = 0; i < size; i++){
					stringStack.push("a");
				}
				for(int iter = 0; iter < iterMax; iter++){
					long start = System.nanoTime();
					stringStack.peek();
					long end = System.nanoTime();
					totalTime += (end - start);
				}
				double averageTime = totalTime/(double)iterMax;
				String data = size + "\t" + averageTime + "\n";
				fileWriter.write(data);
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		iterMax = iterTmp;
	}
	
	@Test
	public void linkedListStackPopTiming(){
		if(!runTimingTests)	return;
		int iterTmp = iterMax;
		iterMax = 100_000_000;
		try {
			String testName = "LinkedListStack_Pop";
			String path = System.getProperty("user.dir");  
			path.replace("\\\\", "/");  
			path +=  "/assignment07TimingData";
			File folder = new File(path);
			if(!folder.exists())
				folder.mkdirs();
			path += "/" + testName + "_Timing.tsv";
			FileWriter fileWriter = new FileWriter(path);
			long totalTime;
			int size = 0;
			String header = testName + "\nSize\tAverage Time (nano seconds)\n";
			fileWriter.write(header);
			for(int exp = 10; exp <= expMax; exp++){
				totalTime = 0;
				size =(int) Math.pow(2, exp);
				for(int i = 0; i < size; i++){
					stringStack.push("a");
				}
				for(int iter = 0; iter < iterMax; iter++){
					long start = System.nanoTime();
					stringStack.pop();
					long end = System.nanoTime();
					totalTime += (end - start);
					stringStack.push("a");
				}
				double averageTime = totalTime/(double)iterMax;
				String data = size + "\t" + averageTime + "\n";
				fileWriter.write(data);
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		iterMax = iterTmp;
	}
	
	@Test
	public void linkedListStackPushTiming(){
		if(!runTimingTests)	return;
		int iterTmp = iterMax;
		iterMax = 100_000_000;
		try {
			String testName = "LinkedListStack_Push";
			String path = System.getProperty("user.dir");  
			path.replace("\\\\", "/");  
			path +=  "/assignment07TimingData";
			File folder = new File(path);
			if(!folder.exists())
				folder.mkdirs();
			path += "/" + testName + "_Timing.tsv";
			FileWriter fileWriter = new FileWriter(path);
			long totalTime;
			int size = 0;
			String header = testName + "\nSize\tAverage Time (nano seconds)\n";
			fileWriter.write(header);
			for(int exp = 10; exp <= expMax; exp++){
				totalTime = 0;
				size =(int) Math.pow(2, exp);
				for(int i = 0; i < size; i++){
					stringStack.push("a");
				}
				for(int iter = 0; iter < iterMax; iter++){
					long start = System.nanoTime();
					stringStack.push("a");
					long end = System.nanoTime();
					totalTime += (end - start);
					stringStack.pop();
				}
				double averageTime = totalTime/(double)iterMax;
				String data = size + "\t" + averageTime + "\n";
				fileWriter.write(data);
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		iterMax = iterTmp;
	}
	
}