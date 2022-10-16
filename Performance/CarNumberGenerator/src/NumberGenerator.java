import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NumberGenerator extends Thread {
    public static final String DATA_FOLDER = "res/";
    private char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    private int threadsCount;
    private int instanceNumber;
    private int startRegion;
    private int regionsInThread;
    long start;

    public NumberGenerator(int threadsCount, int instanceNumber,
                           int startRegion, int regionsInThread,
                           long start) {
        super();
        this.threadsCount = threadsCount;
        this.instanceNumber = instanceNumber;
        this.startRegion = startRegion;
        this.regionsInThread = regionsInThread;
        this.start = start;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            String fileName = instanceNumber % 2 != 0 ?
                    DATA_FOLDER + "numbers" :
                    "d:/" + DATA_FOLDER + "numbers";
            writer = new PrintWriter(fileName +
                    String.format("%02d%02d", threadsCount, instanceNumber) +
                    ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int count = 0;
        for (int i = 0; i < regionsInThread; i++) {
            int regionCode = startRegion + i;
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                            count++;
                        }
                    }
                }
            }
            writer.write(builder.toString());
        }

        writer.flush();
        writer.close();

        System.out.println("count: " + count + "  " +
                (System.currentTimeMillis() - start) + " ms");
    }

    public static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
