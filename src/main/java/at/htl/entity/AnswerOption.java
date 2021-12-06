package at.htl.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LD_ANSWER_OPTION")
@SequenceGenerator(name = "ld_answer_option_seq",
        sequenceName = "LD_ANSWER_OPTION_SEQ",
        initialValue = 5000,
        allocationSize = 1
)
public class AnswerOption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    public String text;

    public LocalDateTime createDateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    public Survey survey;

    public AnswerOption() {
    }

    public AnswerOption(String text, Survey survey) {
        this.createDateTime = LocalDateTime.now();

        this.text = text;
        this.survey = survey;
    }

    @Override
    public String toString() {
        return "AnswerOption{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createDateTime=" + createDateTime +
                ", survey=" + survey +
                '}';
    }
}
