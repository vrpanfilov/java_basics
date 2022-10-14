import core.Line;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class MoscowMetro {

    public int getConnectionCount() {
        return connectionCount;
    }

    private Map<String, Line> lines = new TreeMap<>(Line::compare);

    private List<List<Station>> connections = new ArrayList<>();

    private int connectionCount;

    public Map<String, Line> getLines() {
        return lines;
    }

    public List<List<Station>> getConnections() {
        return connections;
    }

    public void parseSite() throws IOException {
        String url = "https://skillbox-java.github.io/";
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Element elTextSimple = doc.select("div.t-text-simple").first();
        createLines(elTextSimple);
        createStations(elTextSimple);
        createConnections(elTextSimple);
    }

    private void createLines(Element elTextSimple) throws IOException {
        LineColors colors = new LineColors();

        Elements elLines = elTextSimple.select("div.js-toggle-depend");
        for (Element elLine : elLines) {
            Element metroLine = elLine.select("span.js-metro-line").first();
            String lineNumber = metroLine.attr("data-line");
            String lineName = metroLine.text().replace(" линия", "");
            Line line = new Line(lineNumber, lineName);

            String ln = lineNumber;
            if (lineNumber.endsWith("A")) {
                ln = lineNumber.substring(0, lineNumber.length() - 1);
            }
            String color = colors.get(ln);
            line.setColor(color);

            lines.put(lineNumber, line);
        }
    }

    private void createStations(Element elTextSimple) {
        Elements elLines = elTextSimple.select("div.js-toggle-depend");
        Elements elStationsOnLines = elTextSimple.select("div.js-depend");
        for (int i = 0; i < elStationsOnLines.size(); i++) {
            String lineNumber = elLines.get(i).select("span").first()
                    .attr("data-line");
            Elements elStations = elStationsOnLines.get(i)
                    .select("p.single-station");
            for (Element elStation : elStations) {
                String name = elStation.select("span.name").first().text();
                Line line = lines.get(lineNumber);
                Station station = new Station(name, line);
                line.addStation(station);
            }
        }
    }

    private void createConnections(Element elTextSimple) {
        connectionCount = 0;
        Elements elLines = elTextSimple.select("div.js-toggle-depend");
        Elements elStationsOnLines = elTextSimple.select("div.js-depend");
        for (int i = 0; i < elStationsOnLines.size(); i++) {
            String lineNumber = elLines.get(i).select("span").first()
                    .attr("data-line");
            Line line = lines.get(lineNumber);
            Elements elStations = elStationsOnLines.get(i)
                    .select("p.single-station");
            for (Element elStation : elStations) {
                String name = elStation.select("span.name").first().text();
                Station station = line.getStation(name);
                List<Station> connectionKnot = new ArrayList<>();
                Elements elConnections = elStation.select("span.t-icon-metroln");
                for (Element elConnection : elConnections) {
                    String className = elConnection.className();
                    String lineNumb = className.substring(
                            className.indexOf("ln-") + 3, className.length());
                    String t = elConnection.attr("title");
                    t = t.replace("переход на станцию «", "");
                    String stationName = t.replace(t.substring(t.indexOf("»")), "");
                    Line connectionLine = lines.get(lineNumb);
                    Station connectionStation = connectionLine.getStation(stationName);
                    connectionKnot.add(connectionStation);
                }
                connectionKnot.sort((st1, st2) ->
                    Line.compare(st1.getLine().getNumber(), st2.getLine().getNumber()));
                connectionKnot.add(0, station);

                boolean smallData = true;
                if (elConnections.size() > 0) {
                    boolean equivalentKnotFound = false;
                    if (smallData) {
                        for (List<Station> list : connections) {
                            if (connectionKnot.containsAll(list) &&
                                    list.containsAll(connectionKnot)) {
                                equivalentKnotFound = true;
                                break;
                            }
                        }
                    }
                    if (!equivalentKnotFound) {
                        connections.add(connectionKnot);
                        connectionCount++;
                    }
                }
            }
        }
    }
}
