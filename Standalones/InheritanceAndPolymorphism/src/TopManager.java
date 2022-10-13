public class TopManager extends Manager {
    private static double bonus = 1.5;
    private static int companyIncomeBound = 10_000_000;

    public TopManager(int fixedSalary) {
        super(fixedSalary);
    }

    @Override
    public int getMonthSalary() {
        if (company.getIncome() >= companyIncomeBound) {
            return (int) (fixedSalary * (1.0 + bonus));
        } else {
            return fixedSalary;
        }
    }
}
