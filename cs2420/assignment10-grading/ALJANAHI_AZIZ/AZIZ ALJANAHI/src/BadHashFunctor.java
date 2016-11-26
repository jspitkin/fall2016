package assignment10;
/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Bad Hash Functor
 */

public class BadHashFunctor implements HashFunctor {
  

  @Override
  public int hash(String item) {

    return item.length()/2; //Divides length by two
    
  }


}
