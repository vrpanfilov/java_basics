public class Operator implements Employee {
    protected Company company;
    protected int fixedSalary;
    protected int income;

    public Operator(int fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    @Override
    public int getMonthSalary() {
        return fixedSalary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int getIncome() {
        return 0;
    }

    @Override
    public void resetIncome() {
        income = 0;
    }

    public int getFixedSalary() {
        return fixedSalary;
    }
}
