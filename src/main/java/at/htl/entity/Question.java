package at.htl.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Question.findAll",
                query = "select q from Question q"
        ),
        @NamedQuery(
                name = "Question.findById",
                query = "select q from Question q where q.id = :id order by q.text"
        )
})
@Entity
@Table(name = "LD_QUESTION")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Q_ID")
    private Long id;

    @Column(name = "Q_TEXT")
    private String text;

    @Column(name = "Q_SEQ_NUMBER")
    private int seqNumber;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Q_TYPE")
    private QuestionType type;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Q_QUESTIONNAIRE")
    private Questionnaire questionnaire;

    public Question() {

    }

    public Question(String text, int seqNumber, QuestionType type, Questionnaire questionnaire) {
        this.text = text;
        this.seqNumber = seqNumber;
        this.type = type;
        this.questionnaire = questionnaire;
    }

    public Question(Long id, String text, int seqNumber, QuestionType type, Questionnaire questionnaire) {
        this.id = id;
        this.text = text;
        this.seqNumber = seqNumber;
        this.type = type;
        this.questionnaire = questionnaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long q_id) {
        this.id = q_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String q_text) {
        this.text = q_text;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int q_sequenceNumber) {
        this.seqNumber = q_sequenceNumber;
    }

/*    public String getType(Long id) {
        return type.getMap().get(id);
    }*/

    public void setType(QuestionType q_type) {
        this.type = q_type;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire q_questionnaire) {
        this.questionnaire = q_questionnaire;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", sequenceNumber=" + seqNumber +
                ", type=" + type.name +
                ", questionnaire=" + questionnaire +
                '}';
    }
}
