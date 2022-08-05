package org.baykov.course.relations.task2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Principal.class).addAnnotatedClass(School.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

//            Principal principal = session.get(Principal.class, 2);
//            System.out.println(principal);
//            System.out.println(principal.getSchool());

//            School school = session.get(School.class, 3);
//            System.out.println(school);
//            System.out.println(school.getPrincipal());

//            Principal principal = new Principal("Alan", 45);
//            School school = new School(46);
//            principal.setSchool(school);
//            session.save(principal);

//            School school = session.get(School.class, 3);
//            Principal principal = new Principal("Cecilia", 34);
//            session.save(principal);
//            principal.setSchool(school);

//            Principal principal = session.get(Principal.class, 5);
//            School school = new School(3043);
//            principal.setSchool(school);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
