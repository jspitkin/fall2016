package assignment10;

public class MediocreHashFunctor implements HashFunctor{



	@Override
	/*
	 *Create one hashing function that you think is mediocre. 
	 *You should expect that it will result in fewer collisions than the bad one. 
	 *Put the hashing function in the class MediocreHashFunctor.
	 *
	 */
	public int hash(String item) {
		int a = 0;
		for(int i = 0; i < item.length();i++){
		a = a + item.charAt(i);
		}
		return a;
	}
}
