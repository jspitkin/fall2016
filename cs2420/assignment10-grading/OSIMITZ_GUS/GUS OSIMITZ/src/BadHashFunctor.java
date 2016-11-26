package assignment10;

/**
 * Class that uses a bad hash function to generate a hash for a string.
 * @author Thomas Osimitz U0970671
 */
public class BadHashFunctor implements HashFunctor {

    /**
     *
     * @param item - item who's hash is desired
     * @return - the hash (length of the string) of the item
     */
    @Override
    public int hash(String item) {
        return item.length();
    }
}
