package assignment10;

/**
 * Hash Function for an inputed String.
 * @author Jana Klopsch (u0854469)
 * Last Modified 11-6-16
 *
 */
public class MediocreHashFunctor implements HashFunctor{
	
	/**
	 * Returns an integer hash value for an inputed String.  This particular function
	 * returns the value of the first character of the String plus the length of the String.
	 */
	public int hash(String item){
		int value;
		
		char first = item.charAt(0);
		Character letter = (Character) first;
		
		value = letter.charValue();
		value += item.length();
		
		return value;
	}
	
}
