package ru.skillbox;

public class Storage {
    private final StorageType type;
    private final int volume;
    private final int weight;

    public Storage(StorageType type, int volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public StorageType getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "Storage: " +
                " Type: " + type +
                "; Volume: " + volume + " GB" +
                "; Weight: " + weight + " g\n";
    }
}
