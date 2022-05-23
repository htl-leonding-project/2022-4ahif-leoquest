package at.htl.leosurvey.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "S_CHOSEN_OPTION")
public class ChosenOption extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_ID")
    public Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "CO_AO_ID")
    public AnswerOption answerOption;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "CO_A_ID")
    public Answer answer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "CO_Q_ID")
    public Question question;

    public ChosenOption() {
    }

    public ChosenOption(AnswerOption answerOption, Answer answer, Question question) {
        this.answerOption = answerOption;
        this.answer = answer;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChosenOption that = (ChosenOption) o;
        return Objects.equals(id, that.id) && Objects.equals(answerOption, that.answerOption) && Objects.equals(answer, that.answer) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerOption, answer, question);
    }

    @Override
    public String toString() {
        return "ChosenOption{" +
                "id=" + id +
                ", answerOption=" + answerOption +
                ", answer=" + answer +
                ", question=" + question +
                '}';
    }
}
