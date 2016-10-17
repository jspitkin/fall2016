package timing;

import assignment06.DoublyLinkedList;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Brayden Wright
 */
public class TimingExperimentCharter {
    private static final int TIMES_TO_ITERATE = 10;
    private static final String METHOD = "ArrayList remove Runtime";
    private static final String X_AXIS = "Array Size";
    private static final String DATA_FILE = METHOD + "_data";
    private static final String CHART_FILE = METHOD + "_plot.png";

    public static void main(String[] args) {

        // Apparently it's good to let the VM spin up first
        long startSpin = System.nanoTime();
        while (System.nanoTime() - startSpin < 2_000_000_000);

        try (FileWriter writer = new FileWriter(new File(DATA_FILE))) {

            // A loop to increase the size of the set
            for (int sizeCount = 10; sizeCount <= 22; sizeCount++) {

                // Total average time an operation took
                long totalTime = 0;

                // Size of set to test on
                int setSize = (int) Math.pow(2, sizeCount);

                // Do the test repeatedly
                for (int testNum = 0; testNum < TIMES_TO_ITERATE; testNum++) {
                    int randInt = new Random(137).nextInt(setSize - 1);

                    // Create the set
//                    DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int count = 0; count < setSize; count++)
//                        list.addLast(count);
                         list.add(count);


                    // Start timing
                    long timeStart = System.nanoTime();

                    // Operation to test
//                    list.addFirst(testNum);
//                    list.add(0, testNum);
//                    list.get(setSize/2);
                    list.remove(setSize/2);

                    // Stop timing
                    long timeStop = System.nanoTime();

                    // Record run time
                    totalTime += (timeStop - timeStart);
                }

                double averageTime = (totalTime / TIMES_TO_ITERATE);
                System.out.println(setSize + " -- " + averageTime);
                writer.write(/*setSize + "\t" + */averageTime + "\n");
            }

        } catch (Exception e) {
            System.err.println("Error occurred while writing to '" + DATA_FILE);
            e.printStackTrace();
        }

//        new Charter().createChart(DATA_FILE, CHART_FILE, METHOD, X_AXIS, METHOD);
    }
}
