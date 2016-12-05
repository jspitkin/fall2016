package assignment12;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
  * @author Eduardo Ortiz
 * U0922628
 * CS 2420 MW 3-4
 */

public class CompressionDemo {
  
  public static void compressFile(String infile, String outfile) {
	 
	  HuffmanTree t = new HuffmanTree();

    t.compressFile(new File(infile), new File(outfile));
    
    t.huffmanToDot("huffman_tree.dot");
  }
  
  public static void decompressFile(String infile, String outfile) {
    HuffmanTree t = new HuffmanTree();

    t.decompressFile(new File(infile), new File(outfile));
   
  }
  
  public static void main(String[] args) {
    compressFile("original.txt", "compressedORIGINAL.txt");
    decompressFile("compressedoriginal.txt", "decompressedORIGNIAL.txt");
    compressFile("SINGLETEXT.txt", "compressedSINGLETEXT.txt");
    decompressFile("compressedSINGLETEXT.txt", "decompressedSINGLETEXT.txt");
    compressFile("LARGE.txt", "compressedLARGE.txt");
    decompressFile("compressedLARGE.txt", "decompressedLARGE.txt");
    compressFile("EMPTY.txt", "compressedEMPTY.txt");
    decompressFile("compressedEMPTY.txt", "decompressedEMPTY.txt");
    System.out.println("done with Initial Testing"); 
    System.out.println("Beginning testing varying frequency on compression ratio");
   
    for(int i = 10; i <= 100; i=i+10){
    	randomWordFile(50000,0,i);
    	File file = new File("outofCharIs500000"+".txt");
    	compressFile(("outofCharIs500000"+".txt"),("compressedFORCHARS"+i+".txt"));
    	File file2 = new File("compressedFORCHARS"+i+".txt");
    	System.out.println("Frequency of Char is " + i + "% Compression sizes are: "+file.length()+" " + file2.length() + " ratio is: " +file2.length()/(double)file.length());      
    	decompressFile(("compressedFORCHARS"+i+".txt"),("decompressFORCHARS"+i+".txt"));
  
   }
    System.out.println("done with testing varying char size on byte Size"); 
    System.out.println("Beginning testing varying unique characters on byte Size for constant char");
    for(int i = 24; i >= 0; i = i - 2){
    	randomWordFile(50000,i,0);
    	File file = new File("outofCharIs50000"+i+".txt");
    	compressFile(("outofCharIs50000"+i+".txt"),("compressedFORCHARS2"+ ".txt"));
    	File file2 = new File("compressedFORCHARS2"+ ".txt");
    	System.out.println("Unique Characters = " + (25-i) +" Compresion sizes : "+ file.length() +" "+ file2.length()+ " ratio is: " +file2.length()/(double)file.length());
    	decompressFile(("compressedFORCHARS2"+".txt"),("decompressFORCHARS2"+i+".txt"));
    
    }
  
  }
  
  /** Method used to generate a random file of desired words, chars while controlling frequency of the chars
 * @param numberOfWords Total number of words you would like 
 * @param uniqueChars Total number of unique characters you would like 
 * @param charFrequency frequency of a character you would like
 */
public static void randomWordFile(int numberOfWords, int uniqueChars, double charFrequency ){
  try{
	  FileWriter f = new FileWriter("outofCharIs"+numberOfWords+ uniqueChars+".txt");
	  BufferedWriter out = new BufferedWriter(f);
	  int wordLength= 5;
	  char[] charHolder = new char[0];
	  	int min = 98+uniqueChars; 
	  	int count = min;
	 
	  	if(charFrequency == 0){
	  		for(int i = 0; i< numberOfWords; i++){
	  			charHolder = new char[wordLength];	
				  for(int y = 0; y < wordLength; y++){
					  if(count > 122)
						  count = min;
					  charHolder[y] = (char)count;
					  count++;
					  
				  }
				  wordLength= 5;
				  out.write(charHolder);
				  out.write(" ");}
	  		}
	  	else 
		  for(int i = 0; i< numberOfWords; i++){
			  charHolder = new char[wordLength];
			  Random x = new Random();
				for(int y = 0; y < wordLength; y++){	  
					int temp = x.nextInt(100);
					if(temp >= 100-charFrequency)
						charHolder[y] = (char)97;
					else{
						if(count > 122)
							count = 97;
						charHolder[y] = (char)count;
						count++;
					}
				}
		  wordLength= 5;
		  out.write(charHolder);
		  out.write(" ");
		  
	  }
  out.close();
  }catch (Exception e){
  System.err.println("Error: " + e.getMessage());
  }
}}