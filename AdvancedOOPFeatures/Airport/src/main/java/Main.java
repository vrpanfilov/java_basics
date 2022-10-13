import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Airport airport;

    public static void main(String[] args) {
        airport = Airport.getInstance();
        List<Flight> leavings = findPlanesLeavingInTheNextTwoHours(airport);
        leavings.stream().forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date now = convertToDate(LocalDateTime.now());
        Date end = convertToDate(LocalDateTime.now().plusHours(2));
        List<Flight> flights = airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(f -> f.getDate().after(now) && f.getDate().before(end)
                        && f.getType() == Flight.Type.DEPARTURE)
                .collect(Collectors.toList());
        return !flights.isEmpty() ? flights :
                Collections.emptyList();
    }

    // From https://www.baeldung.com/java-date-to-localdate-and-localdatetime
    private static Date convertToDate(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}