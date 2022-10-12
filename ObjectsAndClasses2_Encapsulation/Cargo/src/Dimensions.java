public class Dimensions {
    private final int length;
    private final int width;
    private final int height;

    public Dimensions(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public Dimensions setLength(int length) {
        return new Dimensions(length, width, height);
    }

    public int getWidth() {
        return width;
    }

    public Dimensions setWidth(int width) {
        return new Dimensions(length, width, height);
    }

    public int getHeight() {
        return height;
    }

    public Dimensions setHeight(int height) {
        return new Dimensions(length, width, height);
    }

    public int getVolume() {
        return length * width * height;
    }

    public String toString() {
        return "length: " + length +
                " width: " + width +
                " height: " + height +
                " volume: " + getVolume() + "\n";
    }
}
