import java.io.File;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        long sumLength = 0;
        File folder = new File(path);
        File[] files = folder.listFiles();
        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    sumLength += calculateFolderSize(file.getPath());
                } else {
                    sumLength += file.length();
                }
            }
        }
        catch (NullPointerException ex) {
            System.out.println("\tК папке \"" + folder + "\" нет доступа");
        }
        return sumLength;
    }
}
