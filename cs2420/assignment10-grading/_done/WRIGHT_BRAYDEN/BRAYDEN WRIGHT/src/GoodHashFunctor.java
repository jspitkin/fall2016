package assignment10;

import java.util.Random;

/**
 * @author Brayden Wright (u0895942)
 */
public class GoodHashFunctor implements HashFunctor {

    @Override
    public int hash(String item) {

        int hash = 13;
        for (char chr : item.toCharArray()){
            hash = (hash*101)+chr;
        }
        return Math.abs(hash);
    }
}
