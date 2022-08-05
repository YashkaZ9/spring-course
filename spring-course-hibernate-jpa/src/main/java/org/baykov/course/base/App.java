package org.baykov.course.base;

import org.baykov.course.base.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person1 = new Person("Name1", 20);
//            Person person2 = new Person("Name2", 40);
//            Person person3 = new Person("Name3", 50);
//
//            session.save(person1);
//            session.save(person2);
//            session.save(person3);

//            Person person = session.get(Person.class, 2);
//            person.setName("NameUpdated");

//            Person person = session.get(Person.class, 2);
//            session.delete(person);

//            Person person = new Person("NameNew", 29);
//            session.save(person);

//            Person person1 = new Person("Alice", 32);
//            Person person2 = new Person("Bob", 18);
//
//            session.save(person1);
//            session.save(person2);

            List<Person> people = session.createQuery("from Person where age > 30 and name like 'Name%'").getResultList();
            for (Person person : people) {
                System.out.println(person);
            }

//            session.createQuery("update Person set name = 'NameUpdate' where age > 30").executeUpdate();
//            session.createQuery("delete from Person where age > 40").executeUpdate();
//            session.createQuery("from Person").stream().forEach(System.out::println);
            session.getTransaction().commit();

//            System.out.println(person.getId());
        } finally {
            sessionFactory.close();
        }
    }
}
