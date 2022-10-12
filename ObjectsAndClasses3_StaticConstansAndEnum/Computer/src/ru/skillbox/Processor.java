package ru.skillbox;

public class Processor {
    private final double frequency;
    private final int coreNumber;
    private final String vendor;
    private final int weight;

    public Processor(double frequency, int coreNumber, String vendor, int weight) {
        this.frequency = frequency;
        this.coreNumber = coreNumber;
        this.vendor = vendor;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCoreNumber() {
        return coreNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "Processor: " +
                " Frequency: " + frequency + " MHz" +
                "; Core Number: " + coreNumber +
                "; Vendor: " + vendor +
                "; Weight: " + weight + " g\n";
    }
}
