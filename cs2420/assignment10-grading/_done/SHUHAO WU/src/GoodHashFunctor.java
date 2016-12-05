package assignment10;

public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int ascii = 0;
		for(int i =0; i<item.length();i++){
			char a = item.charAt(i);
			ascii+=(int)a;
		}
		return ascii;
	}

}
