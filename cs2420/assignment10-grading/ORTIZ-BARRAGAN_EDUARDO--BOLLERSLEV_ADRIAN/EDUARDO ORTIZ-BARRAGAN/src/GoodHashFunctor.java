package assignment10;

/**
 * @author Eduardo Ortiz
 * u0922628
 *
 */
public class GoodHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
			int hashVal = 0;
			char[] wordChars = item.toCharArray();
			for(char c: wordChars)
			{
				hashVal = hashVal + c;
			}
			return hashVal;
		}

	}


