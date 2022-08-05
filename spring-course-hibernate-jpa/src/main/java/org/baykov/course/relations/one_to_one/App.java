package org.baykov.course.relations.one_to_one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

//            Person person = new Person("Person with passport", 14);
//            Passport passport = new Passport(349025);
//            person.setPassport(passport);
//            session.save(person);

//            Person person = session.get(Person.class, 7);
//            System.out.println(person.getPassport().getNumber());

//            Passport passport = session.get(Passport.class, 7);
//            System.out.println(passport.getPerson().getName());

//            Person person = session.get(Person.class, 7);
//            person.getPassport().setNumber(777777);

//            Person person = session.get(Person.class, 7);
//            session.remove(person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
