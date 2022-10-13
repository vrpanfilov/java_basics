package practice;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска вводимого номера в консоль в каждой из структуры данных
     - проанализировать полученные данные
     */

    public static void main(String[] args) {
        List<String> list = CoolNumbers.generateCoolNumbers();

        System.out.println("Start searching");

        long start = System.nanoTime();
        boolean found = CoolNumbers.bruteForceSearchInList(list, "Н888МВ138");
        long end = System.nanoTime();
        System.out.println("bruteForceSearchInList: " +
                found + "  duration: " + (int)(end - start));

        list.sort(null);
        start = System.nanoTime();
        found = CoolNumbers.binarySearchInList(list, "Н888МВ138");
        end = System.nanoTime();
        System.out.println("binarySearchInList: " +
                found + "  duration: " + (int)(end - start));

        HashSet<String> hashSet = new HashSet<>(list);
        start = System.nanoTime();
        found = CoolNumbers.searchInHashSet(hashSet, "Н888МВ138");
        end = System.nanoTime();
        System.out.println("searchInHashSet: " +
                found + "  duration: " + (int)(end - start));

        TreeSet<String> treeSet = new TreeSet<>(list);
        start = System.nanoTime();
        found = CoolNumbers.searchInTreeSet(treeSet, "Н888МВ138");
        end = System.nanoTime();
        System.out.println("searchInTreeSet: " +
                found + "  duration: " + (int)(end - start));
    }
}
