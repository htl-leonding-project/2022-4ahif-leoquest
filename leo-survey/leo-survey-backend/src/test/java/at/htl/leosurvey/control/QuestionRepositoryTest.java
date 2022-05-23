package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.*;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class QuestionRepositoryTest {

    @Inject
    QuestionRepository repository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    AgroalDataSource ds;

    @Test
    @Order(1)
    void t001_insertQuestion() {
        // arrange
        Question question = new Question("xy", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));

        // act
        question = repository.save(question);

        // assert
        Table table = new Table(ds, "S_QUESTION");
        output(table).toConsole();

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("Q_ID").isNotNull()
                .value("Q_TEXT").isEqualTo("xy")
                .value("Q_SEQUENCE_NUMBER").isEqualTo(1);
        repository.remove(question);
    }

    @Test
    @Order(2)
    void t002_findAll() {
        List<Question> list = repository.findAll().list();

        Table table = new Table(ds, "S_QUESTION");
        org.assertj.core.api.Assertions.assertThat(list.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row(0)
                .value("Q_ID").isNotNull()
                .value("Q_TEXT").isEqualTo("Der Lehrer ist fair - gerecht")
                .value("Q_SEQUENCE_NUMBER").isEqualTo(1);
        assertThat(table).row(table.getRowsList().size()-1)
                .value("Q_ID").isNotNull()
                .value("Q_TEXT").isEqualTo("xy")
                .value("Q_SEQUENCE_NUMBER").isEqualTo(1);
    }

    @Test
    @Order(3)
    public void t003_findQuestionById(){
        //arrange
        Question question = new Question("xy", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));

        //act
        question = repository.save(question);
        Question question1 = repository.findById(1L);

        //assert
        Table table = new Table(ds, "S_QUESTION");
        output(table).toConsole();

        assertThat(table).row(table.getRowsList().size()-1)
                .value("Q_ID").isNotNull()
                .value("Q_TEXT").isEqualTo("xy")
                .value("Q_SEQUENCE_NUMBER").isEqualTo(1);
        repository.remove(question);
    }

    @Test
    @Order(4)
    public void t004_updateQuestion() {
        //arrange
        Question q1 = new Question("xy", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));


        //act
        q1 = repository.save(q1);
        q1.text = "yx";
        q1 = repository.save(q1);

        //assert
        Table table = new Table(ds, "S_QUESTION");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("Q_ID").isNotNull()
                .value("Q_TEXT").isEqualTo("yx")
                .value("Q_SEQUENCE_NUMBER").isEqualTo(1);
        repository.remove(q1);
    }

    @Test
    @Order(5)
    public void t005_deleteQuestion() {
        //arrange
        Question question = new Question("xy", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));

        question = repository.save(question);

        Table table = new Table(ds, "S_QUESTION");
        int before = table.getRowsList().size();
        repository.remove(question);
        table = new Table(ds, "S_QUESTION");
        int after = table.getRowsList().size();

        assertThat(before).isEqualTo(after + 1);

    }
    @Test
    @Order(6)
    public void t006_findQuestionsByQuestionnaire(){
        Questionnaire questionnaire = Questionnaire.findById(1L);

        List<Question> questions = repository.findByQuestionnaire(questionnaire);

        assertThat(questions.size()).isEqualTo(42);
    }

    @Test
    @Order(7)
    public void t007_findQuestionsByInterviewerId(){
        Questionnaire questionnaire = Questionnaire.findById(1L);

        List<Question> questions = repository.getQuestionsByQuestionaireId(questionnaire.id);

        assertThat(questions.size()).isEqualTo(42);
    }
}


