package assignment10;
/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Tests collisions
 */
import java.util.ArrayList;
import java.util.Collection;

public class CollisionTest {

  public static void main(String[] args) {

    String[] words = StringGenerator.readFile("3000sample.txt");
    Collection<String> list = new ArrayList<String>();
    for (int i = 0; i < words.length; i++) {
      list.add(words[i]);
    }
    QuadProbeHashTable hashTable = new QuadProbeHashTable(3000, new BadHashFunctor());
    hashTable.addAll(list);
    System.out.println("Bad Functor\t"+hashTable.getCollisions());
    hashTable.clear();
    hashTable = new QuadProbeHashTable(3000, new MediocreHashFunctor());
    hashTable.addAll(list);
    System.out.println("Mediocre Functor\t"+hashTable.getCollisions());
    hashTable.clear();
    hashTable = new QuadProbeHashTable(3000, new GoodHashFunctor());
    hashTable.addAll(list);
    System.out.println("Good Functor\t"+hashTable.getCollisions());

  }

}
