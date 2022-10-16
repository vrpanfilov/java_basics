import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SAXLoader {
    private SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private HashMap<Voter, Integer> voterCounts = new HashMap<>();
    private HashSet<Integer> hashCodes = new HashSet<>();

    DefaultHandler handler = new DefaultHandler() {
        private Voter voter;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            try {
                if (qName.equals("voter") && voter == null) {
                    voter = new Voter(attributes.getValue("name"),
                            birthDayFormat.parse(attributes.getValue("birthDay")));
                    if (voter.getName().equals("Юнусов Епифан")) {
                        voter = voter;
                    }
                } else if (qName.equals("visit") && voter != null) {
                    if (!hashCodes.add((Integer) voter.hashCode())) {
                        int count = voterCounts.getOrDefault(voter, 1);
                        voterCounts.put(voter, count + 1);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("voter")) {
                voter = null;
            }
        }
    };

    public void printDuplicateVoters() {
        System.out.println("Duplicated voters: ");
        List<Map.Entry<Voter, Integer>> entries = voterCounts.entrySet().stream()
                .sorted((en1, en2) -> {
                    return en1.getValue() != en2.getValue() ?
                            en1.getValue() - en2.getValue() :
                            en1.getKey().getName().compareTo(en2.getKey().getName());
                }).toList();
        for (var entry : entries) {
            System.out.println(entry.getKey().toString()+ " - " + entry.getValue());
        }
    }

    public SAXLoader(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Стартуем разбор XML-документа
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        SAXLoader loader = new SAXLoader("res/data-18M.xml");
        loader.printDuplicateVoters();

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        System.out.println("Memory usage: " + usage);
    }
}
