package assignment04;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/**
 * 
 * @author Angel Dhungana
 * @author Daniel Zhu
 *
 */
public class AnagramUtilWithJavaSort 
{
	private static CharacterComparator comp=new CharacterComparator();
	
	
	/**
	 * This method returns the sorted version of the input string. 
	 * The sorting is  accomplished using an insertion sort.
	 * @param sortedInput
	 * @return Sorted string
	 */
	public static void main(String[] args){
		AnagramUtil abc=new AnagramUtil();
		String a="act";
		String b="cat";
		System.out.println(sort(a));
		String[] words = readFile("sample_word_list.txt");
		
		System.out.println(Arrays.toString(getLargestAnagramGroup("sample_word_list.txt")));
		System.out.println(abc.sort(a));
		System.out.println(abc.sort(b));
	}
	
	
	
	public static String sort(String sortedInput)
	{	
		
		Character[] letters=ToCharacterArray(sortedInput);
		Arrays.sort(letters);
		
		String a="";
		
		for(int i=0;i<letters.length;i++){
			a+=letters[i];
		}
		
		return a;
	}
	
	
	

	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param list
	 * @param comparator
	 * 
	 */

	public static <T> void insertionSort(T[] list,Comparator<? super T> comparator)
	{
	
		  for(int i=0; i < list.length-1; i++)
		  {
		  for(int j=i+1;j<list.length;j++){
			if(comparator.compare(list[i], list[j])>0){
				T temp=list[i];
				list[i]=list[j];
				list[j]=temp;
			}
		  }
		  }
		  
		
		
	}
/**
 * returns true if two strings are anagrams and false if they are not
 * @param input1
 * @param input2
 * @return boolean 
 */
	public static boolean areAnagrams(String input1, String input2)
	{
		String data = sort(input1.toLowerCase());
		String data2 = sort(input2.toLowerCase());
		if(data.equals(data2)){
				return true;
		}
		return false;
	}
	/**
	 * This method returns the largest group of anagrams in the input array of words, in no particular order.
	 *  It returns an empty array if there are no anagrams in the input array.
	 * @param largestAnagram
	 * @return String array
	 */
	public static String[]getLargestAnagramGroup(String[] largestAnagram)
	{
		ArrayList<String> largestAnagramGroup=new ArrayList<String>();
		ArrayList<String> currentAnagramList=new ArrayList<String>();
		
		
		for(int i=0; i<largestAnagram.length-1; i++){
			currentAnagramList.add(largestAnagram[i]);
			for(int j=i+1; j<largestAnagram.length;j++){
				if(areAnagrams(largestAnagram[i],largestAnagram[j])){
					
					currentAnagramList.add(largestAnagram[j]);
			}
			
		}
			if(currentAnagramList.size()>1&&largestAnagramGroup.size()<currentAnagramList.size()){
				largestAnagramGroup.clear();
				largestAnagramGroup.addAll(currentAnagramList);
			}
			currentAnagramList.clear();
		}
		String[] largeanagramarray=new String[largestAnagramGroup.size()];
		for(int i=0;i<largestAnagramGroup.size();i++){
			largeanagramarray[i]=largestAnagramGroup.get(i);
			
			
		}
		
		
		return largeanagramarray;
	}
	
	
	private static Character[] ToCharacterArray(String word){
		
		Character[] Characterwords=new Character[word.length()];
		for(int i=0;i<word.length();i++){
			Characterwords[i]=word.charAt(i);	
		}
		
		return Characterwords;
	}
	/**
	 * Behaves the same as the previous method, but reads the list of words from the input filename.
	 * If the file does not exist or is empty, the method returns an empty array because there are no anagrams.
	 * @param filename
	 * @return
	 */
	
	public static String[] getLargestAnagramGroup(String filename)
	{
	return getLargestAnagramGroup(readFile(filename));	
	}



public static String[] readFile(String filename) {
	ArrayList<String> results = new ArrayList<String>();
	try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
		while(input.ready()) {
			results.add(input.readLine());
		}
		input.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
	return results.toArray(new String[results.size()]);
}
}

