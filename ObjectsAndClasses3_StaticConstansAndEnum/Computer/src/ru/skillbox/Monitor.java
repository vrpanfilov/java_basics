package ru.skillbox;

public class Monitor {
    private final double diagonal;
    private final MonitorType type;
    private final int weight;

    public Monitor(double diagonal, MonitorType type, int weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public MonitorType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return  "Monitor: " +
                " Diagonal: " + diagonal + " inches" +
                "; Type: " + type +
                "; Weight: " + weight + " g\n";
    }
}
