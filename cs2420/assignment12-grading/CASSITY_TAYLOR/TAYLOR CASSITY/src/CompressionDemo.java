package assignment12;

import java.io.File;
//demonstrates how to use the HuffmanTree class to compress a file and to decompress a file.
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
    compressFile("5k.txt", "compressed.txt");
    
    decompressFile("compressed.txt", "decompressed.txt");
  }
}