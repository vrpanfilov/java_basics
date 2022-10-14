import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonDecoder {
    public static ArrayList<Line> getLines() {
        return lines;
    }

    private static JSONObject jData;
    private static JSONArray jLines;
    private static JSONObject jStations;

    private static ArrayList<Line> lines = new ArrayList<>();
    private static Map<String, Line> number2line = new HashMap<>();

    public static void decode(String fileName) throws IOException, ParseException {
        String content = getJsonFile(fileName);
        JSONParser parser = new JSONParser();
        jData = (JSONObject) parser.parse(content);

        jLines = (JSONArray) jData.get("lines");
        jStations = (JSONObject) jData.get("stations");

        initLines();
        initStations();
    }

    private static String getJsonFile(String fileName) throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        lines.forEach(builder::append);
        return builder.toString();
    }

    private static void initLines() {
        for (Object oLine : jLines) {
            JSONObject jLine = (JSONObject) oLine;
            String lineNumber = (String) jLine.get("number");
            Line line = new Line(lineNumber, (String) jLine.get("name"));
            lines.add(line);
            number2line.put(lineNumber, line);
        }
    }

    private static void initStations() {
        for (Object o : jStations.entrySet()) {
            Map.Entry<String, JSONArray> entry = (Map.Entry<String, JSONArray>) o;
            String lineNumber = entry.getKey();
            JSONArray jStations = entry.getValue();
            jStations.forEach(o1 -> {
                Line line = number2line.get(lineNumber);
                Station station = new Station(o1.toString(), line);
                line.addStation(station);
            });
        }
    }
}
