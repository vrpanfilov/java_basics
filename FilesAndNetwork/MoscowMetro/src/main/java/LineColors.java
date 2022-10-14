import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class LineColors extends HashMap<String, String> {
    public LineColors getLineColors() {
        return this;
    }

    public LineColors() throws IOException {
        super();
        URL linesUrl = new URL("https://moscowmetro.fandom.com/ru/wiki/%D0%9B%D0%B8%D0%BD%D0%B8%D0%B8");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(linesUrl.openStream()))) {
            StringBuilder builder = new StringBuilder();
            String inLine;
            while ((inLine = reader.readLine()) != null) {
                builder.append(inLine);
            }
            String html = builder.toString();
            Document doc = Jsoup.parse(html);
            processMetroTable(doc);
            processDiametersTable(doc);
        }
    }

    private String lineColorPrev = "";

    private void processMetroTable(Document doc) {
        Element table = doc.select("table").first();
        Elements rows = table.select("tr");
        boolean begin = true;
        for (Element row : rows) {
            if (begin) {
                begin = false;
                continue;
            }

            Elements columns = row.select("td");
            Element data = columns.get(0);
            Element img = data.select("img").first();
            if (img == null) {
                continue;
            }
            String lineNumb = img.attr("alt");
            lineNumb = lineNumb.replace("Линия ", "")
                    .replace(".png", "");

            data = columns.get(1);
            String lineName = data.select("font").first().text();

            data = columns.get(2);
            Element el = data.select("b").first();
            String lineColor;
            if (el != null) {
                lineColor = data.select("b").first().text();
            } else {
                lineColor = lineColorPrev;
            }
            lineColorPrev = lineColor;

            this.put(lineNumb, lineColor);
        }
    }

    private void processDiametersTable(Document doc) {
        Element table = doc.select("table").get(1);
        Elements rows = table.select("tr");
        boolean begin = true;
        for (Element row : rows) {
            if (begin) {
                begin = false;
                continue;
            }

            Elements columns = row.select("td");
            Element data = columns.get(0);
            Element img = data.select("img").first();
            if (img == null) {
                continue;
            }
            String lineNumb = img.attr("alt");
            lineNumb = lineNumb.replace("Диаметр ", "D")
                    .replace(".png", "");

            data = columns.get(1);
            String lineName = data.select("font").first().text();

            data = columns.get(2);
            Element el = data.select("b").first();
            String lineColor;
            if (el != null) {
                lineColor = data.select("b").first().text();
            } else {
                lineColor = lineColorPrev;
            }
            lineColorPrev = lineColor;

            this.put(lineNumb, lineColor);
        }
    }
}
