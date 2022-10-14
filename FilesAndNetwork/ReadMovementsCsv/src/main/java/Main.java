import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите путь до исходной папки:");

        Scanner scanner = new Scanner(System.in);
        String srcFolder = scanner.nextLine();
        File file = new File(srcFolder);
        if (!file.exists()) {
            System.out.println("Такой папки не существует");
            System.exit(150);
        }
        if (!file.isDirectory()) {
            System.out.println("Это не папка");
            System.exit(150);
        }

        System.out.println("Введите путь до папки назначения:");
        String destFolder = scanner.nextLine();
        file = new File(destFolder);
        if (file.exists()) {
            System.out.println("Объект с таким именем уже существует");
            System.exit(150);
        }
        try {
            Files.createDirectory(Path.of(destFolder));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(150);
        }

        try {
            FileUtils.copyFolder(srcFolder, destFolder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
