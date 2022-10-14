import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("org.hibernate");
        logger.setLevel(Level.INFO);
        try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build()) {
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()) {
                Session session = sessionFactory.openSession();

                Course course = session.get(Course.class, 1);
                System.out.println("\nКурс: " + course.getName() +
                        "\nПреподаватель: " + course.getTeacher().getName());

                System.out.println("Студенты:");
                List<Student> students = course.getStudents();
                for (Student student : students) {
                    System.out.println(student.getName());
                }

                Subscription subscription = session.get(Subscription.class,
                        new Subscription.PK(1, 10));
                String studentName = session.get(Student.class, 1).getName();
                String courseName = session.get(Course.class, 10).getName();
                String dateStr = new SimpleDateFormat(
                        "dd MMMMM yyyy", Locale.getDefault())
                        .format(subscription.getSubscriptionDate());
                System.out.println("\nДата записи студента " + studentName +
                        " на курс " + courseName + ": " +
                        dateStr + "\n");
            }
        }
    }
}
