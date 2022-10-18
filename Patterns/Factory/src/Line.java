public class Line implements Figure {
    private Point begin;
    private Point end;

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }

    public Line(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public double calculatePerimeter() {
        return length(begin, end);
    }

    @Override
    public double calculateSquare() {
        return 0;
    }

    @Override
    public double getWidth() {
        return Math.abs(end.getX() - begin.getX());
    }

    @Override
    public double getHeight() {
        return Math.abs(end.getY() - begin.getY());
    }
}
