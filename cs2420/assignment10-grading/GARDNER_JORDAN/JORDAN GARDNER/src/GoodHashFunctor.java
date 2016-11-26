
package assignment10;
/**
 * 
 * @author Jordan Gardner
 *
 */
public class GoodHashFunctor implements HashFunctor{

	public int hash(String item){
		int hash=0;
		char[] c = item.toCharArray();
		for(int i=0;i<item.length();i++){
			hash=hash+c[i];
		}
		return hash;
	}

}
