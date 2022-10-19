import java.awt.*;
import java.util.Random;

public class LengthCalculator {
    private static Random random = new Random();

    public static double getLength(Point p1, Point p2) {
        return 10 + random.nextDouble();
    }
}
