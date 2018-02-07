import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;



public class Select {

    public static void main(String args[]) throws IOException {

        int kth = Integer.parseInt(args[0]);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = stdIn.readLine();
        ArrayList<Integer> inputs = new ArrayList<Integer>();

        while (currentLine != null) {
            inputs.add(Integer.parseInt(currentLine));
            currentLine = stdIn.readLine();
        }


        if (kth > inputs.size() - 1) {
            throw new IOException("BAD DATA");
        }

        long startTime = System.nanoTime();
        quickSort(inputs, inputs.size() - 1, kth);
        System.out.print("this workd");
        long endTime = System.nanoTime();

        System.out.println("Total Time Sorting: " + (double)(endTime - startTime) / 1000000000);
    }

    private static int quickSort(ArrayList<Integer> inputs, int n, int k) {

        int pivot = partition(inputs, 0, n);
        if (pivot > k) {
            quickSort((ArrayList<Integer>) inputs.subList(0, pivot), pivot - 1, k);
        }
        else if (pivot < k) {
            quickSort((ArrayList<Integer>) inputs.subList(pivot+1, n), n-pivot, k-pivot);
        }
        return pivot;
    }

    private static int partition(ArrayList<Integer> inputs, int f, int n) {
        int pivot = (int) (Math.random() * n);
        int last = n;
        int first = f + 1;

        while (last > first) {
            while (first < last && inputs.get(first) < pivot) {
                f++;
            }
            while (first < last && inputs.get(last) >= pivot) {
                last--;
            }
            if (last > first) {
                swap(inputs, last, first);
            }
        }
        //while (first < n && inputs.get(last) >= pivot) {
        //last--;
        //}
        if (pivot > inputs.get(last)) {
            inputs.set(f, inputs.get(last));
            inputs.set(last, pivot);
            return last;
        }
        else {
            return f;
        }
    }

    public static void swap(ArrayList<Integer> inputs, int i, int j) {
        int temp = (int) inputs.get(i);
        inputs.set(i, inputs.get(j));
        inputs.set(j, temp);
    }

}
