package assignment10;

public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int hash = 7;
		for (int i = 0; i < item.length(); i++) {
		    hash = hash*31 + item.charAt(i);
		}
		if(hash < 0)
			hash = hash * -1;
		return hash;
	}
	
}
