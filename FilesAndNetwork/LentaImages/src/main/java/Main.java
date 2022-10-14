import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        Document doc = Jsoup.connect("https://lenta.ru").get();
        Elements imgs = doc.select("img[src~=.jp]");
        for (Element img : imgs) {
            String imgAddress = img.attr("abs:src");
            String fileName = readAndStoreImage(imgAddress);
            list.add(fileName);
        }
        list.sort((s1, s2) -> s1.compareTo(s2));
        list.forEach(System.out::println);
    }

    private static String readAndStoreImage(String address)
            throws IOException {
        URL url = new URL(address);
        File dir = new File("images");
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = new File(url.getFile()).getName();
        String relName = dir.getName() + "\\" + fileName;

        try (ReadableByteChannel readableByteChannel =
                     Channels.newChannel(url.openStream());
             FileChannel fileChannel =
                     new FileOutputStream(relName).getChannel()) {
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }
        return fileName;
    }
}