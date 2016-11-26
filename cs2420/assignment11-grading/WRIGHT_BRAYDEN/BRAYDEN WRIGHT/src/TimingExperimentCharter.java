package assignment11;

import java.io.File;
import java.io.FileWriter;

/**
 * @author Brayden Wright
 */
public class TimingExperimentCharter {
    private static final int TIMES_TO_ITERATE = 1_000;
    private static final String METHOD = "add";
    private static final String DATA_FILE = "src/assignment11/" + METHOD + "_data";

    public static void main(String[] args) {

        // Apparently it's good to let the VM spin up first
        long startSpin = System.nanoTime();
        while (System.nanoTime() - startSpin < 5_000_000_000L);

        try (FileWriter writer = new FileWriter(new File(DATA_FILE))) {

            // A loop to increase the size of the set
            for (int sizeCount = 8; sizeCount <= 20; sizeCount++) {

                // Total average time an operation took
                long totalTime = 0;

                // Size of set to test on
                int setSize = (int) Math.pow(2, sizeCount);

                // Do the test repeatedly
                for (int testNum = 0; testNum < TIMES_TO_ITERATE; testNum++) {

                    // Create the queue
                    PriorityQueue<Integer> queue = new PriorityQueue<>();
                    for (int count = 0; count < setSize; count++)
                        queue.add(count);

                    // Start timing
                    long timeStart = System.nanoTime();

                    // Operation to test
//                    queue.add(new Random().nextInt(setSize));
//                    queue.findMin();
                    queue.deleteMin();

                    // Stop timing
                    long timeStop = System.nanoTime();

                    // Record run time
                    totalTime += (timeStop - timeStart);
                }

                double averageTime = (totalTime / TIMES_TO_ITERATE);
                System.out.println(setSize + "\t" + averageTime);
                writer.write(/*setSize + "\t" + */averageTime + "\n");
            }

        } catch (Exception e) {
            System.err.println("Error occurred while writing to '" + DATA_FILE);
            e.printStackTrace();
        }

    }
}
