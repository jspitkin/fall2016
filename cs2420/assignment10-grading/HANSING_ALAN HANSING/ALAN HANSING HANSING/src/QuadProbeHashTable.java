package assignment10;

import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {

	private String[] array;
	private int size;
	private HashFunctor functor;
	public QuadProbeHashTable(int capacity, HashFunctor funct) {

		size = 0;
		functor = funct;
		
		if(checkPrime(capacity)){
			array = new String[capacity];
		}
		else {
			array = new String[newPrime(capacity)];
		}
	}

	@Override
	public boolean add(String item) {
		if (contains(item)) {
			return false;
		}
		
		long location;
		
		if(size + 1 >= array.length){
			
			String[] temporary = new String[newPrime(array.length * 2)];
			for (long i = 0; i < array.length - 1; i++) {
				if (array[(int) i] != null) {
					int tempHash = functor.hash(array[(int) i]);
					location = ((tempHash) + (i * i)) % array.length;
					while (location >= temporary.length) {
						location -= temporary.length;
					}

					if (temporary[(int)location] == null) {
						size++;
						temporary[(int)location] = array[(int) i];
					}
				}

				array = temporary;
			}
		}
		
		int hash = functor.hash(item);
		
		for(long i = 0; i < array.length -1; i++){
			location = ((hash) + (i * i)) % array.length;
			while(array.length < location){
				location -= array.length;
			}
			
			if(array[(int)location] == null){
				array[(int)location] = item;
				size++;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean toReturn = false;
		
		for(String toAdd: items){
			toReturn |= add(toAdd);
		}
		return toReturn;
	}

	@Override
	public void clear() {
		for(int i = 0; i < array.length - 1; i++){
			array[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean contains(String item) {
		int hash = functor.hash(item);
		long location;
		
		for(long i = 0; i < size; i++){
			location = ((hash) + (i*i)) % array.length;
			while(array.length < location){
				location -= array.length;
			}
			if(array[(int)location] == null){
				break;
			}
			else{
				if(array[(int)location].equals(item)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean toReturn = true;
		for(String toCheck : items){
			toReturn &= contains(toCheck);
		}
		return toReturn;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}
	
	public int newPrime(int number) {

		if (checkPrime(number)) {
			return number;
		}
		return newPrime(number + 1);
	}
	
	public boolean checkPrime(int number) {

		if (number >= 2) {
			for (int i = 2; i < number; i++) {
				if (number % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
