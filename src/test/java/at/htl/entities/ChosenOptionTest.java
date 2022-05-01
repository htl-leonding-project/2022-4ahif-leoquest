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
class ChosenOptionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    @Inject
    AgroalDataSource ds;

    @Test
    void createChosenOptionTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SingleChoice.name(), q);
        Answer a = new Answer("Yes", qn);
        AnswerOption a1 = new AnswerOption("Yes", 1, 1, qn, 0);
        tm.begin();
        em.persist(q);
        em.persist(qn);
        em.persist(a);
        em.persist(a1);
        em.persist(new ChosenOption(a1, a, qn, "abc"));
        tm.commit();
        Table chosenOption = new Table(ds, "lq_chosenoption");
        assertThat(chosenOption).hasNumberOfRows(1);
        assertThat(chosenOption).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("abc")
                .value().isEqualTo(1)
                .value().isEqualTo(1);
    }
}