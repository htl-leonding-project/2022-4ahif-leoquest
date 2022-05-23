package at.htl.leosurvey.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "S_QUESTIONNAIRE")
public class Questionnaire extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QN_ID")
    public Long id;

    @Column(name = "QN_NAME")
    public String name;

    @Column(name = "QN_DESCR")
    public String description;

    @Column(name = "QN_IS_PUBLIC")
    public Boolean isPublic;

    @Column(name = "QN_I_ID")
    public String interviewer;

    //region constructors
    public Questionnaire() {
    }

    public Questionnaire(String name, String description, Boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    public Questionnaire(String name, String description, Boolean isPublic, String interviewer) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        this.interviewer = interviewer;
    }

    //endregion

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", interviewer" + interviewer +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questionnaire that = (Questionnaire) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
