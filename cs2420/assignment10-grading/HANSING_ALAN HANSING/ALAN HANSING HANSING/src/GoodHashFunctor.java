package assignment10;

public class GoodHashFunctor implements HashFunctor {


	@Override
	public int hash(String item) {
		char[] array = item.toCharArray();
		long toReturn = 7;
		for(int i = 0; i< array.length; i++){
			toReturn =toReturn *3 + array[i];
		}
		if((int)toReturn < 0){
			toReturn = Math.abs(toReturn);
		}
		
		return (int)toReturn;
	}

}
