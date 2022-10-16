import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int age;
    private List<String> courses;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getCourses() {
        return courses;
    }

    public static Student init(String csvRow) {
        String[] parts = csvRow.split("\"");
        String[] nameAge = parts[0].split(",");
        String[] courses = parts[1].split(",");

        Student student = new Student();
        student.name = nameAge[0];
        student.age = Integer.parseInt(nameAge[1]);
        student.courses = new ArrayList<>();
        for (String course : courses) {
            student.courses.add(course);
        }
        return student;
    }
}
