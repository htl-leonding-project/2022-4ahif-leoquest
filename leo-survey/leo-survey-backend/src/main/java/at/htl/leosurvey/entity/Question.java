package at.htl.leosurvey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.io.File;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "S_QUESTION")
public class Question extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Q_ID")
    public Long id;

    @Column(name = "Q_TEXT")
    public String text;

    //@Column(name = "Q_IMAGE")
    //public byte[] image;
    //public File image;

    @Column(name = "Q_SEQUENCE_NUMBER")
    public Integer sequenceNumber;

    @Column(name = "Q_TYPE")
    public QuestionType questiontype;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "Q_QN_ID")
    public Questionnaire questionnaire;

    public Question() {
    }

    public Question(String text, Integer sequenceNumber, QuestionType type, Questionnaire questionnaire) {
        this.text = text;
        this.sequenceNumber = sequenceNumber;
        this.questiontype = type;
        this.questionnaire = questionnaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(text, question.text) && Objects.equals(sequenceNumber, question.sequenceNumber) && questiontype == question.questiontype && Objects.equals(questionnaire, question.questionnaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, sequenceNumber, questiontype, questionnaire);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", type=" + questiontype +
                ", questionnaire=" + questionnaire +
                '}';
    }
}
