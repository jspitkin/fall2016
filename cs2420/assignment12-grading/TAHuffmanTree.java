package assignment12;
import java.io.File;
import java.net.URL;
import java.util.Scanner;


public class TAHuffmanTree {

	public static int score = 0; // Out of 40	
	public static HuffmanTree tree = new HuffmanTree();
	
	public static void main(String[] args){
		
		System.out.println("-------------------------------");
		System.out.println("-----Small File Compression----");
		biggerCompress("inClass1.txt");
		decompress("compinClass1.txt");
		biggerCompress("helloWorld.txt");
		decompress("comphelloWorld.txt");
		biggerCompress("tie_1.txt");
		decompress("comptie_1.txt");
		biggerCompress("abcs.txt");
		decompress("compabcs.txt");
		biggerCompress("tie_2.txt");
		decompress("comptie_2.txt");
		biggerCompress("tie_3.txt");
		decompress("comptie_3.txt");
		
		System.out.println("\n");
		System.out.println("-------------------------------");
		System.out.println("----Medium File Compression----");
		actualCompress("modernMajor.txt");
		decompress("compmodernMajor.txt");
		actualCompress("shakespeare.txt");
		decompress("compshakespeare.txt");
		
		System.out.println("\n");
		System.out.println("-------------------------------");
		System.out.println("----Large File Compression----");
		actualCompress("guliversTravels.txt");
		decompress("compguliversTravels.txt");
		actualCompress("senseAndSensi.txt");
		decompress("compsenseAndSensi.txt");
		//actualCompress("paradiseLost.txt");
		//decompress("compparadiseLost.txt");
		System.out.println("\n");
		
		
		if(score < 20){
			System.out.println("-------------------------------");
			System.out.println("Student failed more than 50% (" + score+"/40) of testing.");
			System.out.println("Partial credit grading.");
			System.out.println("\n");
			System.out.println("compareTo method");
			System.out.println("/2 compareTo correctly compares weight values.");
			System.out.println("/2 compareTo compares character values if weights are equal.");
			System.out.println("/6 compareTo has a tie break for equal characters.");
			System.out.println("/10 Subtotal.");
			System.out.println();
			System.out.println("createTree method");
			System.out.println("/2 new node is created.");
			System.out.println("/2 first two values are dequeued and set to new node.");
			System.out.println("/3 child nodes point back to parent node.");
			System.out.println("/3 final node in queue is set to root.");
			System.out.println("/10 Subtotal.");
			System.out.println();
			System.out.println("getCode method");
			System.out.println("/2 leaf node is some how found.");
			System.out.println("/3 correctly works way back up to root " +
					"(comparing current node to parent's children to determine direction).");
			System.out.println("/3 path is built as parents are traversed.");
			System.out.println("/2 path is correctly converted to an int array of 0 and 1 " +
					"(not of 48 and 49), order is reversed if need be");
			System.out.println("/10 Subtotal.");
			
			System.out.println("-------------------------------");
			System.out.println("--------Scoring Results--------");
			System.out.println();
			System.out.println("/15  TA spot check.");
			System.out.println("/30 partial credit");
			System.out.println("/10  Quality of tests and style.");
			System.out.println();
			System.out.println("/55 Total points for partial credit. (Max 55)");
			
		}else{
		
		
			String spaces;
			if(score < 10)
				spaces = "   ";
			else
				spaces = "  ";
			
			System.out.println("-------------------------------");
			System.out.println("--------Scoring Results--------");
			System.out.println();
			System.out.println("/15  TA spot check.");
			System.out.println((score * (50.0/40.0)) + "/50" + spaces + "Compression/decompression tests.");
			System.out.println("/10  Quality of tests and style.");
			System.out.println();
			System.out.println("/75  Total points.");
		}
		
			System.out.println("\n");
			System.out.println("-------------------------------");
			System.out.println("----------TA Comments----------");
		
		
	}
	
	public static double compress(String fileName){
		try{
			File tempFile = findFile(fileName);
			File compTempFile = new File("comp" + fileName);
			tree.compressFile(tempFile, compTempFile);
			return (double)compTempFile.length()/tempFile.length();
		}catch(Exception e){
			return -1;
		}
	}
	
	public static boolean decompress(String fileName){
		File tempFile = null, decompTempFile = null;
		try{
			tempFile = findFile(fileName);
			decompTempFile = new File("de" + fileName);
			tree.decompressFile(tempFile, decompTempFile);
			File orig = findFile(fileName.substring(4));
			
			Scanner decomp = new Scanner(decompTempFile);
			Scanner origFile = new Scanner(orig);
			boolean failed = false;
			if(!decomp.hasNext()){
				failed = true;
			}
				
			while(origFile.hasNext() && decomp.hasNext()){
				if(!decomp.nextLine().equals(origFile.nextLine())){
					failed = true;
					break;
				}
			}
			
			if(failed){
				System.out.println("FAILED: \"" + fileName.substring(4) + "\" failed decompression.");
				return false;
			}else if(orig.length() == decompTempFile.length()){
				score += 3;				
				System.out.println("PASSED: \"" + fileName.substring(4) + "\" successfully decompressed");
				return true;
			}
			return false;
		}catch(Exception e){
			System.out.println("ERROR: \"" + fileName.substring(4) + "\" threw exception at decompression.");
			return false;
		} finally {
			delete(tempFile);
			delete(decompTempFile);
		}
	}
	
	private static void delete(File file) {
		if(file == null) {
			return;
		}
		if(!file.delete()) {
			file.deleteOnExit();
		}
	}
	
	public static boolean biggerCompress(String fileName){
		double test = compress(fileName);
		if(1 < test){
			System.out.println("PASSED: \"" + fileName + "\" compression resulted in large file.");
			score += 1;
			return true;
		}else if(test == -1){
			System.out.println("ERROR:  Exception thrown on file compression \"" +fileName + "\"");
			return false;
		}else{
			System.out.println("FAILED: File should not have compressed \"" +fileName + "\"");
			return false;
		}
	}
	
	public static boolean actualCompress(String fileName){
		double test = compress(fileName);
		if(1 > test){
			System.out.println("PASSED: \"" + fileName + "\" compression resulted in smaller file.");
			score += 1;
			return true;
		}else if(test == -1){
			System.out.println("ERROR:  Exception thrown on file compression \"" +fileName + "\"");
			return false;
		}else{
			System.out.println("FAILED: File should have compressed \"" +fileName + "\"");
			return false;
		}
	}
	
	public static File findFile(String path) {
		URL url = TAHuffmanTree.class.getClassLoader().getResource("huffman_files/" + path);
		if(url == null) {
			return new File(path);
		}
		return new File(url.getPath());
	}
}
