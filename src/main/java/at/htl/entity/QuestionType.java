package at.htl.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "LD_QUESTION_TYPE")
public class QuestionType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qt_id")
    Long id;

    @Column(name = "qt_name")
    String name;

    // TODO: UNIDIREKTIONAL

    public QuestionType() {
    }

    public QuestionType(Long id, String name) {
        this.id = id;
        this.name = name;

    }
}
