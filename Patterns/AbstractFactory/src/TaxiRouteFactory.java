import java.awt.*;
import java.util.List;

public class TaxiRouteFactory implements RouteAbstractFactory {
    List<Point> points;
    private double costPerKm;
    private double costPerMinute;

    public TaxiRouteFactory(List<Point> points, double costPerKm, double costPerMinute) {
        this.points = points;
        this.costPerKm = costPerKm;
        this.costPerMinute = costPerMinute;
    }

    @Override
    public Route createRoute() {
        TaxiRoute route = new TaxiRoute(points);
        route.setCostPerRKm(costPerKm);
        route.setCostPerMinute(costPerMinute);
        double averageSpeed = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            averageSpeed += SpeedProvider.getSpeed(
                    points.get(i), points.get(i + 1));
        }
        averageSpeed /= (points.size() - 1);
        route.setAverageSpeed(averageSpeed);
        return route;
    }
}
