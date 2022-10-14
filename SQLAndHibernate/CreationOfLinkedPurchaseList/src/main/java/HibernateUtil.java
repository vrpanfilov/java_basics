import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface CallbackMethod {
    void process(SessionFactory factory) throws Exception;
}

public class HibernateUtil {
    public static void workWithSessionFactory(String hibernateConfigFile,
                                              CallbackMethod method) {
        try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(hibernateConfigFile).build()) {
            try (SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory()) {
                if (method != null) {
                    method.process(sessionFactory);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void withSessionFactory(String databaseName,
                                          List<Class<?>> classes,
                                          CallbackMethod method,
                                          boolean showSql,
                                          boolean formatSql,
                                          Hbm2DllAuto hbm2DllAuto
    ) throws Exception {
        StandardServiceRegistry registry = null;
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        try {
            //Configuration properties
            Map<String, Object> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/" +
                    databaseName + "?useSSL=false");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "testtest");
            settings.put(Environment.FORMAT_SQL, formatSql);
            settings.put(Environment.HBM2DDL_AUTO, hbm2DllAuto.value());
            settings.put(Environment.SHOW_SQL, showSql);

            registryBuilder.applySettings(settings);
            registry = registryBuilder.build();

            MetadataSources sources = new MetadataSources(registry);
            for (Class<?> cl : classes) {
                sources.addAnnotatedClass(cl);
            }
            Metadata metadata = sources.getMetadataBuilder().build();

            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

            if (method != null) {
                method.process(sessionFactory);
            }
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void withSessionFactory(String databaseName, List<Class<?>> classes,
                                          Hbm2DllAuto hbm2DllAuto) throws Exception {
        withSessionFactory(databaseName, classes, null, true, true, hbm2DllAuto);
    }

    public static void withSessionFactory(String databaseName,
                                          List<Class<?>> classes,
                                          CallbackMethod method) throws Exception {
        withSessionFactory(databaseName, classes, method, false, false, Hbm2DllAuto.VALIDATE);
    }

    public static void withSessionFactory(String databaseName,
                                          List<Class<?>> classes,
                                          CallbackMethod method,
                                          boolean showSql,
                                          boolean formatSql) throws Exception {
        withSessionFactory(databaseName, classes, method, showSql, formatSql, Hbm2DllAuto.VALIDATE);
    }
}
