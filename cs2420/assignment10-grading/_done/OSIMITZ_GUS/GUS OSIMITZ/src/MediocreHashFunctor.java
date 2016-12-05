package assignment10;

/**
 * Class that uses a mediocre hash function to generate a hash for a string.
 * @author Thomas Osimitz U0970671
 */
public class MediocreHashFunctor implements HashFunctor {
    /**
     *
     * @param item - item who's hash is desired
     * @return - the hash of the item
     */
    @Override
    public int hash(String item) {
        char[] array = item.toCharArray();
        int stringSum = 0;
        for (char curr : array) {
            stringSum = stringSum + curr;
        }
        return item.length() + stringSum * stringSum;
    }
}
