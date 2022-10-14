import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        for (;;) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите путь до папки:");
            String path = scanner.nextLine();

            if (path.equals("q")) {
                break;
            }

            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Такой папки не существует");
                continue;
            }

            if (!file.isDirectory()) {
                System.out.println("Это не папка");
                continue;
            }

            long length = FileUtils.calculateFolderSize(path);
            String fl = getFormattedLength(length);

            System.out.println("Размер папки \"" + path + "\" составляет " + fl);
        }
    }

    private static String getFormattedLength(long length) {
        final long  KB = 1024;
        final long  MB = KB * KB;
        final long  GB = MB * KB;

        if (length < KB) {
            return String.format("%d байт", length);
        }
        if (length < 10 * KB) {
            return String.format("%1.2f KB", (double)length / KB);
        }
        if (length < 100 * KB) {
            return String.format("%1.1f KB", (double)length / KB);
        }
        if (length < MB) {
            return String.format("%d KB", (int)((double)length / KB));
        }
        if (length < 10 * MB) {
            return String.format("%1.2f MB", (double)length / MB);
        }
        if (length < 100 * MB) {
            return String.format("%1.1f MB", (double)length / MB);
        }
        if (length < GB) {
            return String.format("%d MB", (int)((double)length / MB));
        }
        if (length < 10 * GB) {
            return String.format("%1.2f GB", (double)length / GB);
        }
        if (length < 100 * GB) {
            return String.format("%1.1f GB", (double)length / GB);
        }
        return String.format("%d GB", (int)((double)length / GB));
    }
}
