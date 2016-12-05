package assignment10;

public class primeNumberGen {

//	private static int getPrime(int pos) {
//		// int pos = index;
//		boolean primeNumber = false;
//
//		if (pos > 15485863) {
//			primeNumber = isPrime(pos);
//			while (!primeNumber) {
//				pos++;
//				primeNumber = isPrime(pos);
//			}
//			return pos;
//		}
//		if (pos <= 15485863) {
//			pos = getPrime(pos);
//		}
//		return pos;
//	}
//
//	private static boolean isPrime(int index) {
//		boolean primeInteger = true;
//		int bound = (int) Math.sqrt(index);
//		for (int i = 2; i < bound; i++) {
//			if (bound % i == 0) {
//				primeInteger = false;
//				break;
//			}
//		}
//		return primeInteger;
//	}

	public static int nextPrimeNumber(int size) {
		int max = 15485863;

		// loop through the numbers one by one starting from the previous size
		for (int i = size; i <= max; i++) {

			boolean isPrimeNumber = true;

			// check to see if the number is prime
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrimeNumber = false;
					break; // exit the inner for loop
				}
			}

			// return the number if prime and greater than our previous prime
			// number
			if (isPrimeNumber) {
				if (!(i <= size)) {
					size = i;
					return size;
				}
			}

		}
		return size;
	}
}
