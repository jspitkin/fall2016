package assignment04;
import CodeTiming.CodeTimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Rahul Ramkumar and Lingxi Zhong.
 */
public class AnagramUtilTiming {
    private static final int ITER_COUNT = 10000;
    private static ArrayList<String> permutationsOfString = new ArrayList<>();

    public static void main(String[] args){
        timeAreAnagrams();
        //timeLargestAnagramGroup();
    }

    public static void timeAreAnagrams(){
        long totalStart = System.nanoTime();
        Random r = new Random();
        for(int n = 3; n < 50; n++) {
            CodeTimer areAnagramsTimer = new CodeTimer(n, "String", "areAnagrams");
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            long totalTime = 0;
            long start = 0; long end = 0;
            for (int i = 0; i < ITER_COUNT; i++) {
                String test1 = "";
                String test2 = "";
                for(int inner = 0; inner<n; inner++){
                    test1 += alphabet.charAt(r.nextInt(alphabet.length()));
                    test2 += alphabet.charAt(r.nextInt(alphabet.length()));
                }
                start = System.nanoTime();
                AnagramUtil.areAnagrams(test1, test2);
                end = System.nanoTime();
                totalTime += end-start;
            }
            areAnagramsTimer.add(totalTime/(long) ITER_COUNT);
            areAnagramsTimer.writeToFile("initial_test_single");
        }
        System.out.println("timeAreAnagrams(): " + ((double)(System.nanoTime() - totalStart)/(double)1000000000) + " seconds");

    }

    public static void timeLargestAnagramGroup(){
        long totalStart = System.nanoTime();
        for(int n = 2; n <= Math.pow(2, 10); n *= 2){
            CodeTimer timeLargestAnagramGroup = new CodeTimer(n, "OurSort", "getLargestAnagramGroup");
            long totalTime = 0; long start = 0; long end = 0;
            ArrayList<String> testStrings = (ArrayList<String>) CodeTimer.generateList(String.class, n);
            for(int i = 0; i < ITER_COUNT; i++) {
                Collections.shuffle(testStrings);
                String[] testArr = new String[0];
                testStrings.toArray(testArr);
                start = System.nanoTime();
                AnagramUtil.getLargestAnagramGroup(testArr);
                end = System.nanoTime();
                totalTime += end - start;
            }
            timeLargestAnagramGroup.add(totalTime/(long) ITER_COUNT);
            timeLargestAnagramGroup.writeToFile("initial_test_groupJava");
        }
        System.out.println("timeLargestAnagramGroup(): " + ((double)(System.nanoTime() - totalStart)/(double)1000000000) + " seconds");
    }

    public static void timeLargestAnagramGroupJavaSort(){

    }

    /**
     * Modified from code initially found on StackOverflow
     * http://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string
     * @param str
     */
    public static void permutation(String str) {
        permutation("", str);

    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if(n == 0) permutationsOfString.add(prefix);
        //if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n)); //Recursion!
        }
    }
    
    public static String[] concat(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c= new String[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
}
