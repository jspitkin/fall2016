package assignment10;

import org.junit.*;
import java.util.ArrayList;

/**
 * Tests for the Chaining Hash Table
 *
 * @author Thomas Osimitz U0970671
 */
public class ChainingHashTest {
    BadHashFunctor badHash;
    ChainingHashTable fiveItemInitiallyCHT, oneFourteenItems, emptyTable; //all using badHashes because we want to test for collision handling;
    String oneFourteenTextFile;
    ArrayList<String> oneFourteenItemsList;

    @Before
    public void setup() {
        oneFourteenTextFile = "onefourteenwords.txt";
        badHash = new BadHashFunctor();
        fiveItemInitiallyCHT = new ChainingHashTable(5, badHash);
        emptyTable = new ChainingHashTable(55, badHash);
        oneFourteenItems = new ChainingHashTable(114, badHash);
        oneFourteenItemsList = new ArrayList<String>();
        oneFourteenItemsList.addAll(oneFourteenItems.readFile(oneFourteenTextFile));
        oneFourteenItems.addAll(oneFourteenItemsList);
        System.out.println();
    }

    @Test
    public void testAddBasic() {
        oneFourteenItems.add("Paul Simon");
        Assert.assertTrue(oneFourteenItems.contains("Paul Simon"));
    }

    @Test
    public void testContainsAll() {
        Assert.assertTrue(oneFourteenItems.containsAll(oneFourteenItemsList));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        oneFourteenItems.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllNull() {
        oneFourteenItems.addAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNullNonEmptyTable() {
        oneFourteenItems.contains(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsNullEmptyHashTable() {
        emptyTable.contains(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllNullNonEmptyHashTable() {
        oneFourteenItems.containsAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllNullEmptyHashTable() {
        emptyTable.containsAll(null);
    }

    @Test
    public void testAddAllEmpty() {
        Assert.assertFalse(oneFourteenItems.addAll(new ArrayList<String>()));
    }

    @Test
    public void testAddAllAlreadyAddedd() {
        Assert.assertFalse(oneFourteenItems.addAll(oneFourteenItemsList));
    }

    @Test
    public void testContainsAllDoesntContain() {
        Assert.assertFalse(fiveItemInitiallyCHT.containsAll(oneFourteenItemsList));
    }

    @Test
    public void testClearOnEmptyTable() {
        emptyTable.clear();
        Assert.assertEquals(0, emptyTable.size());
    }

    @Test
    public void testClearOnBigTable() {
        Assert.assertTrue(oneFourteenItems.contains("macrosimulation")); //Included to ensure that it is properly filled first
        oneFourteenItems.clear();
        Assert.assertEquals(0, oneFourteenItems.size());
        Assert.assertFalse(oneFourteenItems.contains("macrosimulation"));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(emptyTable.isEmpty());
        Assert.assertFalse(oneFourteenItems.isEmpty());
    }

    @Test
    public void testAddEmptyString() {
        Assert.assertTrue(oneFourteenItems.add(""));
        Assert.assertTrue(oneFourteenItems.contains(""));
    }

}
