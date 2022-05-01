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
class SurveyTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    @Inject
    AgroalDataSource ds;

    @Test
    void createSurveyTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        tm.begin();
        em.persist(q);
        em.persist(new Survey(dt, q));
        tm.commit();
        Table surveys = new Table(ds, "lq_survey");
        assertThat(surveys).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(dt)
                .value().isEqualTo(1);
    }
}