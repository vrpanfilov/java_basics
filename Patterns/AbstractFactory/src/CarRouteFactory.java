import java.awt.*;
import java.util.List;

public class CarRouteFactory implements RouteAbstractFactory {
    List<Point> points;

    public CarRouteFactory(List<Point> points) {
        this.points = points;
    }

    @Override
    public Route createRoute() {
        return new CarRoute(points);
    }
}
