import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
    private List<Employee> employees;
    private int income;

    public Company() {
        employees = new ArrayList<>();
        income = 0;
    }

    public void hire(Employee employee) {
        employee.setCompany(this);
        employees.add(employee);
    }

    public void hireAll(List<Employee> listToHire) {
        for (Employee employee : listToHire) {
            employee.setCompany(this);
        }
        employees.addAll(listToHire);
    }

    public void fire(int index) {
        if (index >= 0 && index < employees.size()) {
            employees.remove(index);
        }
    }

    public void fire(Employee employee) {
        sortEmployees();
        int index = employees.indexOf(employee);
        fire(index);
    }

    public int getIncome() {
        if (income != 0) {
            return income;
        }
        for (Employee employee : employees) {
            income += employee.getIncome();
        }
        return income;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        sortEmployees();
        List<Employee> topList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            topList.add(employees.get(employees.size() - 1 - i));
        }
        return topList;
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        sortEmployees();
        List<Employee> lowestList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lowestList.add(employees.get(i));
        }
        return lowestList;
    }

    public void sortEmployees() {
        Comparator<Employee> comparator = new Comparator<>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Integer.compare(e1.getMonthSalary(), e2.getMonthSalary());
            }
        };
        employees.sort(comparator);
    }

    public void resetIncome() {
        income = 0;
        for (Employee employee : employees) {
            employee.resetIncome();
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
