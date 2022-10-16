import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SAXLoaderDB {
    private final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private int total_count = 0;

    DefaultHandler handler = new DefaultHandler() {
        private String voterName = "";
        private String voterBirthDay = "";

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("voter") && voterName.isEmpty()) {
                voterName = attributes.getValue("name");
                voterBirthDay = attributes.getValue("birthDay");
            } else if (qName.equals("visit") && !voterName.isEmpty()) {
                try {
                    DBConnection.countVoter(voterName, voterBirthDay);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
                if (++total_count % 1024 == 0) {
                    System.out.print("\rtotal_count: " + total_count / 1024 + " K");
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("voter")) {
                voterName = "";
            }
        }
    };

    public SAXLoaderDB(String fileName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        saxParser.parse(fileName, handler);
    }

    public static void main(String[] args) throws Exception {
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();

        SAXLoaderDB loader = new SAXLoaderDB("res/data-1572M.xml");
        DBConnection.executeMultiInsert();

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;

        System.out.println();
        DBConnection.printVoterCounts();

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        System.out.println("Memory usage: " + usage);
    }
}
