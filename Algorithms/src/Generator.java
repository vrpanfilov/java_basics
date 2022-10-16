import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    public static int[] generateRandom(int count) {
        int[] arr = new int[count];
        Random random = new Random(7372);
        for (int i = 0; i < count; i++) {
            arr[i] = random.nextInt(count * 2);
        }
        return arr;
    }

    public static int[] generateIncr(int count) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] generateDesc(int count) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = count - i;
        }
        return arr;
    }
}
