package assignment10;

public class GoodHashFunctor implements HashFunctor
{
	@Override
	public int hash(String item) 
	{
		int charTotal = 0;

		for(int i = 0; i < item.length(); i++)
		{
			charTotal = charTotal + item.charAt(i);
		}

		return charTotal;
	}
}
