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
@Table(name = "LQ_CHOSEN_OPTION")
public class ChosenOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "co_answerOption")
    private AnswerOption answerOption;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "co_answer")
    private Answer answer;

    public ChosenOption() {
    }

}
