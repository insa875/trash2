import config.HbnConfig;
import model.Cat;
import model.Dog;
import model.Pet;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.*;

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


//        List list = session.createQuery("from User").list();
//        printList(list);


//        Criteria criteria = session.createCriteria(User.class);

//        COUNT OF ROWS
//        criteria.setMaxResults(1);


//        LIKE
//        criteria.add(Restrictions.like("name", "%i%"));


//        BETWEEN
//        criteria.add(Restrictions.between("age", 10, 19));


//        IS EMPTY?
//        criteria.add(Restrictions.isEmpty("pets"));


//        ==
//        criteria.add(Restrictions.eq("name", "Mike"));


//        OR
//        criteria.add(Restrictions.or(
//                Restrictions.like("name", "Mike"),
//                Restrictions.like("name", "%il%")
//        ));


//        LIKE + JOIN
//        criteria.add(Restrictions.like("name", "%i%"));
//        criteria.createCriteria("pets").add(Restrictions.eq("name", "Patzy"));


//        JOIN
//        criteria.createCriteria("pets").add(Restrictions.eq("name", "Patzy"));


//        JOIN?
//        criteria.createAlias("pets", "pt");


//        User user = new User("Mike", 20);
//        criteria.add(Example.create(user));
//        criteria.add(Restrictions.isEmpty("pets"));


//        FROM TO
//        criteria.setFirstResult(5).setMaxResults(10);


//        List list = criteria.list();
//        printList(list);


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> u = criteria.from(User.class); //from User u

        criteria
                .select(u)
                .where(builder.and(
                        builder.equal(u.get("name"), "Mike"),
                        builder.between(u.<Integer>get("age"), 10, 20),
                        builder.isEmpty(u.<List<Pet>>get("pets")).not()
                ));


        List<User> list = session.createQuery(criteria).getResultList();
        printList(list);

        session.getTransaction().commit();
        session.close();
    }

    public static <T> void printList(List<T> list) {
        for (T elem : list) {
            System.err.println(elem);
        }
    }
}
