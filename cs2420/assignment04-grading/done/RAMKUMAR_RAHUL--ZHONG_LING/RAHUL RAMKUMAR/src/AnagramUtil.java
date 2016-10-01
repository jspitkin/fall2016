package assignment04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;




/**
 * @author Rahul Ramkumar and Lingxi Zhong Last Edited: 9/21/16.
 */
public class AnagramUtil {
    private static final String RESOURCE_PATH = "files/assignment04/";
    /**
     * This method returns the sorted version of the input string. The sorting is accomplished using an insertion sort.
     * @param string
     * @return
     */
    public static <T> String sort(String string){
        Character[] charArray = new Character[string.length()];

        for(int i = 0; i < string.length(); i++){
            charArray[i] = string.toLowerCase().charAt(i);
        }
        Comparator<Character> charComp = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1.compareTo(o2);
            }
        };
        insertionSort((T[])charArray, (Comparator<? super T>) charComp);
        string = "";
        for(Character c : charArray){
            string += c;
        }
        return string;
    }

    /**
     * This generic method sorts the input array using an insertion sort and the input Comparator object.
     * @param objArray
     * @param comparator
     * @param <T>
     */
    public static <T> void insertionSort(T[] objArray, Comparator<? super T> comparator){
        for(int i = 0; i < objArray.length; i++){
            for(int sublist_i = i-1; sublist_i >= 0; sublist_i--){
                if(comparator.compare(objArray[sublist_i], objArray[sublist_i+1]) > 0){
                    swap(objArray, sublist_i, sublist_i + 1);
                }
            }
        }
    }

    private static <T> void swap(T[] objArray, int index1, int index2){
        T temp = objArray[index1];
        objArray[index1] = objArray[index2];
        objArray[index2] = temp;
    }
    /**
     * This method returns true if the two input strings are anagrams of each other, otherwise returns false.
     * @param one
     * @param two
     * @return
     */
    public static boolean areAnagrams(String one, String two){
        if(sort(one).equals(sort(two))){
            return true;
        }
        return false;
    }

    /**
     * This method returns the largest group of anagrams in the input array of words, in no particular order.
     * It returns an empty array if there are no anagrams in the input array.
     * @param stringArray
     * @return
     */
    public  static <T> String[] getLargestAnagramGroup(String[] stringArray){
        Comparator<String> anagramGroupSorter = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return sort(o1).compareTo(sort(o2));
            }
        };
        //insertionSort((T[])stringArray, (Comparator<? super T>) anagramGroupSorter);
        Arrays.sort(stringArray, anagramGroupSorter);
        String current = "";
        ArrayList<ArrayList<String>> groupTrack = new ArrayList<>();
        int currentOuterListIndex = -1;
        for(String s : stringArray){
            if(sort(s).equals(sort(current))){
                groupTrack.get(currentOuterListIndex).add(s);
            }
            else{
                groupTrack.add(new ArrayList<String>());
                currentOuterListIndex++;
                groupTrack.get(currentOuterListIndex).add(s);
                current = s;
            }
        }
        int mostElements = 0;
        ArrayList<String> biggestList = new ArrayList<>();
        for(ArrayList<String> list : groupTrack){
            if(list.size()>mostElements){
                mostElements = list.size();
                biggestList = list;
            }
        }
        String[] biggestListArray = new String[mostElements];
        for(int i = 0; i < mostElements; i++){
            biggestListArray[i] = biggestList.get(i);
        }
        if(biggestListArray.length < 2){
            return new String[0];
        }
        return biggestListArray;
    }

	/**
	 * Behaves the same as the other getLargestAnagramGroup() method, but reads
	 * the list of words from the input filename. It is assumed that the file
	 * contains one word per line. If the file does not exist or is empty, the
	 * method returns an empty array because there are no anagrams.
	 *
	 * @param filename
	 * @return
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		URL path = AnagramUtil.class.getResource(filename);
		try (BufferedReader input = new BufferedReader(new FileReader(new File(path.getFile())))) {
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (NullPointerException e) {
			try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
				while (input.ready()) {
					results.add(input.readLine());
				}
				input.close();
			} catch (Exception other_e) {
				other_e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getLargestAnagramGroup(results.toArray(new String[results.size()]));
	}

}
