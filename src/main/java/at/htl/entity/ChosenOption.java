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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "co_answerOption")
    private AnswerOption answerOption;

    @ManyToOne
    @JoinColumn(name = "co_answer")
    private Answer answer;

    public ChosenOption() {
    }

}
