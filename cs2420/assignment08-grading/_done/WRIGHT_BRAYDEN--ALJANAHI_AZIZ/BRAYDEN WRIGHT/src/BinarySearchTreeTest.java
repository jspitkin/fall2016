package assignment08;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Brayden Wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
@SuppressWarnings("unchecked")
public class BinarySearchTreeTest {
    private static final String INFILE = "sample.txt";
    private BinarySearchTree sampleTree, testTree;
    private List<String> wordList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        sampleTree = new BinarySearchTree();

        // The file to grab words from to populate 'sampleTree'
        File file = new File(INFILE);

        // Read the file
        try (Scanner fileInput = new Scanner(file)) {
            fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

            while (fileInput.hasNext()) {
                String s = fileInput.next();
                if (!s.equals("")) {
                    wordList.add(s.toLowerCase());
                }
            }
        }

        // Populate sampleTree
        sampleTree.addAll(wordList);
    }

    @Test
    public void TreeCreationTest() {
        testTree = new BinarySearchTree();
        assertEquals(0, testTree.size()); //new binary tree is empty
    }

    @Test(expected = NullPointerException.class)
    public void ContainsNullItemTest() {
        assertFalse(sampleTree.contains(null)); //checks if passed an null item
    }

    @Test(expected = NullPointerException.class)
    public void AddNullItemTest() {
        assertFalse(sampleTree.add(null)); //checks if passed a null item
    }

    @Test
    public void BasicAddTest() {
        testTree = new BinarySearchTree();
        boolean added = true;

        // Test multiple add calls
        for (int i = 0; i < 20; i++)
            added &= testTree.add(i);

        assertTrue(added);
        assertFalse(testTree.add(3)); // Test adding existing
    }

    @Test
    public void CorrectSizeTest() {
        // Expected size is size-3 because of duplicate words in sample.txt
        int expectedSize = wordList.size() - 3;
        assertEquals(expectedSize, sampleTree.size()); //checks if size works

        // Also test size after adding some items
        sampleTree.add("fnord");
        sampleTree.add("mirror");
        sampleTree.add("void");
        assertEquals(expectedSize + 3, sampleTree.size());
    }

    @Test
    public void AddAllTest() {
        int expectedSize = wordList.size() - 3;
        List<String> list = new ArrayList<>();
        list.add("gfx");
        list.add("nphysics");
        list.add("synth");
        sampleTree.addAll(list);

        assertEquals(expectedSize + 3, sampleTree.size());  // Check expected size
        assertTrue(sampleTree.containsAll(list));  // Check if actually within the tree
//        sampleTree.writeDot("AddAllTest.dot");
    }

    @Test
    public void TreeContainsTest() {
        assertTrue(sampleTree.contains("luck")); //check contains

        sampleTree.add("fnord");
        assertTrue(sampleTree.contains("fnord")); //contains after add
        assertFalse(sampleTree.contains("dslfhdsj"));// check if does not contain
    }

    @Test
    public void TreeContainsAllTest() {
        assertTrue(sampleTree.containsAll(wordList));

        wordList.add("fnord");
        assertFalse(sampleTree.containsAll(wordList));
    }

    @Test
    public void GetFirstItemTest() {
        assertEquals("and", sampleTree.first());
        sampleTree.add("a");
        assertEquals("a", sampleTree.first());
    }

    @Test
    public void GetLastItemTest() {
        assertEquals("which", sampleTree.last());
        sampleTree.add("zoo");
        assertEquals("zoo", sampleTree.last());
    }

    @Test
    public void IsEmptyTest() {
        testTree = new BinarySearchTree();
        assertTrue(testTree.isEmpty());
        assertTrue(testTree.size()==0); //checks size of set
    }

    @Test
    public void ClearTreeTest() {
        sampleTree.clear();
        assertEquals(0, sampleTree.size()); //checks size
        assertTrue(sampleTree.isEmpty()); //chcks if empty
//        sampleTree.writeDot("ClearTreeTest.dot");
    }

    @Test
    public void RemoveRootTest() {
        assertTrue(sampleTree.remove("good"));
        assertFalse(sampleTree.contains("good"));
//        sampleTree.writeDot("RemoveRootTest.dot");
    }

    @Test
    public void RemoveLeafTest() {
        assertTrue(sampleTree.remove("can"));
        assertFalse(sampleTree.contains("can"));
//        sampleTree.writeDot("RemoveLeafTest.dot");
    }

    @Test
    public void RemoveSingleChildTest() {
        assertTrue(sampleTree.remove("is"));
        assertFalse(sampleTree.contains("is"));
//        sampleTree.writeDot("RemoveSingleChildTest.dot");
    }

    @Test
    public void RemoveTwoChildrenTest() {
        assertTrue(sampleTree.remove("ar"));
        assertFalse(sampleTree.contains("ar"));
//        sampleTree.writeDot("RemoveTwoChildrenTest.dot");
    }

    @Test
    public void RemoveWithManyChildrenBranches() {
        assertTrue(sampleTree.remove("search"));
        assertFalse(sampleTree.contains("search"));
//        sampleTree.writeDot("RemoveWithManyChildrenBranches.dot");
    }

    @Test
    public void ToArrayListTest() {
        testTree = new BinarySearchTree();
        List list = new ArrayList();
        List expected = new ArrayList();

        for (int i = 0; i < 20; i++) {
            list.add(i);
            expected.add(i);
        }

        Collections.shuffle(list);
        testTree.addAll(list);

        assertEquals(expected, testTree.toArrayList());
//        testTree.writeDot("ToArrayListTest.dot");
    }

    @Test
    public void SpellCheckerTest() {
        SpellChecker sc = new SpellChecker(new File("dictionary.txt"));
        List misspelled = sc.spellCheck(new File(INFILE));

        assertTrue(misspelled.contains("deltions"));
        assertFalse(misspelled.contains("luck"));
    }

    /*
    // Convert all DOT files to GIFs for easy visual analysis after all tests complete.
    // Assumes Graphviz is installed, will fail otherwise.
    @AfterClass
    public static void ConvertDotFiles() {
        File[] files = new File(".").listFiles();
        File outdir = new File("dotgifs");  // Throw the GIFs into this directory

        // Create directory if needed
        if (!outdir.exists()) {
            if (!outdir.mkdir()) return;
        }

        // Convert DOT to GIFs using the 'dot' command
        for (File file : files) {
            String filename = file.getName();
            if (filename.endsWith(".dot")) {
                String name = filename.substring(0, filename.lastIndexOf('.'));

                try {
                    Runtime.getRuntime().exec("dot -T gif " + filename
                            + " -o " + outdir + File.separator + name + ".gif").waitFor();
                } catch (InterruptedException | IOException e) {
                    System.err.println("Unable to convert DOTs to GIFs.");
                    e.printStackTrace();
                }
            }
        }
    }
    */
}
