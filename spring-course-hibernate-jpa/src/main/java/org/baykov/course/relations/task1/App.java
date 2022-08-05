package org.baykov.course.relations.task1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

//            Director director = session.get(Director.class, 3);
//            System.out.println(director);
//            System.out.println(director.getMovies());

//            Movie movie = session.get(Movie.class, 5);
//            System.out.println(movie);
//            System.out.println(movie.getDirector());

//            Director director = session.get(Director.class, 5);
//            Movie movie = new Movie("New movie", 2022, director);
//            session.save(movie);
//            director.getMovies().add(movie);

//            Director director = new Director("New director", 54);
//            Movie movie = new Movie("Interesting movie", 2349, director);
//            session.save(director);
//            session.save(movie);

//            Movie movie = session.get(Movie.class, 12);
//            Director director = session.get(Director.class, 3);
//            movie.getDirector().getMovies().remove(movie);
//            movie.setDirector(director);
//            director.getMovies().add(movie);

//            Director director = session.get(Director.class, 7);
//            Movie movie = director.getMovies().get(0);
//            session.remove(movie);
//            director.getMovies().remove(movie);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
