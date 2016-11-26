package assignment10;

/**
 * @author Eduardo Ortiz
 *u092628
 */
public class MediocreHashFunctor implements HashFunctor { 
	@Override
	public int hash(String item) {
		int hashVal = 0;
		int prime = 13;
		char[] word = item.toCharArray();
		if (word.length <= 2)
		{
			hashVal = word[0] * prime;
		}
		else if (word.length == 3)
		{
			hashVal = word[1] * prime;
		}
		else
		{
			hashVal = word[3] * prime;
		}
		return hashVal;
	}
}
