public class Rectangle implements Figure {
    private Point[] vertices = new Point[4];

    public Rectangle(Point vertex1, Point vertex2, Point vertex3, Point vertex4) {
        vertices[0] = vertex1;
        vertices[1] = vertex2;
        vertices[2] = vertex3;
        vertices[3] = vertex4;
    }

    @Override
    public double calculatePerimeter() {
        return (length(vertices[0], vertices[1]) +
                length(vertices[1], vertices[2])) * 2;
    }

    @Override
    public double calculateSquare() {
        return length(vertices[0], vertices[1]) *
                length(vertices[1], vertices[2]);
    }

    @Override
    public double getWidth() {
        return length(vertices[0], vertices[1]);
    }

    @Override
    public double getHeight() {
        return length(vertices[1], vertices[2]);
    }
}
