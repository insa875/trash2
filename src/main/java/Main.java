import config.HbnConfig;
import model.Cat;
import model.Dog;
import model.Pet;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        SessionFactory factory = HbnConfig.getSessionFactory();
        Session session = factory.openSession();
        session.getTransaction().begin();

//        Query query = session.createQuery(
//                        "select u " +
//                        "from User u " +
//                        "join fetch u.pets p " +
//                        "where p.name = :name"
//        );
//        query.setParameter("name", "Killer");
//        List list = query.getResultList();
//        printList(list);


//        User user = new User("Mike", 20);


        User user = session.load(User.class, 1);
        System.out.println(user);
        user.getPets().remove(0);
        System.out.println(user);


//        List list = session.createQuery("from User").list();
//        printList(list);


        session.getTransaction().commit();
        session.close();
    }

    public static void printList(List list) {
        for (Object user : list) {
            System.out.println(user);
        }
    }
}
