package ru.skillbox;

public class ArithmeticCalculator {
    private int var1;
    private int var2;

    public int getVar1() {
        return var1;
    }

    public int getVar2() {
        return var2;
    }

    public ArithmeticCalculator(int var1, int var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public int calculate(Operation operation) {
        switch (operation) {
            case ADD -> {
                return var1 + var2;
            }
            case SUBSTRACT -> {
                return var1 - var2;
            }
            case MULTIPLY -> {
                return var1 * var2;
            }
            default -> { return 0; }
        }
    }
}
