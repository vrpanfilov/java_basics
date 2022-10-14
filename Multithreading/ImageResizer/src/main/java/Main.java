import java.io.File;

public class Main {

    public static void main(String[] args) {
//        testImageResizer();
        testImgscalrImageResizer();
    }

    static void testImageResizer() {
        String dataFolder = "data/";
        String srcFolder = dataFolder + "src/";
        String dstFolder = dataFolder + "dst1/";
        int newWidth = 300;
        int coreNumber = Runtime.getRuntime().availableProcessors();

        File dataDir = new File(dataFolder);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        File dstDir = new File(dstFolder);
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        File srcDir = new File(srcFolder);
        if (!srcDir.exists()) {
            srcDir.mkdir();
        }

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        if (files.length == 0) {
            System.out.println("No files in 'Data\\src' folder");
        }

        int beginNext = 0;
        for (int i = 0; i < coreNumber; i++) {
            int begin = beginNext;
            int length = (files.length - begin) /  (coreNumber - i);
            beginNext = begin + length;

            File[] filesPart = new File[length];
            System.arraycopy(files, begin, filesPart, 0, length);
            Thread resizer = new ImageResizer(filesPart, newWidth, dstFolder, start);
            resizer.start();
        }
        System.out.println("coreNumber: " + coreNumber);
    }

    static void testImgscalrImageResizer() {
        String dataFolder = "Data/";
        String srcFolder = dataFolder + "src/";
        String dstFolder = dataFolder + "dst2/";
        int newWidth = 300;
        int coreNumber = 2;

        File dataDir = new File(dataFolder);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        File dstDir = new File(dstFolder);
        if (!dstDir.exists()) {
            dstDir.mkdir();
        }

        File srcDir = new File(srcFolder);
        if (!srcDir.exists()) {
            srcDir.mkdir();
        }

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        if (files.length == 0) {
            System.out.println("No files in 'Data\src' folder");
        }

        int beginNext = 0;
        for (int i = 0; i < coreNumber; i++) {
            int begin = beginNext;
            int length = (files.length - begin) /  (coreNumber - i);
            beginNext = begin + length;

            File[] filesPart = new File[length];
            System.arraycopy(files, begin, filesPart, 0, length);
            ImgscalrImageResizer resizer = new ImgscalrImageResizer(filesPart, newWidth, dstFolder, start);
            resizer.start();
        }
        System.out.println("coreNumber: " + coreNumber);
    }
}
