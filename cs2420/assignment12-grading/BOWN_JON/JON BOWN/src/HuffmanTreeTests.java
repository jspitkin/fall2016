package assignment12;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Jonathan Bown U0696785
 */

public class HuffmanTreeTests
{

    //Tests compression/decompression on small, medium, and large files
    
    @Test
    public void testWriteSmallFile(){
        char[] arrayOfChars = new char[12];
        
       
        arrayOfChars[0] = 'a';
        arrayOfChars[1] = 'b';
        arrayOfChars[2] = 'c';
        arrayOfChars[3] = 'd';
        arrayOfChars[4] = 'e';
        arrayOfChars[5] = 'f';
        arrayOfChars[6] = 'a';
        arrayOfChars[7] = 'b';
        arrayOfChars[8] = 'c';
        arrayOfChars[9] = 'd';
        arrayOfChars[10] = 'e';
        arrayOfChars[11] = 'f';
               
        
        writeFile(arrayOfChars, "smallFile.txt");
        compressFile("smallFile.txt", "compressed.txt");
        decompressFile("compressed.txt", "decompressed.txt");
        String[] finalVersion = readFile("decompressed.txt");
        
        assertEquals("abcdefabcdef", finalVersion[0]);
        
    }
    
    @Test
    public void testOnLargeFile(){

        compressFile("dictionary.txt", "compressed.txt");
        decompressFile("compressed.txt", "decompressed.txt");
        String[] finalVersion = readFile("decompressed.txt");
        String[] original = readFile("dictionary.txt");
        assertTrue(finalVersion.length == original.length);
        for(int i = 0; i < original.length; i++){
          assertTrue(original[i].equals(finalVersion[i]));
        }
    }
    
    
    @Test
    public void testWriteMediumFile(){
        String[] words = new String[15];
        
       
        words[0] = "this";
        words[1] = "is";
        words[2] = "a";
        words[3] = "test";
        words[4] = "for";
        words[5] = "a";
        words[6] = "medium";
        words[7] = "length";
        words[8] = "file";
        words[9] = "that";
        words[10] = "has";
        words[11] = "a lot";
        words[12] = "of";
        words[13] = "strings";
        words[14] = "void";
               
        
        writeFile(words, "smallFile.txt");
        compressFile("smallFile.txt", "compressed.txt");
        decompressFile("compressed.txt", "decompressed.txt");
        String[] finalVersion = readFile("decompressed.txt");
        
        assertEquals("this is a test for a medium length file that has a lot of strings void ", finalVersion[0]);
        
    }
    
    @Test
    public void testUncompressed(){
        compressFile("Original.txt", "compressed.txt");
        decompressFile("compressed.txt", "decompressed.txt");
        
        String[] original = readFile("Original.txt");
        String[] finalVersion = readFile("decompressed.txt");
        
        assertEquals("I heart data." , finalVersion[0]);
        
    }
    
    @Test
    public void testCompressedFiledNotEqualToOriginal(){
        compressFile("Original.txt", "compressed.txt");
        
        
        String[] original = readFile("Original.txt");
        String[] finalVersion = readFile("compressed.txt");
        
        assertNotEquals("I heart data." , finalVersion[0]);
        
    }
    
    
    
    public static void compressFile(String infile, String outfile) {
        HuffmanTree t = new HuffmanTree();

        t.compressFile(new File(infile), new File(outfile));
        
      }
      
      public static void decompressFile(String infile, String outfile) {
        HuffmanTree t = new HuffmanTree();

        t.decompressFile(new File(infile), new File(outfile));
      }
      
      /**
       * Writes a file based on an input array of chars
       * @param list - array of chars
       * @param outputFile - string name of desired filename
       */
    
    
    public static void writeFile(char[] list, String outputFile){
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

 

           
                for (int col = 0; col < list.length; col++)
                {
                    writer.print(list[col]);
                }
                writer.println();
            

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Writes a file based on an input array of strings
     * @param list - array of strings
     * @param outputFile - name of the desired output file
     */
    
    public static void writeFile(String[] list, String outputFile){
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

 

           
                for (int col = 0; col < list.length; col++)
                {
                    writer.print(list[col]);
                    writer.print(" ");
                }
                writer.println();
            

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Helper method to read a file name into an array of strings
     * 
     * @param filename
     * @return
     */

    public static String[] readFile (String filename)
    {
        ArrayList<String> results = new ArrayList<String>();
        try (BufferedReader input = new BufferedReader(new FileReader(filename)))
        {
            while (input.ready())
            {
                results.add(input.readLine());
            }
            input.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return results.toArray(new String[results.size()]);
    }

    
    
    
    
}
