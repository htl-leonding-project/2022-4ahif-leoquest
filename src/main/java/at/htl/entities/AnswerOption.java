package at.htl.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "AnswerOption.findAll",
                query = "select a from AnswerOption a"
        )
})
@Entity
@Table(name = "LQ_ANSWER_OPTION")
public class AnswerOption extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ao_id")
    private Long id;
    @Column(name = "ao_text")
    private String text;
    @Column(name = "ao_value")
    private int value;
    @Column(name = "ao_seqNo")
    private int seqNumber;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ao_question_id")
    private Question question;

    private int how_often;

    public AnswerOption() {
    }

    public AnswerOption(String text, int value, int seqNumber, Question question, int how_often) {
        this.text = text;
        this.value = value;
        this.seqNumber = seqNumber;
        this.question = question;
        this.how_often = how_often;
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


    public int getHow_often() {
        return how_often;
    }

    public void setHow_often(int how_often) {
        this.how_often = how_often;
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
