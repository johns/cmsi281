import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    public static void main(java.lang.String[] args) throws IOException {

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Double> list = new ArrayList<Double>();
        String currentLine;

        while ((currentLine = stdIn.readLine()) != null) {
            list.add(Double.parseDouble(currentLine));
        }

        if (list.size() != 0) {
            //long startTime = System.nanoTime();
            //System.out.println("Before: " + list);
            list = bucketSort(list, list.size());
            //System.out.println("After:  " + list);
            //long endTime = System.nanoTime();

            System.out.println(list);

            //System.out.println("Total Time Sorting: " + (double)(endTime - startTime) / 1000000000);
        }

    }

    public static ArrayList<Double> bucketSort (ArrayList<Double> list, int n) {

        double max = Collections.max(list);
        double min = Collections.min(list);
        double interval = (max - min + 1) / n;

        ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>(n);

        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<Double>());
        }

        for (int i = 0; i < list.size(); i++) {
            buckets.get((int)((list.get(i) - min)/interval)).add(list.get(i));
        }

        int counter = 0;
        for (int i = 0; i < buckets.size(); i++) {
            buckets.set(i, insertSort(buckets.get(i)));
            for (int j = 0; j < buckets.get(i).size(); j++) {
                list.set(counter, buckets.get(i).get(j));
                counter++;
            }
        }

        return list;
    }

    public static ArrayList<Double> insertSort(ArrayList<Double> input) {
        double temp;
        for (int i = 1; i < input.size(); i++) {
            for(int j = i ; j > 0 ; j--){
                if(input.get(j) < input.get(j-1)){
                    temp = input.get(j);
                    input.set(j, input.get(j-1));
                    input.set(j-1, temp);
                }
            }
        }
        return input;
    }
}
