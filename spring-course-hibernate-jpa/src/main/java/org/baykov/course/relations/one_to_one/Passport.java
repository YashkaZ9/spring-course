package org.baykov.course.relations.one_to_one;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "passport")
public class Passport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "number")
    private int number;

    public Passport() {}

    public Passport(int number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "number='" + number + '\'' +
                '}';
    }
}

