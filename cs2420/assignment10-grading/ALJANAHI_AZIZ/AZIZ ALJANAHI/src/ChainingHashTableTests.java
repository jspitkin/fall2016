package assignment10;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Chaining Hash Table tests
 */
public class ChainingHashTableTests {

  ChainingHashTable HTable;

  @Before
  public void setupBefore() {
    String[] arr = StringGenerator.readFile("3000sample.txt");

    Collection<String> strngList = new ArrayList<String>();
    for (int i = 0; i < arr.length; i++) {
      strngList.add(arr[i]);
    }
    HTable = new ChainingHashTable(10, new MediocreHashFunctor());
    HTable.addAll(strngList);
  }

  @Test
  public void testAdd() {
    ChainingHashTable tempTable = new ChainingHashTable(10, new MediocreHashFunctor());
    assertEquals(true, tempTable.add("XXX"));
    assertEquals(true, tempTable.add("xx"));
    assertEquals(false, tempTable.add("xx"));
    assertEquals(true, tempTable.contains("XXX"));
  }

  @Test
  public void testAddAllandContainsAll() {
    String[] arr = StringGenerator.readFile("3000sample.txt");
    Collection<String> strngList = new ArrayList<String>();
    for (int i = 0; i < arr.length; i++) {
      strngList.add(arr[i]);
    }
    ChainingHashTable tempTable = new ChainingHashTable(10, new MediocreHashFunctor());
    tempTable.addAll(strngList);
    assertEquals(true, tempTable.containsAll(strngList));
    tempTable.clear();
    assertEquals(false, tempTable.containsAll(strngList));
  }

  @Test
  public void testClear() {
    String[] arr = StringGenerator.readFile("3000sample.txt");
    Collection<String> tempTable = new ArrayList<String>();
    for (int i = 0; i < arr.length; i++) {
      tempTable.add(arr[i]);
    }
    HTable.addAll(tempTable);
    assertEquals(3000, HTable.size());
    HTable.add("a");
    assertEquals(3001, HTable.size());
  }

  @Test
  public void testSize() {
    String[] arr = StringGenerator.readFile("3000sample.txt");
    Collection<String> tempTable = new ArrayList<String>();
    for (int i = 0; i < arr.length; i++) {
      tempTable.add(arr[i]);
    }
    HTable.addAll(tempTable);
    assertEquals(3000, HTable.size());
    HTable.clear();
    assertEquals(0, HTable.size());
    assertEquals(0, HTable.getCollisions());
  }

  @Test
  public void testContains() {
    assertEquals(false, HTable.contains("PLPL"));
    assertEquals(true, HTable.contains("afterburners"));
    assertEquals(true, HTable.contains("abandonedly"));
    assertEquals(false, HTable.contains("find"));
  }

  @Test
  public void testisEmpty() {
    assertEquals(false, HTable.isEmpty());
    HTable.clear();
    assertEquals(true, HTable.isEmpty());
    HTable.add("p");
    assertEquals(false, HTable.isEmpty());
  }
}
