package at.htl.entities;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;

import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
class AnswerOptionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    @Inject
    AgroalDataSource ds;


    @Test
    void createAnswerOptionsTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SingleChoice.name(), q);
        tm.begin();
        em.persist(q);
        em.persist(qn);
        em.persist(new AnswerOption("Yes", 1, 1, qn, 1));
        em.persist(new AnswerOption("no", 2, 2, qn, 1));
        tm.commit();
        Table ao = new Table(ds, "lq_answer_option");
        assertThat(ao).hasNumberOfRows(2);
        assertThat(ao).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes")
                .value().isEqualTo(1);
    }
}