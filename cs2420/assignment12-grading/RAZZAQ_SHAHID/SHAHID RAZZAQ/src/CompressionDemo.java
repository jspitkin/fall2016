package assignment12;

import java.io.File;
/**
 * 
 * @author Not Shahid Bilal Razzaq
 * u0996062
 *
 */
public class CompressionDemo {
  
  public static void compressFile(String infile, String outfile) {
    HuffmanTree t = new HuffmanTree();

    t.compressFile(new File(infile), new File(outfile));
    
    t.huffmanToDot("1_huffman_tree.dot");
  }
  
  public static void decompressFile(String infile, String outfile) {
    HuffmanTree t = new HuffmanTree();

    t.decompressFile(new File(infile), new File(outfile));
  }
  
  public static void main(String[] args) {
    compressFile("1_original.txt", "1_compressed.txt");
    
    decompressFile("1_compressed.txt", "1_decompressed.txt");
  }
}