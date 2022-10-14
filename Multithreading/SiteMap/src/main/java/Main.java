import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final String DATA_FOLDER = "data/";
    public static final String SITE_URL = "https://skillbox.ru/";

    public static void main(String[] args) throws IOException {
        int threadNumber = 1;
        Node node = new Node(SITE_URL, 0);
        SiteMapCalculator calculator = new SiteMapCalculator(node);
        String siteMap = new ForkJoinPool(threadNumber).invoke(calculator);

        File dataDir = new File(DATA_FOLDER);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        try (FileWriter writer = new FileWriter(DATA_FOLDER + "site_map.txt")) {
            writer.write(siteMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

