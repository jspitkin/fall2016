package assignment12;

import CodeTiming.CodeTimer;

import java.io.File;
import java.io.FileWriter;

/**
 * @author rahul ramkumar - u0833357.
 */
public class HuffmanTreeTiming {
    private static final int ITER_COUNT = 10;
    private static final int MIN_POW_2 = 3;
    private static final int MAX_POW_2 = 12;
    private static final int MIN_ASCII_CHAR = 32;
    private static final int MAX_ASCII_CHAR = 126;

    public static void main(String[] args) throws Exception {
        //testUniqueCharacters();
        testFrequency();
    }

    // 1000 character strings
    public static void testUniqueCharacters() throws Exception {
        for (int n = 0; n < MAX_ASCII_CHAR - MIN_ASCII_CHAR; n++) {
            CodeTimer<Double> times = new CodeTimer<>(n, "Unique", "string");
            for (int iter = 0; iter < ITER_COUNT; iter++) {
                String test = "";

                int c = MIN_ASCII_CHAR + n;
                while (test.length() < 1000) {
                    long ch = (System.nanoTime() % (c - MIN_ASCII_CHAR + 1)) + MIN_ASCII_CHAR;
                    //if (test.length() == 500) System.out.println("n: " + n + " ch: " + ch + " c: " + c);
                    test += (char) ch;
                }
                File testFile = new File("test.rahul12");
                File outFile = new File("testOut.rahul12");
                FileWriter testFileWriter = new FileWriter(testFile, false);
                testFileWriter.write(test);
                testFileWriter.close();
                HuffmanTree t = new HuffmanTree();
                t.compressFile(testFile, outFile);
                double ratio = (double) outFile.length() / (double) testFile.length();
                times.add(ratio);
            }
            times.writeToFile("files/assignment12/unique.csv");
        }

    }

    public static void testFrequency() throws Exception {
        for(int n = 1; n < Math.pow(2, MAX_POW_2); n*=2){
            CodeTimer<Double> times = new CodeTimer<>(n, "frequency", "string");
            for(int iter = 0; iter < ITER_COUNT; iter++){
                String test = "";
                for(int c = MIN_ASCII_CHAR; c <= MAX_ASCII_CHAR; c++){
                    test += (char)c;
                }
                String tmp = test;
                for(int i2 = 0; i2 < n; i2++){
                    test += tmp;
                }
                File testFile = new File("test.rahul12");
                File outFile = new File("testOut.rahul12");
                FileWriter testFileWriter = new FileWriter(testFile, false);
                testFileWriter.write(test);
                testFileWriter.close();
                HuffmanTree t = new HuffmanTree();
                t.compressFile(testFile, outFile);
                double ratio = (double) outFile.length() / (double) testFile.length();
                times.add(ratio);
            }
            times.writeToFile("files/assignment12/freq.csv");
        }
    }
}
