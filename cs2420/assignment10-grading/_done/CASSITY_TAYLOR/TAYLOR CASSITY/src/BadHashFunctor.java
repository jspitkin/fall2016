package assignment10;
/**
 * 
 * @author Taylor Cassity
 *
 */
public class BadHashFunctor implements HashFunctor {

	public int hash(String item) { 
        return  Character.getNumericValue(item.charAt(0));
      } 
}
