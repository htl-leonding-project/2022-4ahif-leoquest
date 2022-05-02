package at.htl.control;

import at.htl.entities.Questionnaire;
import at.htl.entities.Survey;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SurveyRepositoryTest {

    @Inject
    SurveyRepository surveyRepository;

    @Inject
    AgroalDataSource ds;


    @Test
    @Order(10)
    void createSurveyTest(){
        Table t = new Table(ds, "lq_survey");

        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate d = LocalDate.now();
        Survey s = new Survey(d, q);

        surveyRepository.save(s);
        assertThat(t).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(d)
                .value().isEqualTo(1);
    }
}
