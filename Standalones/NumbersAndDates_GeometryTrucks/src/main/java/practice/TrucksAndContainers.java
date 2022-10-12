package practice;

import java.util.Scanner;

public class TrucksAndContainers {

    static final int MAX_CONTAINERS = 12;
    static final int MAX_BOXES = 27;
    static final int MAX_BOXES_IN_TRUCK = MAX_BOXES * MAX_CONTAINERS;
    static final String LS = System.lineSeparator();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //получение количество коробок от пользователя
        int boxes = scanner.nextInt();

        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */

        int boxesInit = boxes;
        int truck = 1;
        int truckPrev = 0;
        int container = 0;
        int containerPrev = 0;
        int box = 0;

        while (boxes-- > 0) {
            if (box % MAX_BOXES == 0) {
                if (container == MAX_CONTAINERS) {
                    truck++;
                }
                container++;
            }
            box++;
            if (truck != truckPrev) {
                System.out.print("Грузовик: " + truck + LS);
                truckPrev = truck;
            }
            if (container != containerPrev) {
                System.out.print("\tКонтейнер: " + container + LS);
                containerPrev = container;
            }
            System.out.print("\t\tЯщик: " + box + LS);
        }
        System.out.print("Необходимо:" + LS);
        System.out.print("грузовиков - " +
                (int) (boxesInit / MAX_BOXES_IN_TRUCK +
                        (boxesInit % MAX_BOXES_IN_TRUCK == 0 ? 0 : 1)) +
                " шт." + LS);
        System.out.print("контейнеров - " +
                (int) (boxesInit / MAX_BOXES +
                        (boxesInit % MAX_BOXES == 0 ? 0 : 1)) +
                " шт." + LS);
    }

}
