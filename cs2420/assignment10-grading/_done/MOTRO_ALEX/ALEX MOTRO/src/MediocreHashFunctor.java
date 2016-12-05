package assignment10;

public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int total = 0;
		for(char c : item.toCharArray())
			total+=c;
		return total;
	}

}
