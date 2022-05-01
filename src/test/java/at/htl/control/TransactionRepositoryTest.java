package at.htl.control;

import at.htl.entities.Questionnaire;
import at.htl.entities.Survey;
import at.htl.entities.Transaction;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionRepositoryTest {

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    SurveyRepository surveyRepository;
    @Inject
    AgroalDataSource ds;

    Table transactions = new Table(ds, "lq_transaction");

    @PersistenceContext
    EntityManager em;

    @Test
    @Order(10)
    void createTransactionTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey survey = new Survey(dt, q);

        questionnaireRepository.save(q);
        surveyRepository.save(survey);
        transactionRepository.save(new Transaction("abc", false, (Survey) em.createQuery("select s from Survey s where s.id = 1").getSingleResult()));
        assertThat(transactions).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(false)
                .value().isEqualTo("abc")
                .value().isEqualTo(1);
    }

}
