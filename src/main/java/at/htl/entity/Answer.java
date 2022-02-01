package at.htl.entity;

import at.htl.entity.AnswerOption;

import javax.inject.Inject;
import javax.persistence.*;
import java.time.LocalDateTime;
@NamedQueries({

        @NamedQuery(
                name = "Answer.findAll",
                query = "select a from Answer a"
        )

})
@Entity
@Table(name = "LD_ANSWER")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String userName;

    @ManyToOne(cascade = CascadeType.ALL)
    public AnswerOption answerOption;

    public LocalDateTime createDateTime;

    public Answer() {
    }

    public Answer(AnswerOption ao1, String user) {
        this.createDateTime = LocalDateTime.now();

        this.userName = user;
        this.answerOption = ao1;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", answerOption=" + answerOption +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
