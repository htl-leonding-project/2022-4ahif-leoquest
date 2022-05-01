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
class QuestionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    @Inject
    AgroalDataSource ds;

    @Test
    void createQuestionTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        tm.begin();
        em.persist(q);
        em.persist(new Question("Yes or No", 1, QuestionType.SingleChoice.name(), q));
        em.persist(new Question("No or Yes", 2, QuestionType.SingleChoice.name(), q));
        tm.commit();
        Table questions = new Table(ds, "lq_question");
        assertThat(questions).hasNumberOfRows(2);
        assertThat(questions).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes or No")
                .value().isEqualTo("SingleChoice")
                .value().isEqualTo(1);
    }
}