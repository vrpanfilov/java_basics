import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuickSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

     private static void sort(int[] arr, int from, int to) {
        if (from < to) {
            int pivot = partition(arr, from, to);
            sort(arr, from, pivot - 1);
            sort(arr, pivot + 1, to);
        }
    }

    private static int partition(int[] arr, int from, int to) {
        int pivot = from;
        int pivotValue = arr[pivot];
        for (int i = from + 1; i <= to; i++) {
            int arrValue = arr[i];
            if (arrValue < pivotValue) {
                arr[i] = arr[++pivot];
                arr[pivot] = pivotValue;
                arr[pivot - 1] = arrValue;
            }
        }
        return pivot;
    }

    public static void main(String[] args) {
        int count = 100_000_000;

//        int[] arr = Generator.generateIncr(count);
//        int[] arr = Generator.generateDesc(count);
        int[] arr = Generator.generateRandom(count);

        long start = System.currentTimeMillis();
        QuickSort.sort(arr);
        System.out.println("Duration: " + (System.currentTimeMillis() - start));

        boolean right = true;
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                right = false;
                break;
            }
        }
        System.out.println(right ? "Correct sorting" : "Incorrect sorting");
    }
}
