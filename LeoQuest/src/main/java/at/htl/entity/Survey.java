package at.htl.entity;

import at.htl.entity.Questionnaire;
import at.htl.entity.Teacher;

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
public class Survey {

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

    public Survey(LocalDate date, Teacher teacher, Questionnaire questionnaire) {
        this.date = date;
        this.teacher = teacher;
        this.questionnaire = questionnaire;
    }

    public Survey(Long id, LocalDate date, Teacher teacher, Questionnaire questionnaire) {
        this.id = id;
        this.date = date;
        this.teacher = teacher;
        this.questionnaire = questionnaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long s_id) {
        this.id = s_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate s_date) {
        this.date = s_date;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher s_teacher) {
        this.teacher = s_teacher;
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
                ", teacher=" + teacher +
                ", questionnaire=" + questionnaire +
                '}';
    }
}
