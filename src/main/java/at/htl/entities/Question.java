package at.htl.entities;


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
@Table(name = "LQ_QUESTION")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Q_ID")
    private Long id;

    @Column(name = "Q_TEXT")
    private String text;

    @Column(name = "Q_SEQ_NO")
    private int seqNumber;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Q_QUESTIONNAIRE_ID")
    private Questionnaire questionnaire;

    private String questype;

    public Question() {

    }

    public Question(String questype, int seqNumber, String text, Questionnaire questionnaire) {
        this.text = text;
        this.seqNumber = seqNumber;
        this.questype = questype;
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

    public String getQ_type() {
        return questype;
    }

    public void setQ_type(String q_type) {
        this.questype = q_type;
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
                ", questiontype=" + questype +
                ", questionnaire=" + questionnaire +
                '}';
    }
}
