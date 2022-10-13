package practice.reverseArray;

public class ReverseArray {

    public static String[] reverse(String[] strings) {
        //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.

        for (int i = 0; i < strings.length / 2; i++) {
            String temp = strings[strings.length - 1 - i];
            strings[strings.length - 1 - i] = strings[i];
            strings[i] = temp;
        }
        return strings;
    }

}