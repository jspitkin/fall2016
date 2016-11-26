package assignment10;

import java.util.Random;

/**
 * @author Brayden Wright (u0895942)
 */
public class MediocreHashFunctor implements HashFunctor {

    @Override
    public int hash(String item) {

//        int hash = (item.charAt(0) << item.charAt(item.length()-1) << item.charAt(item.length()/2));
//        return (hash > 0) ? hash : ~hash;
        return (item.length()*13) + (item.length() > 0 ? item.charAt(0) : 1);
    }
}
