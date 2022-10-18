public interface Figure {
    double calculatePerimeter();
    double calculateSquare();
    double getWidth();
    double getHeight();

    default double length(Point begin, Point end) {
        return Math.sqrt((end.getX() - begin.getX()) * (end.getX() - begin.getX()) +
                (end.getY() - begin.getY()) * (end.getY() - begin.getY()));
    }

    default String getName() {
        return getClass().getName();
    }

    default void print() {
        System.out.println(getName());
        System.out.println("\tPerimeter: " + calculatePerimeter());
        System.out.println("\tSquare: " + calculateSquare());
        System.out.println("\tWidth: " + getWidth());
        System.out.println("\tHeight: " + getHeight());
    }
}
