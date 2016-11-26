package assignment10;

public class GoodHashFunctor implements HashFunctor {
	@Override
	public int hash(String item) {
		int hashFunc = 0;
		char[] word = item.toCharArray();
		for (char x : word) {
			hashFunc = hashFunc + x;
		}
		return hashFunc;
	}
}
