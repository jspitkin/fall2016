package assignment10;

public class BadHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		return 0;
	}

}
