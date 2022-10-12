public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println("container.getCount():" + "\n" +
                container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        int symbFirstUp = 'А';
        int symbLastUp = 'Я';
        int symbFirstDown = 'а';
        int symbLastDown = 'я';
        int symbYoUp = 'Ё';
        int symbYoDown = 'ё';

        System.out.println("\nRussian letters:");
        for (int i = 0; i < Short.MAX_VALUE * 2 + 2; i++) {
            if (i >= symbFirstUp && i <= symbLastUp ||
                    i >= symbFirstDown && i <= symbLastDown ||
                    i == symbYoUp || i == symbYoDown) {
                System.out.println(i + " - " + (char) i);
            }
        }
    }
}
