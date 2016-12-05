package assignment10;

public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		char[] array = item.toCharArray();
		int toReturn = 0;
		for(int i = 0; i < array.length; i++){
			toReturn += array[i] * 2;
		}
		return toReturn;
	}
	
	
	
}
