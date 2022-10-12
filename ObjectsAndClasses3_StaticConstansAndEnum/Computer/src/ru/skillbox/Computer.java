package ru.skillbox;

public class Computer {
    private final String vendor;
    private final String name;

    private final Processor processor;
    private final Ram ram;
    private final Storage storage;
    private final Monitor monitor;
    private final Keyboard keyboard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
        processor = null;
        ram = null;
        storage = null;
        monitor = null;
        keyboard = null;
    }

    public Computer(String vendor, String name, Processor processor,
                    Ram ram, Storage storage, Monitor monitor, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.monitor = monitor;
        this.keyboard = keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Computer setProcessor(Processor processor) {
        return new Computer(vendor, name,
                processor, ram, storage, monitor, keyboard);
    }

    public Ram getRam() {
        return ram;
    }

    public Computer setRam(Ram ram) {
        return new Computer(vendor, name,
                processor, ram, storage, monitor, keyboard);
    }

    public Storage getStorage() {
        return storage;
    }

    public Computer setStorage(Storage storage) {
        return new Computer(vendor, name,
                processor, ram, storage, monitor, keyboard);
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Computer setMonitor(Monitor monitor) {
        return new Computer(vendor, name,
                processor, ram, storage, monitor, keyboard);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Computer setKeyboard(Keyboard keyboard) {
        return new Computer(vendor, name,
                processor, ram, storage, monitor, keyboard);
    }

    public int getWeight() {
        return  processor.getWeight() + ram.getWeight() +
                storage.getWeight() + monitor.getWeight() +
                keyboard.getWeight();
    }

    public String toString() {
        return "Computer:  Vendor: " + vendor + "; Name: " + name + "\n" +
                "  " + processor.toString() +
                "  " + ram.toString() +
                "  " + storage.toString() +
                "  " + monitor.toString() +
                "  " + keyboard.toString();
    }
}
