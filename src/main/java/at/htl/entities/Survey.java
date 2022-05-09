package at.htl.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries({

        @NamedQuery(
                name = "Survey.findAll",
                query = "select s from Survey s"
        ),
        @NamedQuery(
                name = "Survey.findQuestion",
                query = "SELECT s FROM Survey s WHERE s.questionnaire.id = :questionnaire_id AND s.teacher.id = :teacher_id"
        ),
        @NamedQuery(
                name = "Survey.findById",
                query = "select s from Survey s where s.id = :id order by s.date"
        )
})
@Entity
@Table(name = "LQ_SURVEY")
public class Survey extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private Long id;

    @Column(name = "s_date")
    private LocalDate date;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "s_teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "s_questionnaire_id")
    private Questionnaire questionnaire;

    public Survey() {
    }

    public Survey(LocalDate date, Questionnaire questionnaire) {
        this.date = date;
        this.questionnaire = questionnaire;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate s_date) {
        this.date = s_date;
    }


    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire s_questionnaire) {
        this.questionnaire = s_questionnaire;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", date=" + date +
                ", questionnaire=" + questionnaire +
                '}';
    }
}
