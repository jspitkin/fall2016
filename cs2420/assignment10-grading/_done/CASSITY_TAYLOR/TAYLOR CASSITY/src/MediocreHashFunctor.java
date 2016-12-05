package assignment10;
/**
 * 
 * @author Taylor Cassity
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	public int hash(String item) { 
		char[] temp = item.toCharArray();
		int c = 0;
		for(int i = 0; i < temp.length; i++)
			c += Character.getNumericValue(temp[i++]);
		c = (int)Math.pow(c+1, 2);
		return Integer.valueOf(c);
      } 
}
