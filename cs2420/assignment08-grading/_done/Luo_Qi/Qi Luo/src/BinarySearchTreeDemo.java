package assignment08;

/**
 * @author Qi Luo & Anthony Iovino 
 */
public class BinarySearchTreeDemo {

	public static void main(String[] args) {
		BinarySearchTree<Integer> intTree = new BinarySearchTree<Integer>();
//		add(intTree);
		intTree.add(6);
		intTree.add(4);
		intTree.add(3);
		intTree.add(10);
		intTree.add(7);
		intTree.add(12);
		intTree.add(11);
		intTree.writeDot("addtree.dot");
		intTree.remove(3);
		intTree.remove(6);
		intTree.writeDot("1.dot");
		intTree.add(8);
		intTree.writeDot("2.dot");
		intTree.add(9);
		intTree.writeDot("3.dot");
		intTree.add(6);
		intTree.writeDot("4.dot");
		intTree.remove(12);
		intTree.writeDot("5.dot");
		
	}

	public static void add(BinarySearchTree<Integer> tree){
		for(int i = 0 ; i<100 ; i++){
			tree.add(i);
		}
		tree.writeDot("integertree.dot");
	}
}
