import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Main {
    private static Company company;

    public static void main(String[] args) {
        company = new Company();

        createTopManagers();
        createManagers();
        createOperators();
        company.sortEmployees();

        printTopSalaryStaff(15);
        printLowestSalaryStaff(30);

        fire50percentOfEmployees();

        System.out.println("\nУволили 50% сотрудников\n");

        // Теперь новые заработанные для компании деньги
        company.resetIncome();

        company.sortEmployees();
        printTopSalaryStaff(15);
        printLowestSalaryStaff(30);
    }

    private static void createTopManagers() {
        for (int i = 0; i < 10; i++) {
            TopManager topManager =
                    new TopManager(80_000 + i * 5_000);
            company.hire(topManager);
        }
    }

    private static void createManagers() {
        Random random = new Random();
        Manager manager = new Manager(86000);
        company.hire(manager);
        for (int i = 1; i < 80; i++) {
            int delta = random.nextInt(21000) - 10000;
            manager = new Manager(40000 + delta);
            company.hire(manager);
        }
    }

    private static void createOperators() {
        ArrayList<Employee> operators = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 180; i++) {
            int delta = random.nextInt(11000) - 5000;
            operators.add(new Operator(20000 + delta));
        }
        company.hireAll(operators);
    }

    private static void printTopSalaryStaff(int count) {
        if (count < 0 || count > company.getEmployees().size()) {
            System.out.println(
                    "Неверный параметр функции printTopSalaryStaff()");
            return;
        }
        System.out.println("Список самых высоких зарплат");
        List<Employee> employees = company.getTopSalaryStaff(count);
        for (int i = 0; i < count; i++) {
            System.out.println(stringToPrint(i + 1, employees.get(i)));
        }
    }

    private static void printLowestSalaryStaff(int count) {
        if (count < 0 || count > company.getEmployees().size()) {
            System.out.println(
                    "Неверный параметр функции printLowestSalaryStaff()");
            return;
        }
        System.out.println("Список самых низких зарплат");
        List<Employee> employees = company.getLowestSalaryStaff(count);
        for (int i = 0; i < count; i++) {
            System.out.println(stringToPrint(i + 1, employees.get(i)));
        }
    }

    private static String stringToPrint(int number, Employee employee) {
        int monthSalary = employee.getMonthSalary();
        String className = employee.getClass().getName();
        return String.format(Locale.FRANCE, "\t%2d. %,7d руб.  %s",
                number, monthSalary, className);
    }

    private static void fire50percentOfEmployees() {
        company.sortEmployees();
        int i = company.getEmployees().size() - 2;
        while (i >= 0) {
            company.fire(i);
            i = i - 2;
        }
    }
}
