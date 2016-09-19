/**
@Author Adrian
*/
package assignment03;

import java.util.Comparator;

public class ReverseComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		if((o1==null)||(o2 == null)){
			new NullPointerException();
			}
		
		if(o1.compareTo(o2)>0){
			return o1.compareTo(o2)*-1;
		}
		else if(o1.compareTo(o2)<0){
			return Math.abs(o1.compareTo(o2));
		}
		else{
			return 0;
		}
	}
	
}

