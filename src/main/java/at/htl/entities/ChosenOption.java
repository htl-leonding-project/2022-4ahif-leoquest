package at.htl.entities;


import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "ChosenOption.findAll",
                query = "select co from ChosenOption co"
        )
})
@Entity
@Table(name = "LQ_CHOSEN_OPTION")
public class ChosenOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "co_answerOption_id")
    private AnswerOption answerOption;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "co_answer_id")
    private Answer answer;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "co_question_id")
    private Question question;

    private String transaction_code;

    public ChosenOption() {
    }

    public ChosenOption(AnswerOption co_ao, Answer co_a, Question co_q, String transaction_code) {
        this.answerOption = co_ao;
        this.answer = co_a;
        this.question = co_q;
        this.transaction_code = transaction_code;
    }

    public Long getId() {
        return id;
    }

    public AnswerOption getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(AnswerOption answerOption) {
        this.answerOption = answerOption;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getTransaction_code() {
        return transaction_code;
    }

    public void setTransaction_code(String transaction_code) {
        this.transaction_code = transaction_code;
    }

    @Override
    public String toString() {
        return "ChosenOption{" +
                /*"co_id=" + co_id +
                ", co_ao=" + co_ao +
                ", co_a=" + co_a +
                ", co_q=" + co_q +
                ", transaction_code='" + transaction_code + '\'' +
                */'}';
    }

}
