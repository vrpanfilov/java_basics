import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory)
            throws IOException {
        File sFolder = new File(sourceDirectory);
        File[] files = sFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                String dPath = destinationDirectory + "/" + file.getName();
                Files.createDirectory(Path.of(dPath));
                copyFolder(file.getPath(), dPath);
            } else {
                copyFile(file, destinationDirectory);
            }
        }
    }

    private static void copyFile(File sourceFile, String destinationDirectory)
            throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            String sName = sourceFile.getName();
            String sPath = sourceFile.getPath();
            fis = new FileInputStream(sPath);
            byte[] buff = fis.readAllBytes();
            String dPath = destinationDirectory + "/" + sName;
            fos = new FileOutputStream(dPath);
            fos.write(buff);
        } finally {
            fis.close();
            fos.close();
        }
    }
}