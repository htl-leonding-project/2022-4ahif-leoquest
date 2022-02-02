package at.htl.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "AnswerOption.findAll",
                query = "select a from AnswerOption a"
        )
})
@Entity
@Table(name = "AnswerOption")
public class AnswerOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ao_id")
    private Long id;
    @Column(name = "ao_text")
    private String text;
    @Column(name = "ao_value")
    private int value;
    @Column(name = "ao_seqNumber")
    private int seqNumber;
    @ManyToOne
    @JoinColumn(name = "ao_question")
    private Question question;

    public AnswerOption() {
    }

    public AnswerOption(String text, int value, int seqNumber, Question question) {
        this.text = text;
        this.value = value;
        this.seqNumber = seqNumber;
        this.question = question;
    }

    public AnswerOption(Long id, String text, int value, int seqNumber, Question question) {
        this.id = id;
        this.text = text;
        this.value = value;
        this.seqNumber = seqNumber;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long ao_id) {
        this.id = ao_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String ao_text) {
        this.text = ao_text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int ao_value) {
        this.value = ao_value;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int ao_sequenceNumber) {
        this.seqNumber = ao_sequenceNumber;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question ao_question) {
        this.question = ao_question;
    }

    @Override
    public String toString() {
        return "AnswerOption{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", value=" + value +
                ", sequenceNumber=" + seqNumber +
                ", question=" + question +
                '}';
    }
}
