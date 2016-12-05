package assignment12;

import java.io.File;

/**
 * Demonstration of Huffman Compression and decompression.
 * @author CS2420 instructors; edited by Dylan Johnson -- u1024233
 * 11/21/16
 */
public class CompressionDemo {
  
  public static void compressFile(String infile, String outfile) {
	  
    HuffmanTree tree = new HuffmanTree();
    
    tree.compressFile(new File(infile), new File(outfile));
    
    tree.huffmanToDot("compressed_files/huffman_tree.dot");
    
  }
  
  public static void decompressFile(String infile, String outfile) {
	  
    HuffmanTree tree = new HuffmanTree();

    tree.decompressFile(new File(infile), new File(outfile));
  }
  
  public static void main(String[] args) {
	  
    compressFile("compressed_files/original.txt", "compressed_files/compressed.txt");
    
    decompressFile("compressed_files/compressed.txt", "compressed_files/decompressed.txt");
  }
}

