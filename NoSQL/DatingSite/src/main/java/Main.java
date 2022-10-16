import java.util.Random;

public class Main {
    private final static int GEST_COUNT = 20;

    public static void main(String[] args) throws Exception {
        RadisStorage guests = new RadisStorage();
        guests.init();

        for (int i = 1; i <= GEST_COUNT; i++) {
            String title = new Random().nextInt(3) < 2 ? "Гостья " : "Гость ";
            guests.addGuest(title + Integer.toString(i));
        }

        System.out.println("Всего гостей: " + guests.getGuestCount() + "\n");

        for (int cycle = 1; cycle <= 40; cycle++) {
            if (cycle % 10 == 0) {
                int impatientGuestInd = new Random().nextInt(GEST_COUNT);
                guests.moveToHead(impatientGuestInd);
                String headGuest = guests.getHead();
                System.out.println("> Пользователь \"" + headGuest + "\" оплатил услугу");
            }
            String headGuest = guests.getHead();
            System.out.println("— На главной странице показываем пользователя \"" + headGuest + "\"");
            guests.moveHeadToTail();
            Thread.sleep(1000);
        }

        System.out.println("\nВсего гостей: " + guests.getGuestCount());

        guests.shutdown();
    }
}
