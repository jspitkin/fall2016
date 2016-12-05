package assignment10;

/**
 * @author Brayden Wright (u0895942)
 */
public class BadHashFunctor implements HashFunctor {

    @Override
    public int hash(String item) {
        return item.length();
    }
}
