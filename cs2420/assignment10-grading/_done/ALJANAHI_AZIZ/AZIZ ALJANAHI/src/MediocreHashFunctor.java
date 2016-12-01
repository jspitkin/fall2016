package assignment10;

/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Mediocre Hash Functor
 */
public class MediocreHashFunctor implements HashFunctor {
  @Override
  public int hash(String item) {
    char[] tempArr = item.toLowerCase().toCharArray();
    if (tempArr.length % 2 == 0) { //if length is even
      if (tempArr[tempArr.length / 2] > 'm') //if mid letter is bigger than m
        return (tempArr.length * 11 + tempArr[tempArr.length / 2]);
      else
        return (tempArr.length * 47 + tempArr[tempArr.length / 2]);
      
    } else {//if length is odd
      if (tempArr[tempArr.length / 2] < 'm')//if mid letter is bigger than m
        return (tempArr.length * 2 + tempArr[tempArr.length / 2]);
      else
        return (tempArr.length * 27 + tempArr[tempArr.length / 2]);
    }

  }
}
