package assignment10;
/**
 * 
 * @author Taylor Cassity
 *
 */
import assignment10.QuadProbeHashTable;

public class GoodHashFunctor implements HashFunctor {

	public int hash(String item) { 
		int c = 0;
		c += Character.getNumericValue(item.charAt(0));
		c += Character.getNumericValue(item.charAt(item.length()-1));
		c += (int)Math.pow(c+1, 2);
		
        return c; 
      } 
	
}
