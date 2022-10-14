import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator calculator;

    @Override
    protected void setUp() throws Exception {
        stationIndex = Main.getStationIndex();
        calculator = new RouteCalculator(stationIndex);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testStationsOnOneLine() {
        Station from = stationIndex.getStation("Волковская");
        Station to = stationIndex.getStation("Старая деревня");
        List<Station> route = calculator.getShortestRoute(from, to);

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 20.0;
        assertEquals(expected, actual);
    }

    public void testOneConnection() {
        Station from = stationIndex.getStation("Пролетарская");
        Station to = stationIndex.getStation("Спасская");
        List<Station> route = calculator.getShortestRoute(from, to);

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 18.5;
        assertEquals(expected, actual);
    }

    public void testTwoConnections() {
        Station from = stationIndex.getStation("Спортивная");
        Station to = stationIndex.getStation("Елизаровская");
        List<Station> route = calculator.getShortestRoute(from, to);

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 22.0;
        assertEquals(expected, actual);
    }
}
