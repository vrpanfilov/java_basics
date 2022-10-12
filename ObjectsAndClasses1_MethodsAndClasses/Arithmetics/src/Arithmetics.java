public class Arithmetics {
    private int val1;
    private int val2;

    public Arithmetics(int val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public int sum() {
        return val1 + val2;
    }

    public int mult() {
        return val1 * val2;
    }

    public int max() {
        return val1 >= val2 ? val1 : val2;
    }

    public int min() {
        return val1 <= val2 ? val1 : val2;
    }
}
