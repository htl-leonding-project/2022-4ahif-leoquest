package at.htl.control;

import at.htl.entities.Answer;
import at.htl.entities.Question;
import at.htl.entities.QuestionType;
import at.htl.entities.Questionnaire;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerRepositoryTest {

    @Inject
    AnswerRepository answerRepository;

    @Inject
    AgroalDataSource ds;



    @Test
    @Order(10)
    void createAnswerTest(){
        Table a = new Table(ds, "lq_answer");

        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SingleChoice.name(), q);
        answerRepository.save(new Answer("Yes", qn));
        assertThat(a).hasNumberOfRows(1);
        assertThat(a).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes")
                .value().isEqualTo(1);
    }
}
