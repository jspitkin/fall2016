package assignment10;

public class GoodHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int total = 0;
		for(int i = 0; i < item.length(); i++)
			total+=((total*23)%100) + item.charAt(i);
		return total;
	}

}
