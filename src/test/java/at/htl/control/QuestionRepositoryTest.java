package at.htl.control;

import at.htl.entities.Question;
import at.htl.entities.QuestionType;
import at.htl.entities.Questionnaire;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Inject
    AgroalDataSource ds;

    @Inject
    QuestionRepository questionRepository;

    Table t = new Table(ds, "question");
    Table questionnaire = new Table(ds, "lq_questionnaire");

    @Order(100)
    @Test
    void persistAQuestion() {
        Questionnaire questionnaire = new Questionnaire(1L,"Quest1",
                "TestQuestionnaire 1");

        Question question = new Question(
                "This is a question",
                1,
                QuestionType.YesNoQuestion.toString(),
                questionnaire);


        questionRepository.save(question);

        Table table = new Table(ds,"LQ_QUESTION");
        Assertions.assertThat(table)
                .column("Q_TEXT").value().isEqualTo("This is a question")
                .column("Q_QUESTIONNAIRE_ID").value().isEqualTo(questionnaire.getId());
    }


    @Test
    @Order(10)
    void createQuestionTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        Question qu = new Question("Yes or No", 1, QuestionType.SingleChoice.name(), q);

        questionRepository.save(qu);
        assertThat(t).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes or No")
                .value().isEqualTo("SINGLECHOICE")
                .value().isEqualTo(1);
    }

    @Test
    @Order(20)
    void deleteQuestionTest(){
        questionRepository.delete(1);
        assertThat(t).hasNumberOfRows(0);
    }

    @Test
    @Order(30)
    void createMultipleChoiceQuestionTest(){
        Questionnaire q = new Questionnaire(2L, "Test", "Test of the Questionnaire");
        Question qu = new Question("MultipleChoice Question", 1, QuestionType.MultipleChoice.name(), q);

        questionRepository.save(qu);
        assertThat(t).row(0).column(3).value().equals("MULTIPLECHOICE");
    }

    @Test
    @Order(40)
    void createYESORNOQuestionTest(){

        Question qu = new Question("YESORNO Question", 1, QuestionType.YesNoQuestion.name(), (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 2").getSingleResult());

        questionRepository.save(qu);
        assertThat(t).row(1).column(3).value().equals("YESORNO");
    }

    @Test
    @Order(66)
    void createFreetextQuestionTest(){

        Question qu = new Question("Freetext Question", 1, QuestionType.Text.name(), (Questionnaire) em.createQuery("select q from Questionnaire q where q.id = 2").getSingleResult());

        questionRepository.save(qu);
        assertThat(t).row(2).column(3).value().equals("FREETEXT");
    }
}
