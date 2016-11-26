package assignment10;

public class MediocreHashFunctor implements HashFunctor {
	@Override
	public int hash(String item) {
		int hashVal = 0;
		int primeNumber = 23;
		char[] word = item.toCharArray();
		if (word.length <= 2) {
			hashVal = word[0] * primeNumber;
		} else if (word.length == 3) {
			hashVal = word[1] * primeNumber;
		} else {
			hashVal = word[3] * primeNumber;
		}
		return hashVal;
	}
}
