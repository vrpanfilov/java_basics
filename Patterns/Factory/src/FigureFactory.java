import java.util.List;

public class FigureFactory {
    public static Figure createFigure(Point point, double radius) {
        return new Circle(point, radius);
    }

    public static Figure createFigure(List<Point> points) {
        int pointCount = points.size();
        if (pointCount < 2) {
            throw new IllegalArgumentException(
                    "Number of points should be more than 1!");
        }
        switch (pointCount) {
            case 2:
                return new Line(points.get(0), points.get(1));
            case 3:
                return new Triangle(points.get(0), points.get(1),
                        points.get(2));
            case 4:
                return new Rectangle(points.get(0), points.get(1),
                        points.get(2), points.get(3));
        }
        return null;
    }
}
