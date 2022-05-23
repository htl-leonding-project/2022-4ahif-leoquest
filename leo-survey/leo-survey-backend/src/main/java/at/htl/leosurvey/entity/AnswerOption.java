package at.htl.leosurvey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "S_ANSWER_OPTION")
public class AnswerOption extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AO_ID")
    public Long id;

    @Column(name = "AO_TEXT")
    public String text;

    @Column(name = "AO_VALUE")
    public Integer value;

    @Column(name = "AO_SEQUENCE_NUMBER")
    public Integer sequenceNumber;

    @Column(name = "AO_IS_CORRECT_ANSWER")
    public Boolean isCorrectAnswer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "AO_Q_ID")
    public Question question;

    public AnswerOption() {
    }

    public AnswerOption(String text, Integer value, Integer sequenceNumber, Boolean isCorrectAnswer, Question question) {
        this.text = text;
        this.value = value;
        this.sequenceNumber = sequenceNumber;
        this.isCorrectAnswer = isCorrectAnswer;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerOption that = (AnswerOption) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(value, that.value) && Objects.equals(sequenceNumber, that.sequenceNumber) && Objects.equals(isCorrectAnswer, that.isCorrectAnswer) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, value, sequenceNumber, isCorrectAnswer, question);
    }

    @Override
    public String toString() {
        return "AnswerOption{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", value=" + value +
                ", sequenceNumber=" + sequenceNumber +
                ", isCorrectAnswer=" + isCorrectAnswer +
                ", question=" + question +
                '}';
    }
}
