import java.awt.*;
import java.util.List;

public class TaxiRoute extends Route {
    private double averageSpeed;
    private static final double taxiSpeedCoeff = 0.7;

    private double costPerRKm;
    private double costPerMinute;

    public TaxiRoute(List<Point> points) {
        super(points);
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public double calculateCost() {
        double priceForLength = costPerRKm * calculateLength();
        double priceForDuration = costPerMinute * calculateDuration();
        return Math.min(priceForLength, priceForDuration);
    }

    @Override
    public double calculateDuration() {
        return calculateLength() / averageSpeed * 60;
    }

    @Override
    public double calculateLength() {
        double totalLength = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            totalLength += LengthCalculator.getLength(
                    points.get(i), points.get(i + 1));
        }
        return totalLength * taxiSpeedCoeff;
    }

    public void setCostPerRKm(double costPerRKm) {
        this.costPerRKm = costPerRKm;
    }

    public void setCostPerMinute(double costPerMinute) {
        this.costPerMinute = costPerMinute;
    }
}
