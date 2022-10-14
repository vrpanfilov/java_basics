import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class JsonEncoder {
    public JSONObject getObjSchema() {
        return objSchema;
    }

    private MoscowMetro metro;
    private JSONObject objSchema = new JSONObject();

    public void encodeMetro(MoscowMetro metro) {
        this.metro = metro;

        JSONObject objStations = new JStations();
        JSONArray objConnections = new JSONArray();
        JSONArray objLines = new JSONArray();

        objSchema.put("stations", objStations);
        objSchema.put("connections", objConnections);
        objSchema.put("lines", objLines);

        fillLines(objLines);
        fillStations(objStations);
        fillConnections(objConnections);
    }

    private void fillLines(JSONArray objLines) {
        Map<String, Line> lines = metro.getLines();
        for (Map.Entry<String, Line> entry : lines.entrySet()) {
            Line line = entry.getValue();
            Map<String, String> map = new HashMap<>();

            map.put("number", line.getNumber());
            map.put("name", line.getName());
            map.put("color", line.getColor());

            Map sortedMap = map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e1,
                                    LinkedHashMap::new));
            objLines.add(sortedMap);
        }
    }

    private void fillStations(JSONObject objStations) {
        Map<String, Line> lines = metro.getLines();
        for (Map.Entry<String, Line> entry : lines.entrySet()) {
            String number = entry.getKey();
            Line line = entry.getValue();

            List<Station> stations = line.getStations();
            JSONArray objStats = new JSONArray();
            for (Station station : stations) {
                objStats.add(station.getName());
            }
            objStations.put(line.getNumber(), objStats);
        }
    }

    private void fillConnections(JSONArray objConnections) {
        List<List<Station>> connectionList = metro.getConnections();
        for (List<Station> connectionGroup : connectionList) {
            JSONArray arrConnectionGroup = new JSONArray();
            for (Station station : connectionGroup) {
                JSONObject objStation = new JSONObject();
                objStation.put("line", station.getLine().getNumber());
                objStation.put("station", station.getName());
                arrConnectionGroup.add(objStation);
            }
            objConnections.add(arrConnectionGroup);
        }
    }

    static class JStations extends JSONObject {
        @Override
        public String toJSONString() {
            HashMap<String, String> map = this;
            Map sortedMap = map.entrySet()
                    .stream()
                    .sorted((i1, i2) -> Line.compare(i1.getKey(), i2.getKey()))
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e1, LinkedHashMap::new));
            return toJSONString(sortedMap);
        }
    }
}
