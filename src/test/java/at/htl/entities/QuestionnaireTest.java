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
class QuestionnaireTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    @Inject
    AgroalDataSource ds;

    @Test
    void createQuestionnaireTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        tm.begin();
        em.persist(q);
        tm.commit();
        Table questionnaires = new Table(ds, "lq_questionnaire");
        assertThat(questionnaires).column("qn_id")
                .value().isEqualTo(1)
                .column("qn_name")
                .value().isEqualTo("Test")
                .column("qn_description")
                .value().isEqualTo("Test of the Questionnaire");
    }
}