package assignment10;

public class MediocreHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		return item.length();
	}

}
