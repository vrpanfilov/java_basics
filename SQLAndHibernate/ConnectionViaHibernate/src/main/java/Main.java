import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build()) {
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build()) {
                Session session = sessionFactory.openSession();

                int id = 1;
                Course course;
                while ((course = session.get(Course.class, id++)) != null) {
                    System.out.printf("%-36s%4d\n", course.getName(), course.getStudentCount());
                }
            }
        }
    }
}
