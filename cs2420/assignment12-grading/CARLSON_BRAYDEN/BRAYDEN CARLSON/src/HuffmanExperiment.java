package assignment12;

import java.io.File;

/**
 * Code used for analysis document experiment
 * @author Brayden Carlson
 *
 */
public class HuffmanExperiment {
	public static void compressFile(String infile, String outfile) {
	    HuffmanTree t = new HuffmanTree();

	    t.compressFile(new File(infile), new File(outfile));
	    
	  }
	  
	  public static void main(String[] args) {
	    compressFile("huffman_experiment/unique1.txt", "huffman_experiment/unique1output.txt");
	    compressFile("huffman_experiment/unique10.txt", "huffman_experiment/unique10output.txt");
	    compressFile("huffman_experiment/unique25.txt", "huffman_experiment/unique25output.txt");
	    compressFile("huffman_experiment/unique50.txt", "huffman_experiment/unique50output.txt");	    
	    
	    compressFile("huffman_experiment/freq1.txt", "huffman_experiment/freq1output.txt");
	    compressFile("huffman_experiment/freq10.txt", "huffman_experiment/freq10output.txt");
	    compressFile("huffman_experiment/freq25.txt", "huffman_experiment/freq25output.txt");
	    compressFile("huffman_experiment/freq50.txt", "huffman_experiment/freq50output.txt");
	  }
}
