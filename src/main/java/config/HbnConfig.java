package config;

import model.Cat;
import model.Dog;
import model.Pet;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HbnConfig {
    private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test?useSSL=false");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "root");
            props.put("hibernate.show_sql", "true");
            props.put("hibernate.format_sql", "true");
//            props.put("hibernate.hbm2ddl.auto", "create");
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
//            props.put("hibernate.current_session_context_class", "thread");

            configuration.setProperties(props);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Pet.class);
            configuration.addAnnotatedClass(Cat.class);
            configuration.addAnnotatedClass(Dog.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }
}
