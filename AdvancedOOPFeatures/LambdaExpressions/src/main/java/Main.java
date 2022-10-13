import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        for (Employee employee : staff) {
            System.out.println(employee);
        }
        sortBySalaryAndAlphabet(staff);
        System.out.println("\nОтсортирован");
        for (Employee employee : staff) {
            System.out.println(employee);
        }
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort((o1, o2) ->
                o1.getSalary().equals(o2.getSalary()) ?
                o1.getName().compareTo(o2.getName()) :
                o1.getSalary().compareTo(o2.getSalary()));
    }
}