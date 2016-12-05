package assignment10;

/**
 * A better hash functor then the bad hash functor, but not
 * as good as the good hash functor. In other words, a 
 * mediocre hash functor.
 * 
 * @author Spencer Jewett 
 * @since 11/11/2016
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	public int hash(String item) {
		int hash;
		if(item.equals("")){
			hash = 0;
		}
		
		if(item.length() >= 3){
			hash = (int) (7 * Math.pow((item.charAt(0) + item.charAt(1)), item.charAt(2))); 
		}
		else if(item.length() > 1){
			hash = 7 * item.charAt(0) + item.charAt(1);
		}
		else{
			hash = 7 * item.charAt(0);
		}
		
		return hash;
	}

}
