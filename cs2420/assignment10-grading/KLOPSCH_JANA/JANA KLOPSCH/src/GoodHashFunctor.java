package assignment10;

/**
 * Hash Function for an inputed String.
 * @author Jana Klopsch (u0854469)
 * Last Modified 11-6-16
 *
 */
public class GoodHashFunctor implements HashFunctor{
	
	/**
	 * Returns an integer hash value for an inputed String.  This particular function
	 * returns the sum of all the characters in the String, times the length of the String.
	 */
	public int hash(String item){
		int value = 0;
		
		//sum all values of characters in item
		char letter;
		Character current;
		for(int index = 0; index < item.length(); index++){
			letter = item.charAt(index);
			current = (Character) letter;
			value += current.charValue();
		}
		//multiply this value by the length of item
		value *= item.length();
		
		return value;
	}

}
