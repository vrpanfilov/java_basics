package ru.skillbox;

public class Ram {
    private final RamType type;
    private final double volume;
    private final int weight;

    public Ram(RamType type, double volume, int weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public RamType getType() {
        return type;
    }

    public double getVolume() {
        return volume;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return  "RAM: " +
                " Type: " + type +
                "; Volume: " + volume + " GB" +
                "; Weight: " + weight + " g\n";
    }
}
