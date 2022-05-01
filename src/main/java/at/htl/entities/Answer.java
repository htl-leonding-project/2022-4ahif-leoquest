package at.htl.entities;

import javax.persistence.*;

@NamedQueries({

        @NamedQuery(
                name = "Answer.findAll",
                query = "select a from Answer a"
        ),
        @NamedQuery(
                name = "Answer.findById",
                query = "select a from Answer a where a.id = :id order by a.text"
        )

})
@Entity
@Table(name = "LQ_ANSWER")
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Long id;

    @Column(name = "a_text")
    private String text;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "a_question_id")
    private Question question;

    public Answer() {
    }

    public Answer(String text, Question question) {
        this.text = text;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long a_id) {
        this.id = a_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String a_text) {
        this.text = a_text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question a_question) {
        this.question = a_question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", question=" + question +
                '}';
    }
}
