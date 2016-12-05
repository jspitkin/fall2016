package assignment10;

public class BadHashFunctor implements HashFunctor {


	@Override
	public int hash(String item) {
		char[] array = item.toCharArray();
		if(array[0] > 100){
			return 1;
		}
		return 0;
	
	}

}
