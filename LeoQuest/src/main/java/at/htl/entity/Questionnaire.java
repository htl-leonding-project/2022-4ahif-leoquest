package at.htl.entity;

import javax.persistence.*;

@NamedQueries({

        @NamedQuery(
                name = "Questionnaire.findAll",
                query = "select qn from Questionnaire qn"
        ),
        @NamedQuery(
                name = "Questionnaire.findById",
                query = "select qn from Questionnaire qn where qn.id = :id order by qn.name"
        )
})
@Entity
@Table(name = "LQ_QUESTIONNAIRE")
public class Questionnaire {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qn_id")
    private Long id;
    @Column(name = "qn_name")
    private String name;
    @Column(name = "qn_desc")
    private String desc;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "a_question_id")
    private Question[] question;

    public Questionnaire() {
    }

    public Questionnaire(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Questionnaire(Long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String qn_name) {
        this.name = qn_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String qn_desc) {
        this.desc = qn_desc;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
