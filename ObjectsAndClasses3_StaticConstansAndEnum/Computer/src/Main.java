import ru.skillbox.*;

public class Main {

    public static void main(String[] args) {
        Computer comp = new Computer("Flextron", "ASUS");
        comp = comp.setProcessor(
                new Processor(3.0, 2, "Intel", 35));
        comp = comp.setRam(
                new Ram(RamType.DDR4_SDRAM, 16.0, 72));
        comp = comp.setStorage(
                new Storage(StorageType.HDD, 596, 620));
        comp = comp.setMonitor(
                new Monitor(24, MonitorType.TN, 3900));
        comp = comp.setKeyboard(
                new Keyboard(KeyboardType.WIRED, Backlight.NO, 250));

        System.out.println(comp +
                "  Total Weight: " + comp.getWeight() + " g");
    }
}
