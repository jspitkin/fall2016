package assignment10;

import java.math.BigInteger;
/**
 * 
 * @author Jordan Gardner
 * Grabs the next largest Prime value
 *
 */
public class Prime{
	public static int nextPrime(int i){
		BigInteger x=BigInteger.valueOf(i);
		x=x.nextProbablePrime();
		return Integer.parseInt(x.toString());
	}
}
