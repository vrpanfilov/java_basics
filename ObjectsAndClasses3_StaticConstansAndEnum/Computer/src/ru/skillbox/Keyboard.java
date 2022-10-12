package ru.skillbox;

public class Keyboard {
    private final KeyboardType type;
    private final Backlight backlight;
    private final int weight;

    public Keyboard(KeyboardType type, Backlight backlight, int weight) {
        this.type = type;
        this.backlight = backlight;
        this.weight = weight;
    }

    public KeyboardType getType() {
        return type;
    }

    public Backlight getBacklight() {
        return backlight;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return  "Keyboard: " +
                " Type: " + type +
                "; Backlight: " + backlight +
                "; Weight: " + weight + " g\n";
    }
}
