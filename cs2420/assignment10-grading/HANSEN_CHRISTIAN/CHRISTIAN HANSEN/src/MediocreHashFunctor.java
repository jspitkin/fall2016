package assignment10;

/**
 * 
 * @author Christian Hansen, U0621884
 * 
 * This is a functor class which contains a method to hash an object. It is an 
 * example of a mediocre Hash function so it does not perform well. It returns a unique
 * integer by adding all the chars together but first multiplying each char by its index
 * location. This means that cats and tacs do not have the same number even though they have
 * the same characters. The hash still is not a good hash because the numbers while unique might be
 * closer together and it does not produce very large numbers.
 */
public class MediocreHashFunctor implements HashFunctor{
	
	/**
	 * This method returns a hash for the string given. It does so by multiplying the
	 * char by its index and adding it to the total, then returning the total.
	 * 
	 * @return hashnum - the hash number of the String
	 */
	@Override
	public int hash(String item) {
		
		int hashNum = 0;
		
		for(int index = 0; index < item.length(); index++){
			hashNum += item.charAt(index) * (index);
		}
		
		return hashNum;
	}

}
