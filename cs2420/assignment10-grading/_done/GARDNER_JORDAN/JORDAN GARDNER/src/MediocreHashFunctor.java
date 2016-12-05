package assignment10;
/**
 * 
 * @author Jordan Gardner
 *
 */
public class MediocreHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int hash=0;
		for(int i=item.length()-1;i>item.length()-10;i--){
			hash=item.charAt(i);
		}
		return hash;
	}

}
