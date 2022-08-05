package org.baykov.course.relations.task1;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_production")
    private int creationYear;

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "director_id")
    private Director director;

    public Movie() {}

    public Movie(String name, int creationYear) {
        this(name, creationYear, null);
    }

    public Movie(String name, int creationYear, Director director) {
        this.name = name;
        this.creationYear = creationYear;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationYear=" + creationYear +
                '}';
    }
}
