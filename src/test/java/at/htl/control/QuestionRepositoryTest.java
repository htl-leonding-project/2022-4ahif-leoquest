package at.htl.control;

import at.htl.entity.Question;
import at.htl.entity.QuestionType;
import at.htl.entity.Questionnaire;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class QuestionRepositoryTest {

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AgroalDataSource ds;

    @Order(100)
    @Test
    void persistAQuestion() {
        Questionnaire questionnaire = new Questionnaire(1L,"Quest1",
                "TestQuestionnaire 1");
        QuestionType questionType = new QuestionType(1L,"qType 01");

        Question question = new Question(
                "This is a question",
                1,
                questionType,
                questionnaire);


        questionRepository.save(question);

        Table table = new Table(ds,"LD_QUESTION");
        Assertions.assertThat(table)
                .column("Q_TEXT").value().isEqualTo("This is a question")
                .column("Q_QUESTIONNAIRE").value().isEqualTo(questionnaire.getId())
                .column("Q_TYPE").value().isEqualTo(questionType.getId());
    }
}
