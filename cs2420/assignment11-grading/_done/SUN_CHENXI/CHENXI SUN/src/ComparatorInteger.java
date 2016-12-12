package assignment11;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import java.util.Comparator;

public class ComparatorInteger implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1.compareTo(o2)>0){
			return 1;
		}
		else if(o1.compareTo(o2)<0){
			return -1;
		}
		else{
		return 0;
		}
	}

}
