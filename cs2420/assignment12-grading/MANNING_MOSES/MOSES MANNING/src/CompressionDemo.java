package assignment12;

import java.io.File;

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
		compressFile("original.txt", "compressed.txt");
		decompressFile("compressed.txt", "decompressed.txt");

		compressFile("wordsEn.txt", "wordsEncompressed.txt");
		decompressFile("wordsEncompressed.txt", "wordsEndecompressed.txt");
		
		compressFile("Mushroom_Publishing.txt", "Mushroom_Publishingcompressed.txt");
		decompressFile("Mushroom_Publishingcompressed.txt", "Mushroom_Publishingdecompressed.txt");
		
		compressFile("barlycor.txt", "barlycorcompressed.txt");
		decompressFile("barlycorcompressed.txt", "barlycordecompressed.txt");

		compressFile("amer_pie.txt", "amer_piecompressed.txt");
		decompressFile("amer_piecompressed.txt", "amer_piedecompressed.txt");
		
		compressFile("100worst.txt", "100worstcompressed.txt");
		decompressFile("100worstcompressed.txt", "100worstdecompressed.txt");
		
		compressFile("songs-x5.txt", "songs-x5compressed.txt");
		decompressFile("songs-x5compressed.txt", "songs-x5decompressed.txt");
		
		compressFile("midi_dic.txt", "midi_diccompressed.txt");
		decompressFile("midi_diccompressed.txt", "midi_dicdecompressed.txt");
		
		compressFile("bullshit.txt", "bullshitcompressed.txt");
		decompressFile("bullshitcompressed.txt", "bullshitdecompressed.txt");
	}
}