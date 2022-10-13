import java.util.Random;

public class Manager extends Operator {
    private static double bonus = 0.05;
    private static Random random = new Random();

    public Manager(int fixedSalary) {
        super(fixedSalary);
    }

    @Override
    public int getMonthSalary() {
        return (int) (fixedSalary + bonus * getIncome());
    }

    @Override
    public int getIncome() {
        if (income == 0) {
            int lowBound = 115_000;
            int highBound = 140_000;
            income = (int) (lowBound + (highBound - lowBound) * random.nextDouble());
        }
        return income;
    }
}
