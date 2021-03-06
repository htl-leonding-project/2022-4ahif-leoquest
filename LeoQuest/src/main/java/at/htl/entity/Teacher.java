package at.htl.entity;

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
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_name")
    private String name;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long t_id) {
        this.id = t_id;
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
                '}';

    }
}
