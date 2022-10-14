import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Data
public class Node {
    private static HashSet<String> viewed = new HashSet<>();

    private String url;
    private int level;
    private String baseUri;
    private String origin;

    public Node(String url, int level) {
        this.url = url;
        this.level = level;
        origin = defineOrigin(url);
    }

    public Collection<Node> getChildren() throws IOException {
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            return null;
        }
        Document doc = null;
        try {
            doc = Jsoup.connect(url).maxBodySize(0).get();
        } catch (Exception e) {
            return null;
        }
        if (level == 0) {
            SiteMapCalculator.setBaseUri(doc.baseUri());
            viewed.add(doc.baseUri());
        }
        baseUri = SiteMapCalculator.getBaseUri();
        String baseOrigin = defineOrigin(baseUri);

        Collection<Node> children = new ArrayList<>();

        Elements elRefs = doc.select("a");
        for (Element elRef : elRefs) {
            String ref = elRef.attr("href");
            if (ref.startsWith("/")) {
                ref = baseUri + ref.substring(1, ref.length());
            }
            if (viewed.contains(ref)) {
                continue;
            }
            viewed.add(ref);
            if (!ref.endsWith("/") || ref.startsWith("#")) {
                continue;
            }
            Node child = new Node(ref, level + 1);
            if (!baseOrigin.equals(child.origin)) {
                continue;
            }
            children.add(child);
            System.out.println(child.getLevel() + "  " + child.url);
            if (children.size() > 9) {
                break;
            }
        }
        return children;
    }

    public String getValue() {
        String prefix = "";
        for (int i = 0; i < level; i++) {
            prefix = "\t" + prefix;
        }
        return prefix + url;
    }

    private String defineOrigin(String url) {
        origin = url;
        if (origin.startsWith("https://")) {
            origin = origin.substring(8, origin.length());
        }
        if (origin.startsWith("www.")) {
            origin = origin.substring(4, origin.length());
        }
        int index = origin.indexOf("/");
        if (index > 0) {
            origin = origin.substring(0, index);
        }
        return origin;
    }
}
