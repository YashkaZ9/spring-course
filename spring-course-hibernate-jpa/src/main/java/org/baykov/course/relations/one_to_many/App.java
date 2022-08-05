package org.baykov.course.relations.one_to_many;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class).addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

//            User user = session.get(User.class, 3);
//            System.out.println(user);
//
//            List<Item> items = user.getItems();
//            System.out.println(items);

//            Item item = session.get(Item.class, 5);
//            System.out.println(item);
//
//            User owner = item.getOwner();
//            System.out.println(owner);

//            User user = session.get(User.class, 2);
//            Item item = new Item("New item", user);
//            user.getItems().add(item);
//            session.save(item);

//            User user = new User("Test user", 34);
//            Item item = new Item("New item", user);
//            user.setItems(new ArrayList<Item>(Collections.singletonList(item)));
//            session.save(user);
//            session.save(item);

//            User user = session.get(User.class, 4);
//            session.remove(user);
//            user.getItems().forEach(item -> item.setOwner(null));

//            User user = session.get(User.class, 4);
//            List<Item> items = user.getItems();
//            for (Item item : items) {
//                session.remove(item);
//            }
//            user.getItems().clear();

//            User user = session.get(User.class, 1);
//            Item item = session.get(Item.class, 5);
//            item.getOwner().getItems().remove(item);
//            item.setOwner(user);
//            user.getItems().add(item);

//            User user = new User("User", 34);
//            Item item = new Item("Item", user);
//            user.setItems(new ArrayList<>(Collections.singletonList(item)));
//            session.save(user);

//            User user = session.get(User.class, 7);
//            user.addItem(new Item("Item 1"));
//            user.addItem(new Item("Item 2"));
//            user.addItem(new Item("Item 3"));
//            session.save(user);

            User user = session.get(User.class, 1);
//            Hibernate.initialize(user.getItems());

            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            user = (User) session.merge(user);
//            Hibernate.initialize(user.getItems());
            List<Item> items = session.createQuery("select i from Item i where i.owner.id = :userId", Item.class)
                    .setParameter("userId", user.getId()).getResultList();
            System.out.println(items);

            session.getTransaction().commit();

//            System.out.println(user.getItems());
        } finally {
            sessionFactory.close();
        }
    }
}
