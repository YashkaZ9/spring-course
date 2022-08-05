package org.baykov.course.relations.task2;

import javax.persistence.*;

@Entity
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "principal_id", referencedColumnName = "id")
    private Principal principal;

    @Column(name = "school_number")
    private int schoolNumber;

    public School() {}

    public School(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public int getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(int schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", schoolNumber='" + schoolNumber + '\'' +
                '}';
    }
}
