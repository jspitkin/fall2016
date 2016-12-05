package assignment10;

import org.junit.*;
import java.util.ArrayList;

/**
 * Tests for the QuadProbeHashTable
 *
 * @author Thomas Osimitz U0970671
 */
public class QuadProbTests {
    BadHashFunctor badHash;
    QuadProbeHashTable fiveItemInitiallyQPHT, oneFourteenItems, emptyTable; //all using badHashes because we want to test for collision handling;
    String oneFourteenTextFile;
    ArrayList<String> oneFourteenItemsList;

    @Before
    public void setup() {
        oneFourteenTextFile = "onefourteenwords.txt";
        badHash = new BadHashFunctor();
        fiveItemInitiallyQPHT = new QuadProbeHashTable(5, badHash);
        emptyTable = new QuadProbeHashTable(55, badHash);
        oneFourteenItems = new QuadProbeHashTable(114, badHash);
        oneFourteenItemsList = new ArrayList<String>();
        oneFourteenItemsList.addAll(oneFourteenItems.readFile(oneFourteenTextFile));
        oneFourteenItems.addAll(oneFourteenItemsList);
    }

    @Test
    public void testResize() {
        oneFourteenItems.add("Art Garfunkel");
        oneFourteenItems.add("Johnny Cash");
        oneFourteenItems.add("Graceland");
        oneFourteenItems.add("Boy in the Bubble");
        oneFourteenItems.add("Johnny Jacob Jingleheimer Schmidt");
        oneFourteenItems.add("The Boxer");
        Assert.assertEquals(120, oneFourteenItems.size());
        Assert.assertEquals(461, oneFourteenItems.getCapacity());
    }

    @Test
    public void testAddBasic() {
        oneFourteenItems.add("Paul Simon");
        Assert.assertEquals(115, oneFourteenItems.size());
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
        Assert.assertFalse(fiveItemInitiallyQPHT.containsAll(oneFourteenItemsList));
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
