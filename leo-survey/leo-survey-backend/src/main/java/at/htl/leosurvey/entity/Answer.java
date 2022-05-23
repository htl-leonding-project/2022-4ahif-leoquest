package at.htl.leosurvey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "S_ANSWER")
public class Answer extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_ID")
    public Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "A_Q_ID")
    public Question question;

    @Column(name = "A_ANSWER_TEXT")
    public String answerText;
    
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "A_TR_ID")
    public Transaction transaction;

    public Answer() {
    }

    public Answer(Question question, String answerText, Transaction transaction) {
        this.question = question;
        this.answerText = answerText;
        this.transaction = transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) && Objects.equals(question, answer.question) && Objects.equals(answerText, answer.answerText) && Objects.equals(transaction, answer.transaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answerText, transaction);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", question=" + question +
                ", answerText='" + answerText + '\'' +
                ", transaction=" + transaction +
                '}';
    }
}
