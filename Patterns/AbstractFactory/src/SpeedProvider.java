import java.awt.*;
import java.util.Random;

public class SpeedProvider {
    private static Random random = new Random();

    public static double getSpeed(Point p1, Point p2) {
        return 30 + 10 * random.nextDouble();
    }
}
