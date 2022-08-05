//package org.baykov.springbasecourse.dao;
//
//import org.baykov.springbasecourse.model.Person;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class PersonDAO {
////    private final JdbcTemplate jdbcTemplate;
//    private static int peopleCount;
//
//    private final SessionFactory sessionFactory;
//
////    @Autowired
////    public PersonDAO(JdbcTemplate jdbcTemplate) {
////        this.jdbcTemplate = jdbcTemplate;
////    }
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> getPeople() {
////        List<Person> res = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
////        peopleCount = res.size();
////        return res;
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select p from Person p", Person.class).getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Person getPerson(int id) {
////        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
////                .stream().findAny().orElse(null);
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//
//    @Transactional(readOnly = true)
//    public Person getPerson(String email) {
////        return jdbcTemplate.query("SELECT * FROM person WHERE email = ?", new Object[]{email},
////                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select p from Person p where p.email = :email", Person.class)
//                .setParameter("email", email).stream().findAny().orElse(null);
//    }
//
//    @Transactional
//    public void save(Person person) {
////        jdbcTemplate.update("INSERT INTO person(name, age, email, address) VALUES (?, ?, ?, ?)",
////                person.getName(), person.getAge(), person.getEmail(), person.getAddress());
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(int id, Person person) {
////        jdbcTemplate.update("UPDATE person SET name = ?, age = ?, email = ?, address = ? WHERE id = ?",
////                person.getName(), person.getAge(), person.getEmail(), person.getAddress(), id);
//        Session session = sessionFactory.getCurrentSession();
//        Person updatedPerson = session.get(Person.class, id);
//        updatedPerson.setName(person.getName());
//        updatedPerson.setAge(person.getAge());
//        updatedPerson.setEmail(person.getEmail());
//        updatedPerson.setAddress(person.getAddress());
//    }
//
//    @Transactional
//    public void delete(int id) {
////        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, id));
//    }
//
////    public List<Person> create1000People() {
////        List<Person> people = new ArrayList<>();
////        for (int i = 0; i < 1000; ++i) {
////            people.add(new Person(i, "name" + i, new Random().nextInt(100), "email" + i + "@test.com", "address " + i));
////        }
////        return people;
////    }
////
////    public void testBatchUpdate() {
////        List<Person> people = create1000People();
////        long start = System.currentTimeMillis();
////        jdbcTemplate.batchUpdate("INSERT INTO person VALUES(?, ?, ?, ?)", new BatchPreparedStatementSetter() {
////            @Override
////            public void setValues(PreparedStatement ps, int i) throws SQLException {
////                ps.setInt(1, people.get(i).getId());
////                ps.setString(2, people.get(i).getName());
////                ps.setInt(3, people.get(i).getAge());
////                ps.setString(4, people.get(i).getEmail());
////            }
////
////            @Override
////            public int getBatchSize() {
////                return people.size();
////            }
////        });
////        long finish = System.currentTimeMillis();
////        System.out.println("With batch: " + (finish - start) + " ms");
////    }
////
////    public void testMultipleUpdate() {
////        List<Person> people = create1000People();
////        long start = System.currentTimeMillis();
////        for (Person person : people) {
////            jdbcTemplate.update("INSERT INTO person VALUES (?, ?, ?, ?)",
////                    person.getId(), person.getName(), person.getAge(), person.getEmail());
////        }
////        long finish = System.currentTimeMillis();
////        System.out.println("Without batch: " + (finish - start) + " ms");
////    }
//}

package org.baykov.springbasecourse.dao;

import org.baykov.springbasecourse.model.Item;
import org.baykov.springbasecourse.model.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Component
public class PersonDAO {
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);
//        //1 query
//        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//        //N queries
//        for (Person person : people) {
//            System.out.println(person.getName() + ": " + person.getItems());
//        }
        //Solution (A LEFT JOIN B -> AB)

        Set<Person> people = new HashSet<>(session.createQuery("select p from Person p left join fetch p.items", Person.class)
                .getResultList());
        for (Person person : people) {
            System.out.println(person.getName() + ": " + person.getItems());
        }
    }

    @Transactional
    public void testGetVsLoad() {
        Session session = entityManager.unwrap(Session.class);
        Item item = new Item("Test item");
        Person personProxy = session.load(Person.class, 13);
        System.out.println("Loaded person: " + personProxy);
        System.out.println(personProxy.getMood());
        System.out.println("Person after get: " + personProxy);
        item.setOwner(personProxy);
        personProxy.getItems().add(item);
        session.save(item);
    }
}
