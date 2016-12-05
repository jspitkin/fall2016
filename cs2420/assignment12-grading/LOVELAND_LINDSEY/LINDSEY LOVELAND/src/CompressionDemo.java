package assignment12;

import java.io.File;
import assignment12.HuffmanTree;

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
    compressFile("repetitive.txt", "repCompressed.txt");
    
    decompressFile("repCompressed.txt", "repDecompressed.txt");
  }
}