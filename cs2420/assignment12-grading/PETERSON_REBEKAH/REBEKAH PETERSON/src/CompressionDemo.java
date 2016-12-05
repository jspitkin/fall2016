package assignment12;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author Rebekah Peterson u0871657
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
	  String filename = ".txt";
	  for (int place = 0; place < 26; place++) {
		  char letter = (char) place;
		  filename = letter + filename;
		  try(FileOutputStream out = new FileOutputStream(filename)) {
			  for (int i = 0; i < place; i++)  {
				  out.write((char) i);
			  }
		  }
		  catch (IOException e) {
			  System.err.println(e);
		  }
		  String outfileName = "compressed.txt";
		  compressFile(filename, outfileName);
		  
		  File compressedFile = new File(outfileName);
		  System.out.println(compressedFile.length());
	  }
  }
}