package assignment10;

/**
 * A good hash functor, very very similar to Java's hashCode method
 * 
 * @author Spencer Jewett (U0677872)
 * @since 11/10/2016
 */
public class GoodHashFunctor implements HashFunctor {


	public int hash(String item) {
		int hash = 5381;
		
		if(item.length() == 0){
			return hash;
		}
			for(int i = 0; i < item.length(); i++){
				hash = ((hash << 5) - hash) + item.charAt(i);
				hash = hash & hash;
			}
		
		return Math.abs(hash);
	}

}
