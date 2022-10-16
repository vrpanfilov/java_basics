public class BinarySearch {
    public static int search(int[] arr, int value, int from, int to) {
        if (from > to) {
            return -1;
        }
        int middle = (from + to) / 2;
        int comparison = value - arr[middle];
        if (comparison == 0) {
            return middle;
        }
        if (comparison < 0) {
            return search(arr, value, from, middle - 1);
        }
        return search(arr, value, middle + 1, to);
    }

    public static void main(String[] args) {
        int count = 1000000;
        int[] arr = Generator.generateIncr(count);
        int value = arr[999998];
        value = 15;
        int index = BinarySearch.search(arr, value, 0, count - 1);
        if (index >= 0) {
            System.out.println(arr[index] == value ? "Found!" : "Lost");
            System.out.println("index: " + index);
        } else {
            boolean found = false;
            for (int i = 0; i < arr.length; i++) {
                if (value == arr[i]) {
                    found = true;
                    break;
                }
            }
            System.out.println("Not found!");
            System.out.println(!found ? "Correct result" : "Incorrect result");
        }
    }
}
