package assignment04;

import java.util.Comparator;

public class CharacterComparator implements Comparator<Character> {

	@Override
	public int compare(Character o1, Character o2) {
		if(Character.isUpperCase(o1)||Character.isUpperCase(o2)){
			Character lowercase1=o1.toLowerCase(o1);
			Character lowercase2=o2.toLowerCase(o2);
			if(lowercase1.compareTo(lowercase2)<0){
				return -1;
			}
			else if((lowercase1.compareTo(lowercase2))>0){
				return 1;
			}
			return 0;
		}
		
		// TODO Auto-generated method stub
		else{
		
		if(o1.compareTo(o2)<0){
			return -1;
		}
		else if(o1.compareTo(o2)>0){
			return 1;
		}
		
		
		
		return 0;
	}
	}

}
