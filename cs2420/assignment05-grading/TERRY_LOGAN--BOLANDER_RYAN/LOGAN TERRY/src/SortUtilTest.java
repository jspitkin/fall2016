package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import static assignment05.SortUtil.*;

import org.junit.Test;

/**
 * Test class for SortUtil.
 * @author Ryan Bolander u0016911
 * @author Logan Terry u0973436
 *
 */
public class SortUtilTest {

	@Test
	public void GenerateBestCaseNormal(){
		ArrayList<Integer> list = generateBestCase(100);
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void GenerateBestCaseSizeZero(){
		assertEquals(new ArrayList<Integer>(), generateBestCase(0));
	}
	
	@Test
	public void GenerateBestCaseNegative(){
		assertEquals(new ArrayList<Integer>(), generateBestCase(-1));
	}
	
	@Test
	public void GenerateAverageCaseNormal(){
		ArrayList<Integer> list = generateAverageCase(100);
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertFalse(correct);
		correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (list.size() - i)).equals(list.get(i)))
			{
				correct = false;
			}
		}
		
		assertFalse(correct);
	}
	
	@Test
	public void GenerateAverageCaseSizeZero(){
		assertEquals(new ArrayList<Integer>(), generateAverageCase(0));
	}
	
	@Test
	public void GenerateAverageCaseNegative(){
		assertEquals(new ArrayList<Integer>(), generateAverageCase(-1));
	}
	
	@Test
	public void GenerateWorstCaseNormal(){
		ArrayList<Integer> list = generateWorstCase(100);
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (list.size() - i).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void GenerateWorstCaseSizeZero(){
		assertEquals(new ArrayList<Integer>(), generateWorstCase(0));
	}
	
	@Test
	public void GenerateWorstCaseNegative(){
		assertEquals(new ArrayList<Integer>(), generateWorstCase(-1));
	}
	
	@Test
	public void QuickSortBestTest(){
		ArrayList<Integer> list = generateBestCase(100);
		quicksort(list, Comparator.naturalOrder());
		
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void QuickSortAverageTest(){
		ArrayList<Integer> list = generateAverageCase(100);
		quicksort(list, Comparator.naturalOrder());
		
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void QuickSortWorstTest(){
		ArrayList<Integer> list = generateWorstCase(100);
		quicksort(list, Comparator.naturalOrder());
		
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}

	public void QuickSortEmptyList(){
		ArrayList<Integer> empty = generateBestCase(0);
		quicksort(empty,Comparator.naturalOrder());
		assertEquals(new ArrayList<Integer>(), empty);
	}
	
	@Test (expected = NullPointerException.class)
	public void QuickSortNullInput(){
		quicksort(null,Comparator.naturalOrder());
	}
	
	@Test
	public void MergeSortBestTest(){
		ArrayList<Integer> list = generateBestCase(100);
		mergesort(list, Comparator.naturalOrder());
		
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void MergeSortAverageTest(){
		ArrayList<Integer> list = generateAverageCase(100);
		mergesort(list, Comparator.naturalOrder());
		
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void MergeSortWorstTest(){
		ArrayList<Integer> list = generateWorstCase(100);
		mergesort(list, Comparator.naturalOrder());
		
		boolean correct = true;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(!(new Integer (i + 1).equals(list.get(i))))
			{
				correct = false;
			}
		}
		
		assertTrue(correct);
	}
	
	@Test
	public void MergeSortEmptyList(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		mergesort(list,Comparator.naturalOrder());
		assertEquals(new ArrayList<Integer>(), list);
	}
	
	@Test (expected = NullPointerException.class)
	public void MergeSortOnNull(){
		mergesort(null,Comparator.naturalOrder());
	}
	
}
