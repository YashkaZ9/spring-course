package org.baykov.course.relations.many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Samuel L. Jackson", 73);
//            Actor actor2 = new Actor("Harvey Keitel", 82);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

//            Movie movie = session.get(Movie.class, 17);
//            System.out.println(movie.getActors());

//            Movie movie = new Movie("Reservoir dogs", 1992);
//            Actor actor = session.get(Actor.class, 2);
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
//            actor.getMovies().add(movie);
//            session.save(movie);

//            Actor actor = session.get(Actor.class, 1);
//            Movie movieToRemove = actor.getMovies().get(0);
//            actor.getMovies().remove(0);
//            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();
        }
    }
}
