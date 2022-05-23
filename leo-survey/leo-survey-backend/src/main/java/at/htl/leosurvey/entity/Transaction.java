package at.htl.leosurvey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "S_TRANSACTION")
public class Transaction extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TR_ID")
    public Long id;

    @Column(name = "TR_TRANSACTION_CODE")
    public String transactionCode;

    @Column(name = "TR_IS_USED")
    public Boolean isUsed;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "T_S_ID")
    public Survey survey;

    public Transaction() {
    }

    public Transaction(String transactionCode, boolean isUsed, Survey survey) {
        this.transactionCode = transactionCode;
        this.isUsed = isUsed;
        this.survey = survey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(transactionCode, that.transactionCode) && Objects.equals(isUsed, that.isUsed) && Objects.equals(survey, that.survey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionCode, isUsed, survey);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionCode='" + transactionCode + '\'' +
                ", isUsed=" + isUsed +
                ", survey=" + survey +
                '}';
    }
}
