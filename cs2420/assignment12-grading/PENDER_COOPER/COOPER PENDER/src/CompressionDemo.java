package assignment12;

import java.io.File;

/**
 * Cooper Pender (u0843147)
 * Used for question #1 on analysis.
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
    compressFile("182repeated.txt", "compressed.txt");
    
    decompressFile("compressed.txt", "decompressed.txt");
  }
}