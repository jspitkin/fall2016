package assignment04;

import java.util.Comparator;

public class AnagramUtilGrader
{
	public static boolean SortHelper(String test, String expected)
	{
		try
		{
			return AnagramUtil.sort(test).equals(expected);
		}
		catch(Exception e)
		{
			System.out.println("exception in sort: "+e.toString());
			return false;
		}
	}

	public static int testSort()
	{
		int total = 0;

		//Test trivial case
		if(!SortHelper("abcd","abcd"))
			System.out.println("Failed on sorting abcd. -2 points.");
		else
			total+=2;

		//Test null object
		//if(!(AnagramUtil.sort(null) == null))
		//	System.out.println("Failed on sorting null object (should return null). -1 point.");
		//else
		//	total++;

		//Test empty string
		if(!SortHelper("",""))
			System.out.println("Failed on sorting empty string. -1 point.");
		else
			total+=2;

		//Test small permuted string
		if(!SortHelper("dcba","abcd"))
			System.out.println("Failed on sorting dcba. -2 points.");
		else
			total+=2;

		//Test single multiple letter
		if(!SortHelper("aaaa","aaaa"))
			System.out.println("Failed on sorting aaaa. -1 point.");
		else
			total++;

		//Test permuted word containing duplicates
		if(!SortHelper("dcdbcaab","aabbccdd"))
			System.out.println("Failed on sorting dcdbcaab. -2 points.");
		else
			total+=2;

		//Test combination of multiple letters
		if(!SortHelper("antidisestablishmentarianism","aaaabdeehiiiiilmmnnnrssssttt"))
			System.out.println("Failed on sorting antidisestablishmentarianism. -1 point.");
		else
			total++;

		return total;
	}

