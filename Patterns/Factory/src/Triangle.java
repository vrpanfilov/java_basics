import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.List;

public class Triangle implements Figure {
    private Point[] vertices = new Point[3];

    public Triangle(Point vertex1, Point vertex2, Point vertex3) {
        vertices[0] = vertex1;
        vertices[1] = vertex2;
        vertices[2] = vertex3;
    }

    public Point[] getVertices() {
        return vertices;
    }

    @Override
    public double calculatePerimeter() {
        return length(vertices[0], vertices[1]) +
                length(vertices[1], vertices[2]) +
                length(vertices[2], vertices[0]);
    }

    @Override
    public double calculateSquare() {
        double p = calculatePerimeter() / 2;
        return Math.sqrt(p *
                (p - length(vertices[0], vertices[1])) *
                (p - length(vertices[1], vertices[2])) *
                (p - length(vertices[2], vertices[0])));
    }

    @Override
    public double getWidth() {
        double left = Math.min(vertices[0].getX(), vertices[1].getX());
        left = Math.min(left, vertices[2].getX());
        double right = Math.max(vertices[0].getX(), vertices[1].getX());
        right = Math.max(right, vertices[2].getX());
        return Math.abs(right - left);
    }

    @Override
    public double getHeight() {
        double bottom = Math.min(vertices[0].getY(), vertices[1].getY());
        bottom = Math.min(bottom, vertices[2].getY());
        double top = Math.max(vertices[0].getY(), vertices[1].getY());
        top = Math.max(top, vertices[2].getY());
        return Math.abs(top - bottom);
    }
}
