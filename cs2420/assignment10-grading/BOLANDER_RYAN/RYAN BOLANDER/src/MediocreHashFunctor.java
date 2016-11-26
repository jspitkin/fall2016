package assignment10;

public class MediocreHashFunctor implements HashFunctor 
{
	@Override
	public int hash(String item) 
	{
		int firstChar = 0;
		
		firstChar = item.charAt(0);
		
		return (firstChar + item.length());
	}
}
