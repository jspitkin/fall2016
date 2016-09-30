package assignment04;

import java.util.Comparator;

/**
 * Implementation of the Comparator interface for Characters.
 * Compares them based on their ASCII ordering.
 * 
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class CharacterComparator implements Comparator<Character> {

	@Override
	public int compare(Character o1, Character o2) {
		return o1 - o2;
	}

}
