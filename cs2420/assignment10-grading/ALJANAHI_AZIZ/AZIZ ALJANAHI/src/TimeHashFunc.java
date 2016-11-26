package assignment10;

import java.util.ArrayList;
import java.util.Collection;
/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Timer Method
 */
@SuppressWarnings("unused")
public class TimeHashFunc {
  static float startTime, endTime, duration;
  static long Billion = 1_000_000_000;
  private static GoodHashFunctor GHF = new GoodHashFunctor();
  private static MediocreHashFunctor MHF = new MediocreHashFunctor();
  private static BadHashFunctor BHF = new BadHashFunctor();

  public static void main(String[] args) {
    // functorTimer();
    chainTimer();


  }

  public static void functorTimer() {
    long size = 30_000;
    QuadProbeHashTable quadProbeGHF;
    QuadProbeHashTable quadProbeMHF;
    QuadProbeHashTable quadProbeBHF;
    ArrayList<String> arr = new ArrayList<>();
    for (int i = 2500; i <= size; i += 2500) {
      // quadProbeGHF = new QuadProbeHashTable(100000, GHF);
      // quadProbeMHF = new QuadProbeHashTable(100000, MHF);
      quadProbeBHF = new QuadProbeHashTable(100000, BHF);

      for (int x = 0; x < i; x++)
        arr.add(StringGenerator.randomString(10));
      // Warm the System up
      while (System.nanoTime() - startTime < Billion) {
      }
      startTime = System.nanoTime();
      // quadProbeGHF.addAll(arrayOfStrings);
      // quadProbeMHF.addAll(arrayOfStrings);
      quadProbeBHF.addAll(arr);
      endTime = System.nanoTime();
      duration = (endTime - startTime);
      // System.out.println("Size: "+ i+" time:"
      // +(int)(duration/i)+" Collisions:"+quadProbeGHF.getCollisions());
      // System.out.println("Size: " + i + " time: " + (int) (duration / i) + "Collisions: "+
      // quadProbeMHF.getCollisions());
      System.out.println("Size: " + i + " time: " + (int) (duration / i) + " Collisions:"
          + quadProbeBHF.getCollisions());

    }

  }

  public static void chainTimer() {
    // System.out.println("Quad Timer");
    System.out.println("Chaining Timer");

    QuadProbeHashTable quadProbe;
    ChainingHashTable chainHash;
    ArrayList<String> arr = new ArrayList<>();
    long size = 30_000;
    for (int i = 2500; i <= size; i += 2500) {
      // quadProbe = new QuadProbeHashTable(100000, GHF);
      chainHash = new ChainingHashTable(100000, GHF);
      for (int x = 0; x < i; x++)
        arr.add(StringGenerator.randomString(8));
      // Warm the System up
      while (System.nanoTime() - startTime < Billion) {
      }
      startTime = System.nanoTime();
      // quadProbe.addAll(arr);
      chainHash.addAll(arr);
      endTime = System.nanoTime();
      duration = (endTime - startTime);
      // System.out.println("Size: " + i + " time: " + (int) (duration / i) + " Collisions:"
      // + quadProbe.getCollisions());
      System.out.println("Size: " + i + " time: " + (int) (duration / i) + " Collisions:"
          + chainHash.getCollisions());

    }
  }
}
