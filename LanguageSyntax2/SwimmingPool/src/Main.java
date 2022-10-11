public class Main {
    public static void main(String[] args) {
        int volume = 1200;
        int fillingSpeed = 30;      // 30 liters per minute
        int devastationSpeed = 10;  // 10 liters per minute

        int currentVolume = 0;
        int duration = 0;

        while (currentVolume < volume) {
            currentVolume = currentVolume + fillingSpeed - devastationSpeed;
            duration = duration + 1;
        }

        System.out.println("Время заполнения бассейна: " + duration + " мин");
    }
}
