package assignment10;

/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Good Hash Functor
 */
public class GoodHashFunctor implements HashFunctor {
  @Override
  public int hash(String item) {

    String temp1 = "";
    String temp2 = "";
    int n=0;
    for (int i = 0; i < item.length() / 2; i++) //takes the first half of the word makes it upper case
      temp1 += item.charAt(i);
    temp1 = temp1.toUpperCase();
    for (int i = 0; i < item.length() / 2; i++){
      char c =temp1.charAt(i);
      n+= 37*c*.5; //creates the first half of the code
    }
    for (int i = item.length() / 2; i < item.length(); i++)//takes the first half of the word makes it lower case
      temp2 += item.charAt(i);
    temp2 = temp2.toLowerCase();
    for (int i = 0; i < temp2.length(); i++){
      char c =temp2.charAt(i);
      n+= 13*c*.5; //creates the second half of the code
    }
    
     return n;
  }
}
