package assignment10;

public class GoodHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int ret = 0;
		for(int i = 0; i < item.length(); i++) {
			ret += item.charAt(i)*(7^(item.length()-i+1));
		}
		return ret;
	}

}
