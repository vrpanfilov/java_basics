import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoaderDB {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        findEqualVoters(doc);
    }

    private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            Date birthDay = birthDayFormat
                    .parse(attributes.getNamedItem("birthDay").getNodeValue());

            DBConnection.countVoter(name, attributes.getNamedItem("birthDay").getNodeValue());
        }
        DBConnection.executeMultiInsert();
    }

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";

        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();

        parseFile(fileName);

        long end = System.currentTimeMillis();
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;

        DBConnection.printVoterCounts();

        System.out.println("Duration: " + (end - start));
        System.out.println(usage);

    }
}
