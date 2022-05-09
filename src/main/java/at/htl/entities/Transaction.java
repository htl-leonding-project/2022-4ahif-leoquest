package at.htl.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Random;

@NamedQueries({

        @NamedQuery(
                name = "Transaction.findAll",
                query = "select ta from Transaction ta"
        ),
        @NamedQuery(
                name = "Transaction.findById",
                query = "select t from Transaction t where t.id = :id order by t.code"
        )
})
@Entity
@Table(name = "LQ_TRANSACTION")
public class Transaction extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_code")
    private String code;

    @Column(name = "t_password")
    private String password;

    @Column(name = "t_isUsed")
    public boolean isUsed;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "t_survey_id")
    private Survey survey;

    public Transaction() {
    }

    public Transaction(String password, boolean isUsed, Survey survey) {
        this.code = generateTransactionCode();
        this.password = password;
        this.isUsed = isUsed;
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String t_code) {
        this.code = t_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String t_password) {
        this.password = t_password;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean t_isUsed) {
        this.isUsed = t_isUsed;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey t_survey) {
        this.survey = t_survey;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", isUsed=" + isUsed +
                ", survey=" + survey +
                '}';
    }

    public String generateTransactionCode(){
        Random r = new Random();
        String back = "";
        for(int i = 0; i < 16; i++){
            char c = (char)(r.nextInt(26) + 'a');
            back += c;
        }
        return back;
    }

}

