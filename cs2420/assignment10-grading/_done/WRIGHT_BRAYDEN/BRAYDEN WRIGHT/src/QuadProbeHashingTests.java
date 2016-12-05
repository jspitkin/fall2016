package assignment10;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Brayden Wright (u0895942)
 */
public class QuadProbeHashingTests {
    private int stringSetSize = 1_000;
    private LinkedList<String> stringList;

    @Before
    public void createLists() {
        stringList = new LinkedList<>();

        // Populate stringList with stringSetSize strings
        int count = 0;
        while (count < stringSetSize) {
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
    }

    //@Test
    public void BadHashTest() {
        int collisions = 0;
        for (String str1 : stringList) {
            int hash1 = new BadHashFunctor().hash(str1);
            for (String str2 : stringList) {
                if (str1.equals(str2)) continue;
                int hash2 = new BadHashFunctor().hash(str2);
                if (hash1 == hash2) {
                    collisions++;
                }
            }
        }
        assertTrue(collisions >= stringSetSize);
    }

    //@Test
    public void MediocreHashTest() {
        int collisions = 0;
        for (String str1 : stringList) {
            int hash1 = new MediocreHashFunctor().hash(str1);
            for (String str2 : stringList) {
                if (str1.equals(str2)) continue;
                int hash2 = new MediocreHashFunctor().hash(str2);
                if (hash1 == hash2) {
                    collisions++;
                }
            }
        }
        assertTrue(collisions >= stringSetSize / 5);
    }

    //@Test
    public void GoodHashTest() {
        int collisions = 0;
        for (String str1 : stringList) {
            int hash1 = new GoodHashFunctor().hash(str1);
            for (String str2 : stringList) {
                if (str1.equals(str2)) continue;
                int hash2 = new GoodHashFunctor().hash(str2);
                if (hash1 == hash2) {
                    collisions++;
                }
            }
        }
        assertTrue(collisions <= stringSetSize / 100);
    }

    @Test
    public void QuadProbeAddTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());

        // Item is inserted, should return true
        assertTrue(table.add("sample"));

        // Item not inserted because it's already in the table, should return false
        assertFalse(table.add("sample"));
    }

    @Test
    public void QuadProbeAddAllTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());

        assertTrue(table.addAll(stringList));
        assertFalse(table.addAll(stringList));

        stringList.add("some string");
        assertTrue(table.addAll(stringList));
    }

    @Test
    public void QuadProbeAddAllOneNewItemTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());

        table.addAll(stringList);
        stringList.add("some string");

        // Only one new item in stringList, should still return true
        assertTrue(table.addAll(stringList));
        assertFalse(table.addAll(stringList));
    }

    @Test
    public void QuadProbeContainsTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());
        String str = "Hello QuadProbeHashTable!";
        table.add(str);

        assertTrue(table.contains(str));
        assertFalse(table.contains("Hello ChainingHashTable!"));
    }

    @Test
    public void QuadProbeContainsAllTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());

        // Test if the table contains all elements from stringList
        table.addAll(stringList);
        assertTrue(table.containsAll(stringList));
    }

    @Test
    public void QuadProbeContainsAllOneNewItemTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());
        table.addAll(stringList);

        // Add another string to table, table still contains all from stringList
        table.add("another string");
        assertTrue(table.containsAll(stringList));

        // Add another string to stringList, now table doesn't contain all from stringList
        stringList.add("some other string");
        assertFalse(table.containsAll(stringList));
    }

    @Test
    public void QuadProbeSizeTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());
        table.add("I");
        table.add("needed");
        table.add("to");
        table.add("add");
        table.add("a");
        table.add("few");
        table.add("strings");
        table.add("to");
        table.add("the");
        table.add("table.");

        // "to" was only added once, so size should be 9
        assertEquals(9, table.size());
    }

    @Test
    public void QuadProbeIsEmptyTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());
        assertTrue(table.isEmpty());

        table.add("fnord");
        assertFalse(table.isEmpty());
    }

    @Test
    public void QuadProbeClearTest() {
        QuadProbeHashTable table = new QuadProbeHashTable(3, new GoodHashFunctor());
        table.addAll(stringList);
        assertFalse(table.isEmpty());

        table.clear();
        assertTrue(table.isEmpty());
    }

}