	public static class ReverseIntComparator implements Comparator<Integer>
	{
		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1.intValue() > o2.intValue())
				return -1;
			else if(o1.intValue() == o2.intValue())
				return 0;
			else
				return 1;
		}

	}

	public static boolean InsertionSortHelper(Integer inputArray[], Integer expectedOutput[])
	{
		boolean nullFlag = false;

		ReverseIntComparator c = new ReverseIntComparator();

		if(inputArray == null)
			nullFlag = true;

		try
		{
			AnagramUtil.insertionSort(inputArray,c);
		}
		catch(Exception e)
		{
			System.out.println("exception in insertionSort: " + e.toString());
			return false;
		}

		if(nullFlag)
			return (inputArray == null);


		for(int i = 0; i < inputArray.length; i++)
			if(expectedOutput[i].intValue() != inputArray[i].intValue())
				return false;

		return true;
	}

	public static int testInsertionSort()
	{
		int total = 0;

		//Test trivial case
		if(!InsertionSortHelper(new Integer[]{5,4,3,2,1,0}, new Integer[]{5,4,3,2,1,0}))
			System.out.println("Failed on sorting {5,4,3,2,1,0}. -2 points.");
		else
			total+=2;

		//Test null object
//		if(!InsertionSortHelper(null, null))
//			System.out.println("Failed on handling null object. -1 point.");
//		else
//			total++;

		//Test empty string
		if(!InsertionSortHelper(new Integer[0], new Integer[0]))
			System.out.println("Failed on sorting empty array. -1 point.");
		else
			total++;

		//Test small permuted string
		if(!InsertionSortHelper(new Integer[]{2,4,0,5,1,3}, new Integer[]{5,4,3,2,1,0}))
			System.out.println("Failed on sorting {2,4,0,5,1,3}.  -2 point.");
		else
			total+=2;

		//Test single multiple letter
		if(!InsertionSortHelper(new Integer[]{2,2,2,2,2,2}, new Integer[]{2,2,2,2,2,2}))
			System.out.println("Failed on sorting {2,2,2,2,2,2}. -1 point.");
		else
			total++;

		//Test permuted word containing duplicates
		if(!InsertionSortHelper(new Integer[]{2,4,0,5,1,3,3,1,0,5,4,2}, new Integer[]{5,5,4,4,3,3,2,2,1,1,0,0}))
			System.out.println("Failed on sorting {2,4,0,5,1,3,3,1,0,5,4,2}. -2 point.");
		else
			total+=2;

		//Test inverted order
		if(!InsertionSortHelper(new Integer[]{0,1,2,3,4,5}, new Integer[]{5,4,3,2,1,0}))
				System.out.println("Failed on sorting {0,1,2,3,4,5} (Inverted order).  -2 point.");
			else
				total+=2;

		return total;
	}

	public static boolean areAnagramsHelper(String testA, String testB, boolean expected)
	{
		try
		{
			return (AnagramUtil.areAnagrams(testA,testB) == expected);
		}
		catch(Exception e)
		{
			System.out.println("exception in sort: "+e.toString());
			return false;
		}
	}

	public static int testAreAnagrams()
	{
		int total = 0;

		//null 1st parameter
//		if(!areAnagramsHelper(null, "abc", false))
//			System.out.println("Failed with first as null parameter.");
//		else
//			total++;

		//null 2nd parameter
//		if(!areAnagramsHelper("abc", null, false))
//			System.out.println("Failed with second as null parameter.");
//		else
//			total++;

		//One empty strings
		if(areAnagramsHelper("", "a", true))
			System.out.println("Failed in reporting \"\" is not an anagram to \"a\".");
		else
			total++;

		//Two empty strings
		if(!areAnagramsHelper("", "", true))
			System.out.println("Failed in reporting \"\" is an anagram to \"\".");
		else
			total++;

		//Simple test case	(+)
		if(!areAnagramsHelper("abc", "cab", true))
			System.out.println("Failed in reporting \"abc\" is an anagram to \"cab\".");
		else
			total++;

		//Simple test case 	(-)
		if(!areAnagramsHelper("abb", "cab", false))
			System.out.println("Failed in reporting \"abb\" is not an anagram to \"cab\".");
		else
			total++;

		// (+) Test case with multiple instances of a single letter
		if(!areAnagramsHelper("baraa", "araba", true))
			System.out.println("Failed in reporting \"baraa\" is an anagram to \"araba\".");
		else
			total++;

		// (-) Test case with multiple instances of a single letter
		if(!areAnagramsHelper("dda", "ddad", false))
			System.out.println("Failed in reporting \"dda\" are not anagrams to \"ddad\".");
		else
			total++;

		// (+) Test case with case sensitivity & multiple instances of single letters
		if(!areAnagramsHelper("Iwonthearthis", "withinearshot", true))
			System.out.println("Failed in reporting \"Iwonthearthis\" is an anagrams to \"withinearshot\".");
		else
			total++;

		// (+) Test case with case sensitivity & single instances of single letters
		if(!areAnagramsHelper("AbCdEf", "FeDcBa", true))
			System.out.println("Failed in reporting \"AbCdEf\" is an anagrams to \"FeDcBa\".");
		else
			total++;

		// (+) Longer test case with close comparison
		if(!areAnagramsHelper("abcdefghijkl", "abcdefghijklm", false))
			System.out.println("Failed in reporting \"abcdefghijkl\" is not an anagram to \"abcdefghijklm\".");
		else
			total++;

		// (+) Longer test case with close comparison (switched order)
		if(!areAnagramsHelper("abcdefghijklm", "abcdefghijkl", false))
			System.out.println("Failed in reporting \"abcdefghijklm\" is not an anagram to \"abcdefghijkl\".");
		else
			total++;

		return total;
	}

	public static boolean GetLargestAnagramGroupHelper(String [] words, String [] ExpectedWords)
	{
		try
		{
			words = AnagramUtil.getLargestAnagramGroup(words);

			if(ExpectedWords == null)
				return (words == null);

			if(words.length != ExpectedWords.length)
				return false;

			for(int i = 0; i < ExpectedWords.length; i++)
			{
				boolean found = false;
				for(int j = 0; j < words.length; j++)
				{
					if(ExpectedWords[i].equals(words[j]))
					{
						found = true;
						continue;
					}
				}
				if(!found)
					return false;
			}

			return true;
		}
		catch(Exception e)
		{
			System.out.println("exception in getLargestAnagramGroup: "+e.toString());
			return false;
		}
	}

	public static int testGetLargestAnagramGroupArray()
	{
		int total = 0;

		//array with all of the same word
		if(!GetLargestAnagramGroupHelper(new String[]{"apple","apple","apple","apple","apple","apple","apple","apple","apple","apple"},
										 new String[]{"apple","apple","apple","apple","apple","apple","apple","apple","apple","apple"}))
			System.out.println("Failed to report the correct list when all values are the same.");
		else
			total+=2;

		//Array with two large groups
		if(!GetLargestAnagramGroupHelper(new String[]{"caba","heya","ahey","abca","baca","aabc","haye","heay","yeah","ayeh"},
				 				   		 new String[]{"heya","ahey","haye","heay","yeah","ayeh"}))
			System.out.println("Failed to report correct anagram list on array with largest group of 6.");
		else
			total+=2;

		//Two equal size groups, just test the size is correct
		if(!(AnagramUtil.getLargestAnagramGroup(new String[]{"abc","cab","def","fed","bca","efd"}).length == 3))
			System.out.println("Failed to report correct size of list when two equal size groups exist.");
		else
			total+=2;

		//List with no anagrams
		if(!GetLargestAnagramGroupHelper(new String[]{"abcd","bcde","cdef","defg","efgh","fghi"}, new String[0]))
			System.out.println("Failed on list without any anagrams.");
		else
			total+=2;

		//List with only one word
		if(!GetLargestAnagramGroupHelper(new String[]{"abcd"}, new String[0]))
			System.out.println("Failed on list without any anagrams.");
		else
			total+=2;

		return total;
	}

	public static void main(String [] args)
	{
		int ssort = 0;
		int sIS = 0;
		int sAA = 0;
		int sGLAGA = 0;
		int sGLAGF = 0;

		System.out.println("Testing AnagramUtil.sort(String s)\n");
		ssort = testSort();
		System.out.println("\nPoints:\t\t\t\t\t\t\t" + ssort + " / 10");
		System.out.println("---------------------------------------------------------------");

		System.out.println("Testing AnagramUtil.insertionSort(T[] data, Comparator<? super T> comp)");
		System.out.println("Using a Comparator<Integer> which sorts in descending order.\n");
		sIS = testInsertionSort();
		System.out.println("\nPoints:\t\t\t\t\t\t\t" + sIS + " / 10");
		System.out.println("---------------------------------------------------------------");

		System.out.println("Testing AnagramUtil.areAnagrams(String a, String b)\n");
		sAA += testAreAnagrams();
		System.out.println("\nPoints:\t\t\t\t\t\t\t" + sAA + " / 10");
		System.out.println("---------------------------------------------------------------");

		System.out.println("Testing AnagramUtil.getLargestAnagramGroup(String [] s)\n");
		sGLAGA = testGetLargestAnagramGroupArray();
		System.out.println("\nPoints:\t\t\t\t\t\t\t" + sGLAGA + " / 10");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Testing AnagramUtil.getLargestAnagramGroup(String filename)\n");

		//Change the directory to the right place
		String filename = AnagramUtilGrader.class.getClassLoader().getResource("sample_word_list.txt").getPath();
		String [] testGroup = AnagramUtil.getLargestAnagramGroup(filename);
		boolean testPassed = (AnagramUtil.getLargestAnagramGroup(testGroup).length == 7);
		String [] ExpectedWords = new String[] {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};

		for(int i = 0; i < ExpectedWords.length && testPassed; i++)
		{
			boolean found = false;
			for(int j = 0; j < testGroup.length; j++)
			{
				if(ExpectedWords[i].equals(testGroup[j]))
				{
					found = true;
					continue;
				}
			}
			if(!found)
			{
				testPassed = false;
				break;
			}
		}

		if(!testPassed)
			System.out.println("Failed to report the correct anagram list for the test file given.");
		else
			sGLAGF = 5;

		System.out.println("\nPoints:\t\t\t\t\t\t\t" + sGLAGF + " / 5");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Insertion sort used\n");
		System.out.println("\nPoints:\t\t\t\t\t\t\t" + "xx / 5");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Style & Testing\n");
		System.out.println("\nPoints:\t\t\t\t\t\t\t" + "xx / 10");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Summary\n");
		int total = ssort + sIS + sAA + sGLAGA + sGLAGF;

		System.out.println("Total:\t\t\t\t\t\t\t" + total + " / 60");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Comments: \n");
	}
}