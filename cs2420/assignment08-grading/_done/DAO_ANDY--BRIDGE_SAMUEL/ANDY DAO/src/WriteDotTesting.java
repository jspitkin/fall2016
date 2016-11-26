package assignment08;

import java.util.ArrayList;

/**
 * This class is used to have controlled testing with the writeDot function.
 * 
 * @author Andy Dao, uID: u0692334
 * @author Sam Bridge, uID: u0984334
 */
public class WriteDotTesting {

    private static final int RIGHT_HEAVY_SET_SIZE = 15;

    // Declare some BST's and ArrayLists
    private static BinarySearchTree<Integer> rightHeavyBST;
    private static ArrayList<Integer> rightHeavyList;

    public static void main(String[] args) {
	printRightHeavyBST();
    }

    private static void printRightHeavyBST() {
	// Set up a right heavy BST
	rightHeavyList = new ArrayList<Integer>();
	for (int i = 0; i < RIGHT_HEAVY_SET_SIZE; i++) {
	    rightHeavyList.add(i);
	}
	// Add the array list to the BST
	rightHeavyBST = new BinarySearchTree<Integer>();
	rightHeavyBST.addAll(rightHeavyList);
	rightHeavyBST.writeDot("right_heavy_bst.dot");
    }

}
