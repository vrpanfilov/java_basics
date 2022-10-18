import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Figure figure = FigureFactory.createFigure(
                new Point(0, 1), 3);
        figure.print();

        figure = FigureFactory.createFigure(Arrays.asList(
                new Point(0, 0), new Point(4, 3)));
        figure.print();

        figure = FigureFactory.createFigure(Arrays.asList(
                new Point(0, 0), new Point(3, 0), new Point(3, 4)));
        figure.print();

        figure = FigureFactory.createFigure(Arrays.asList(
                new Point(0, 0), new Point(3, 0),
                new Point(3, 4), new Point(0, 4)));
        figure.print();
    }

}
