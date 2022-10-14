import core.Line;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static final String FILE_NAME = "data/map.json";

    private static HashMap<String, String> lineColors = null;

    public static void main(String[] args) throws Exception {
        MoscowMetro metro = new MoscowMetro();
        metro.parseSite();

        JsonEncoder encoder = new JsonEncoder();
        encoder.encodeMetro(metro);

        JSONObject objSchema = encoder.getObjSchema();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            String s = objSchema.toJSONString();
            writer.write(s);
        }

        JsonDecoder.decode(FILE_NAME);
        List<Line> lines = JsonDecoder.getLines();
        System.out.println("Линии                    Количество станций");
        lines.forEach(line -> System.out.printf(
                "%-30s%5d\n", line.getName(), line.getStations().size()));
        System.out.printf("\n%s%6d\n\n", "Количество узлов пересадок", metro.getConnectionCount());
    }
}
