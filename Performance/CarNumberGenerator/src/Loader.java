import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static void main(String[] args) throws InterruptedException {
//        int[] threadCounts = new int[]{1, 2, 4, 8, 16};
        File dataDir = new File(NumberGenerator.DATA_FOLDER);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        int[] threadCounts = new int[]{1};
        for (int threadCount : threadCounts) {
            int startRegion = 1;
            int endRegion = 99;
            int regionsInThread = endRegion / threadCount + 1;
            List<Thread> threads = new ArrayList<>();
            long start = System.currentTimeMillis();
            for (int threadNumber = 1; threadNumber <= threadCount; threadNumber++) {
                if (startRegion > endRegion) {
                    break;
                }
                if (startRegion + regionsInThread > endRegion) {
                    regionsInThread = endRegion - startRegion + 1;
                }
                NumberGenerator generator = new NumberGenerator(
                        threadCount, threadNumber, startRegion, regionsInThread, start);
                threads.add(generator);
                generator.start();
                startRegion += regionsInThread;
            }
            for (Thread thread : threads) {
                thread.join();
            }
        }
    }
}
