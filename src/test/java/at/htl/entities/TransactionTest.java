package at.htl.entities;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
class TransactionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    @Inject
    AgroalDataSource ds;

    @Test
    void createS_TransactionTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey survey = new Survey(dt, q);
        tm.begin();
        em.persist(q);
        em.persist(survey);
        em.persist(new Transaction("abc", false, survey));
        tm.commit();
        Table transactions = new Table(ds, "lq_transaction");
        assertThat(transactions).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(false)
                .value().isEqualTo("abc")
                .value().isEqualTo(1);
    }
}