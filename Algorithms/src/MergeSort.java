public class MergeSort {
    public static void sort(int[] array) {
        int n = array.length;
        if (n == 1) {
            return;
        }

        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        System.arraycopy(array, 0, leftArray, 0, middle);
        System.arraycopy(array, middle, rightArray, 0, n - middle);

        sort(leftArray);
        sort(rightArray);
        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] leftArray, int[] rightArray) {
        int n = array.length;
        int ln = leftArray.length;
        int rn = rightArray.length;
        int li = 0;
        int ri = 0;
        for (int i = 0; i < n; i++) {
            if (leftArray[li] < rightArray[ri]) {
                array[i] = leftArray[li++];
                if (li >= ln) {
                    System.arraycopy(rightArray, ri, array, i + 1, n - i - 1);
                    break;
                }
            } else {
                array[i] = rightArray[ri++];
                if (ri >= rn) {
                    System.arraycopy(leftArray, li, array, i + 1, n - i - 1);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int count = 100_000_000;
//        int[] arr = Generator.generateIncr(count);
        int[] arr = Generator.generateRandom(count);

        long start = System.currentTimeMillis();

        MergeSort.sort(arr);

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
