package core;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line>
{
    private String number;
    private String name;
    private String color;
    private List<Station> stations;

    public Line(String number, String name)
    {
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber()
    {
        return number;
    }

    public String getName()
    {
        return name;
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public List<Station> getStations()
    {
        return stations;
    }

    public Station getStation(String stationName) {
        for (Station s : stations) {
            if (s.getName().equals(stationName)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Line line)
    {
        return number.compareTo(line.getNumber());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Line) obj) == 0;
    }

    @Override
    public String toString() {
        String ss = "";
        for (Station s : stations) {
            ss += "\n\t" + s.toString();
        }
        return "Line{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ss +
                '}';
    }
    public static int compare(String s1, String s2) {
        if (s1.length() == 1) {
            s1 = "0" + s1;
        }
        if (s2.length() == 1) {
            s2 = "0" + s2;
        }
        return s1.compareTo(s2);
    }
}
