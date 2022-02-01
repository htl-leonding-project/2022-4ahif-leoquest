package at.htl.entity;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ChosenOptionId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "co_answerOption")
    private AnswerOption answerOption;

    @ManyToOne
    @JoinColumn(name = "co_answer")
    private Answer answer;


    public AnswerOption getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(AnswerOption co_answerOption) {
        this.answerOption = co_answerOption;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer co_answer) {
        this.answer = co_answer;
    }


    @Override
    public String toString() {
        return "ChosenOption{" +
                "answerOption=" + answerOption +
                ", answer=" + answer +
                '}';
    }
}
