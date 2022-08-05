package org.baykov.course.relations.many_to_many;

import javax.persistence.*;
import java.util.List;

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
    private int productionYear;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    public Movie() {}

    public Movie(String name, int productionYear) {
        this.name = name;
        this.productionYear = productionYear;
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

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getId() != movie.getId()) return false;
        if (getProductionYear() != movie.getProductionYear()) return false;
        return getName().equals(movie.getName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getProductionYear();
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }
}
