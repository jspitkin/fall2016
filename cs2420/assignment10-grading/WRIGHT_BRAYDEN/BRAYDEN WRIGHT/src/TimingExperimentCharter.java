package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author Brayden Wright
 */
public class TimingExperimentCharter {
    private static final int TIMES_TO_ITERATE = 1_000;
    private static final String METHOD = "hasherquadprobe";
    //private static final String X_AXIS = "Array Size";
    private static final String DATA_FILE = METHOD + "_data";
    //private static final String CHART_FILE = METHOD + "_plot.png";

    public static void main(String[] args) {

        // Apparently it's good to let the VM spin up first
        long startSpin = System.nanoTime();
        while (System.nanoTime() - startSpin < 5_000_000_000L) ;

        try (FileWriter writer = new FileWriter(new File(DATA_FILE))) {

            // A loop to increase the size of the set
            for (int sizeCount = 2; sizeCount <= 16; sizeCount++) {

                // Total average time an operation took
                long totalTime = 0;

                // Size of set to test on
                int setSize = (int) Math.pow(2, sizeCount);

                // Track collisions
//                int collisions = 0;

                // Do the test repeatedly
                for (int testNum = 0; testNum < TIMES_TO_ITERATE; testNum++) {

                    // Create a set
                    LinkedList<String> stringList = new LinkedList<>();
                    int count = 0;
                    while (count < setSize) {
                        int maxLength = new Random().nextInt(13);
                        char[] wordChars = new char[maxLength];

                        for (int wordLength = 0; wordLength < maxLength; wordLength++) {
                            char chr = (char) (97 + (new Random(count + wordLength).nextInt(26)));
                            wordChars[wordLength] = chr;
                        }

                        String temp = new String(wordChars);
                        stringList.add(temp);
                        count++;
                    }

                    // Create a hash table
                    QuadProbeHashTable table =
                            new QuadProbeHashTable(setSize / 2, new GoodHashFunctor());
//                    ChainingHashTable table =
//                            new ChainingHashTable(setSize / 2, new GoodHashFunctor());

                    // Start timing
                    long timeStart = System.nanoTime();

                    // Add every item to the table
                    table.addAll(stringList);

                    // Loop through hashing method
                    /*for (String str1 : stringList) {
                        int hash1 = new GoodHashFunctor().hash(str1);
                        for (String str2 : stringList) {
                            if (str1.equals(str2)) continue;
                            int hash2 = new GoodHashFunctor().hash(str2);
                            if (hash1 == hash2) {
                                collisions++;
                            }
                        }
                    }*/

                    // Stop timing
                    long timeStop = System.nanoTime();

                    // Record run time
                    totalTime += (timeStop - timeStart) / stringList.size();
                }

                double averageTime = (totalTime / TIMES_TO_ITERATE);
//                collisions = (collisions / TIMES_TO_ITERATE);
                System.out.println(setSize + "\t" + averageTime);
//                System.out.println("Collisions: " + collisions);
                writer.write(setSize + "\t" + averageTime/* + "\t" + collisions*/ + "\n");
            }

        } catch (Exception e) {
            System.err.println("Error occurred while writing to '" + DATA_FILE);
            e.printStackTrace();
        }

//        new Charter().createChart(DATA_FILE, CHART_FILE, METHOD, X_AXIS, METHOD);
    }
}
