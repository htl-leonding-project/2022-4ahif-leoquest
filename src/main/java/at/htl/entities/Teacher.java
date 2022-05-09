package at.htl.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Teacher.findAll",
                query = "select t from Teacher t order by t.name"
        ),
        @NamedQuery(
                name = "Teacher.findById",
                query = "select t from Teacher t where t.id = :id order by t.name"
        )
})

@Entity
@Table(name = "LQ_TEACHER")
public class Teacher extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_name")
    private String name;

    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private Survey survey;


    public Teacher() {
    }

    public Teacher(String name, Survey survey) {
        this.name = name;
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String t_name) {
        this.name = t_name;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", survey=" + survey + '\'' +
                '}';

    }
}
