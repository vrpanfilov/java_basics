import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println("Сотрудники");
        staff.forEach(System.out::println);
        System.out.println("\nМаксимальная зарплата");
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        Optional<Employee> employee = staff.stream()
                .filter(e -> e.getWorkStart().getYear() + 1900 == year)
                .max(Comparator.comparingInt(Employee::getSalary));
        return employee.orElse(null);
    }
}