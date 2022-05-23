package at.htl.leosurvey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "S_INTERVIEWER")
public class Interviewer extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_ID")
    public Long id;

    @Column(name = "I_NAME")
    public String name;

    public Interviewer() {
    }

    public Interviewer(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interviewer that = (Interviewer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Interviewer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
