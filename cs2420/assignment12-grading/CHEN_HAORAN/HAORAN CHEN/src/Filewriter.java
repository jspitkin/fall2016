package assignment12;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Filewriter to generate txt files with characters
 * @author Haoran Chen
 * @uid u1060286
 *
 */
public class Filewriter {

	public static void main(String[] args) {
		printFile("Ass12.txt");

	}

	private static void printFile(String outputFile){
		 try {PrintWriter output = new PrintWriter(new FileWriter(outputFile));
		 
		 for (int i = 97; i < 102; i++){
			  for(int j = 0; j < Math.pow(2, 20); j++){
				  output.print((char)i);
			  }
		 }
			  
			  
			  output.close();
		  }
		  catch(Exception e){
			  System.out.println("Error printing output File "+ e);
			}	 	
		}
}
