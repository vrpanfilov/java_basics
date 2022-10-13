package practice;

import java.util.*;

public class CoolNumbers {
    private static final int REGS = 199;
    private static List<String> list = new ArrayList<>();

    public static List<String> generateCoolNumbers() {
        String letters = "АВЕКМНОРСТУХ";
        String[] nices =
                {"111", "222", "333", "444", "555", "666", "777", "888", "999" };
        int length = letters.length()       // Первые буква
                * nices.length              // Красивые сочетания цифр
                * letters.length()          // Вторые буква
                * letters.length()          // Третьи буква
                * REGS;                     // Регионы
        for (int i = 0; i < length; i++) {
            int regsInd = i % REGS;
            int thirdsInd = (i / REGS) % (letters.length());
            int secondsInd = (i / (REGS * letters.length())) % letters.length();
            int nicesInd = (i / (REGS * letters.length() * letters.length()))
                    % nices.length;
            int firstsInd = (i / (REGS * letters.length() * letters.length() * nices.length))
                    % letters.length();

            String str = letters.substring(firstsInd, firstsInd + 1)
                    .concat(nices[nicesInd])
                    .concat(letters.substring(secondsInd, secondsInd + 1))
                    .concat(letters.substring(thirdsInd, thirdsInd + 1));
            int reg = regsInd + 1;
            if (reg < 100) {
                str = str.concat(String.format("%02d", reg));
            } else {
                str = str.concat(String.format("%3d", reg));
            }
            list.add(str);
        }
        return list;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        for (String item : list) {
            if (item.equals(number)) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        int index = Collections.binarySearch(sortedList, number);
        return index >= 0;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
