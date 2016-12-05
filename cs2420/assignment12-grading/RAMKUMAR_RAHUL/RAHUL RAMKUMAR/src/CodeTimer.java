package CodeTiming;

import assignment04.AnagramUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rahul on 9/21/16.
 */
public class CodeTimer<T> {
    private ArrayList<T> times;
    private int inputSize;
    private String type;
    private String method_tested;

    public CodeTimer(int inputSize, String data_type, String method_tested){
        times = new ArrayList<>();
        this.inputSize = inputSize;
        this.type = data_type;
        this.method_tested = method_tested;
    }

    public void add(T time){
        times.add(time);
    }


    public void writeToFile(String filename){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
            String dataToWrite = "";
            for(T time : times){
                if(dataToWrite.length() != 0){
                    dataToWrite += ",";
                }
                dataToWrite += time.toString();
            }
            out.write("\n" + method_tested + ","+ type + "," + inputSize + ",[" + dataToWrite + "]");
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<? extends Comparable> generateList(Class<?> type, int length){
        if(!(type == String.class || type == int.class)){
            throw new UnsupportedOperationException("generateList doesn't support making lists of type " + type.toString());
        }

        if(type == String.class){
            ArrayList<String> returnList = new ArrayList<String>();
            int substrSize = 2;
            for(int n = 1; n < length; n *= (n+1)){
                substrSize+=2;
            }

            String selection = "abcdefghijklmnop";
            returnList = permutation(selection.substring(0, substrSize%(selection.length()-1)));
            return new ArrayList<>(returnList.subList(0, length));
        }
        else if(type == int.class){
            ArrayList<Integer> returnList = new ArrayList<>();
            for(int i = 0; i < length; i++){
                returnList.add(i);
            }
            Collections.shuffle(returnList);
            return returnList;
        }
        return new ArrayList<>();

    }
    public static ArrayList<String> permutation(String str) {
        ArrayList<String> returnArr = new ArrayList();
        permutation("", str, returnArr);
        return returnArr;

    }

    private static void permutation(String prefix, String str, ArrayList list) {
        int n = str.length();
        if(n == 0) list.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), list); //Recursion!
        }
    }
}
