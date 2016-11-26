package assignment11;

import java.util.Comparator;

/**
 * Node - is used for generic testing for PriorityQueue. 
 * @author Kyle Price
 * 11/14/2016
 */
class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node n1, Node n2) {
		return n1.value - n2.value;
	}
}

public class Node {
	public int value;

	public Node(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
