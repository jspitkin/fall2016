package assignment10;

/**
 * Class that uses a good hash function to generate a hash for a string.
 * @author Thomas Osimitz U0970671
 */
public class GoodHashFunctor implements HashFunctor{
    /**
     *
     * @param item - item who's hash is desired
     * @return - the hash of the item
     */
    @Override
    public int hash(String item) {
        char[] array = item.toCharArray();
        int stringSum = 0;
        int salt = 24213;
        for (char curr : array) {
            stringSum = stringSum + curr;
        }
        stringSum = stringSum + salt;
        int hash = stringSum * stringSum + item.length() * (stringSum/2);
        return hash;
    }
}
