package at.htl.control;

import at.htl.entities.Questionnaire;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionnaireRepositoryTest {

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    UserTransaction userTransaction;

    @Inject
    AgroalDataSource ds;

    Table t = new Table(ds, "lq_questionnaire");

    @Test
    @Order(10)
    void createQuestionnaireTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        questionnaireRepository.save(q);


        Table t = new Table(ds, "questionnaire");
        assertThat(t).column("qn_id")
                .value().isEqualTo(1)
                .column("qn_name")
                .value().isEqualTo("Test")
                .column("qn_description")
                .value().isEqualTo("Test of the Questionnaire");

    }

    @Test
    @Order(20)
    void deleteQuestionnaireTest(){
        questionnaireRepository.delete(1);
        assertThat(t).hasNumberOfRows(0);
    }

    @Test
    @Order(30)
    void findQuestionnaireById(){
        Questionnaire q = new Questionnaire(1L,"Test", "Test of the Questionnaire");
        Questionnaire q1 = new Questionnaire(2L, "Test1", "Test1 of the Questionnaire");
        Questionnaire q2 = new Questionnaire(3L, "Test2", "Test2 of the Questionnaire");

        questionnaireRepository.save(q);
        questionnaireRepository.save(q1);
        questionnaireRepository.save(q2);

        Questionnaire q3 = questionnaireRepository.findById(2);
        Assertions.assertEquals(q3.getName(), "Test1" );
        Assertions.assertEquals(q3.getDesc(), "Test1 of the Questionnaire");
    }

}
