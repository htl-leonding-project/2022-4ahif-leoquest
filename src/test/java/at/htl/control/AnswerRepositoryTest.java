package at.htl.control;

import at.htl.entity.Answer;
import at.htl.entity.Question;
import at.htl.entity.QuestionType;
import at.htl.entity.Questionnaire;
import com.google.common.collect.Table;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnswerRepositoryTest {

    @Inject
    AnswerRepository answerRepository;

    /*
    @Test
    @Order(10)
    void createAnswerTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SINGLECHOICE.name(), q);
        answerRepository.save(new Answer("Yes", qn));
        assertThat(a).hasNumberOfRows(1);
        assertThat(a).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes")
                .value().isEqualTo(1);
    }*/
}