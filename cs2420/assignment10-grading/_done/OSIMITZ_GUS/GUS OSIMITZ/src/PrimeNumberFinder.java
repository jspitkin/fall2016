package assignment10;

/**
 * Created by hoytick on 11/4/16.
 */
public class PrimeNumberFinder {
    public static int findPrime(int number) {
        if(number < 3) {
            return 3;
        }
        while(!isPrime(number)) {
            number++;
        }
        return number;
    }
    private static boolean isPrime(int number) {
        for(int possibleFactor = 2; possibleFactor < number; possibleFactor++) {
            if(number %  possibleFactor == 0) {
                return false;
            }
        }
        return true;
    }
}
