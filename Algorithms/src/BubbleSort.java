public class BubbleSort {
    public static int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int count = 7;
        int[] arr = Generator.generateRandom(count);

        long start = System.currentTimeMillis();

        BubbleSort.sort(arr);

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
