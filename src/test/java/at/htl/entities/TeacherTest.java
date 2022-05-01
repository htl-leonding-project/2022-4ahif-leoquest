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
class TeacherTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;
    @Inject
    AgroalDataSource ds;

    @Test
    void createTeacherTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey s = new Survey(dt, q);
        tm.begin();
        em.persist(q);
        em.persist(s);
        em.persist(new Teacher("Teach", s));
        tm.commit();
        Table teacher = new Table(ds, "lq_teacher");
        assertThat(teacher).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Teach")
                .value().isEqualTo(1);
    }
}