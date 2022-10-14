import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static String dbName = "skillbox";
    static List<Purchase> purchases;
    static boolean showSql = true;
    static boolean formatSql = false;

    public static void main(String[] args) throws Exception {
        Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        // Удаляем linkedPurchaseList, если она есть
        HibernateUtil.withSessionFactory(dbName,
                Arrays.asList(LinkedPurchase.class, Student.class, Course.class,
                        Teacher.class),
                Main::dropLinkedPurchaseList,
                showSql, formatSql, Hbm2DllAuto.UPDATE
        );

        //  Создаём linkedPurchaseList
        HibernateUtil.withSessionFactory(dbName,
                Arrays.asList(LinkedPurchase.class, Student.class,
                        Course.class, Teacher.class),
                null,
                showSql, formatSql, Hbm2DllAuto.UPDATE
        );

        // Читаем readPerchaseList
        HibernateUtil.withSessionFactory(dbName,
                Arrays.asList(Purchase.class),
                Main::readPerchaseList,
                showSql, formatSql, Hbm2DllAuto.NONE
        );

        // Сохраним fillLinkedPurchaseList в БД,
        // потом извлечём оттуда и распечатаем
        HibernateUtil.withSessionFactory(dbName,
                Arrays.asList(Student.class, Course.class,
                        Teacher.class, LinkedPurchase.class),
                Main::fillLinkedPurchaseList,
                showSql, formatSql, Hbm2DllAuto.UPDATE
        );
    }

    private static void dropLinkedPurchaseList(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "drop table if exists linkedPurchaseList";
        session.createNativeQuery(sql, LinkedPurchase.class).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    private static void readPerchaseList(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT st.id student_id, c.id course_id, pl.price price " +
                "FROM purchaselist pl " +
                "join students st on st.name = pl.student_name " +
                "join courses c on c.name = pl.course_name";
        purchases = session.createNativeQuery(sql, Purchase.class).getResultList();
        session.close();
    }
    private static void fillLinkedPurchaseList(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (Purchase purchase : purchases) {
            LinkedPurchase linkedPurchase = new LinkedPurchase();
            linkedPurchase.setStudentId(purchase.getStudentId());
            linkedPurchase.setCourseId(purchase.getCourseId());
            session.persist(linkedPurchase);
        }

        session.getTransaction().commit();

        printLinkedPurchaseList(session);
    }

    private static void printLinkedPurchaseList(Session session) {
        if (!session.isOpen()) {
            session = session.getSessionFactory().openSession();
        }

        String hql = "from Course";
        List<Course> courses = session.createQuery(hql, Course.class).getResultList();
        for(Course course : courses) {
            System.out.println(course.getId() + " " + course.getName());
            for (Student student : course.getLinkedStudents()) {
                System.out.println("\t" + student.getId() + " " +student.getName());
            }
        }

        session.close();
    }

}
