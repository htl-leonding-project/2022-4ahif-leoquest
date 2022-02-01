package at.htl.entity;

import at.htl.entity.Answer;
import at.htl.entity.AnswerOption;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "ChosenOption.findAll",
                query = "select co from ChosenOption co"
        )
})
@Entity
@Table(name = "ChosenOption")
public class ChosenOption {
    @EmbeddedId
    ChosenOptionId id;

    public ChosenOption() {
    }

    public ChosenOption(AnswerOption answerOption, Answer answer) {

        id.setAnswerOption(answerOption);
        id.setAnswer(answer);
    }

}
