import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SiteMapCalculator extends RecursiveTask<String> {
    private Node node;
    private static String baseUri;

    public static String getBaseUri() {
        return baseUri;
    }

    public static void setBaseUri(String baseUri) {
        SiteMapCalculator.baseUri = baseUri;
    }

    public SiteMapCalculator(Node node) {
        this.node = node;
    }

    @Override
    protected String compute() {
        String result = node.getValue() + "\n";
        List<SiteMapCalculator> tasks = new ArrayList<>();
        try {
            Collection<Node> children = node.getChildren();
            if (children == null) {
                return result;
            }
            for (Node child : children) {
                if (child.getLevel() >= 4) {
                    continue;
                }
                SiteMapCalculator task = new SiteMapCalculator(child);
                task.fork();
                tasks.add(task);
            }
            for (SiteMapCalculator task : tasks) {
                result += task.join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
