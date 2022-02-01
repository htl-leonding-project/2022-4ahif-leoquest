package at.htl.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name="S.findByQuestion",
                query="SELECT s FROM Survey s where s.question LIKE :QUESTION"
        ),
        @NamedQuery(
                name = "Survey.findAll",
                query = "select s from Survey s"
        )
})

@Entity
@Table(name = "LD_SURVEY")
@SequenceGenerator(name = "surveySeq",
        sequenceName = "LD_SURVEY_SEQ",
        initialValue = 1000,
        allocationSize = 1
)
public class Survey {

    @Id
    @Column(name="S_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surveySeq")
    public Long id;

    @Column(name="S_QUESTION")
    public String question;

    @Column(name="S_CREATE_DATE_TIME")
    public LocalDateTime createDateTime;

    public Survey() {
    }

    public Survey(String qs) {
        this.question = qs;
        this.createDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
