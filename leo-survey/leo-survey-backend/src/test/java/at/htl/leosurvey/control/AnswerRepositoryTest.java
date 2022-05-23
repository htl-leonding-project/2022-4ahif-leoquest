package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.*;
import cucumber.api.java.cs.A;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AnswerRepositoryTest {
    @Inject
    AnswerRepository repository;

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AgroalDataSource ds;

    Questionnaire questionnaire = new Questionnaire("Q1", "Lehrerfeedback", true, "Susi");
    Question question = new Question("xy", 1, QuestionType.SINGLECHOICE, questionnaire);
    LocalDate date = LocalDate.of(2022, 02, 20);
    Survey survey = new Survey("surv1", "desc1", "Susi", questionnaire, date, false);
    Transaction transaction = new Transaction("1003-2202-abcd", true, survey);

    @Test
    @Order(1)
    void t001_insertAnswer() {
        // arrange
        Answer answer = new Answer(question, "answerText1", transaction);

        // act
        repository.save(answer);

        // assert
        Table table = new Table(ds, "S_ANSWER");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("A_ID").isNotNull()
                .value("A_ANSWER_TEXT").isEqualTo("answerText1")
                .value("A_Q_ID").isEqualTo(115L)
                .value("A_tr_ID").isEqualTo(10L);
        repository.remove(answer);
    }

    @Test
    @Order(2)
    void t002_findAll() {
        List<Answer> list = repository.findAll().list();

        Table table = new Table(ds, "S_ANSWER");
        org.assertj.core.api.Assertions.assertThat(list.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row(0)
                .value("A_ID").isNotNull()
                .value("A_ANSWER_TEXT").isEqualTo("völlig richtig")
                .value("A_Q_ID").isEqualTo(85L);
        assertThat(table).row(table.getRowsList().size()-1)
                .value("A_ID").isNotNull()
                .value("A_ANSWER_TEXT").isEqualTo("answerText1")
                .value("A_Q_ID").isEqualTo(115L);
    }

    @Test
    @Order(3)
    public void t003_findAnswerById(){
        //arrange
       Answer answer = new Answer(question, "answerText", transaction);

        //act
        answer = repository.save(answer);
        Answer answer1 = repository.findById(1L);

        //assert
        Table table = new Table(ds, "S_ANSWER");

        assertThat(table).row(table.getRowsList().size()-1)
                .value("A_ID").isNotNull()
                .value("A_ANSWER_TEXT").isEqualTo("answerText")
                .value("A_Q_ID").isEqualTo(116L)
                .value("A_tr_ID").isEqualTo(11L);
        repository.remove(answer);
    }

    @Test
    @Order(4)
    public void t004_updateAnswer() {
        //arrange
        Answer answer = new Answer(question, "answerText", transaction);

        //act
        answer = repository.save(answer);
        answer.answerText = "answerText2";
        answer = repository.save(answer);

        //assert
        Table table = new Table(ds, "S_ANSWER");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("A_ID").isNotNull()
                .value("A_ANSWER_TEXT").isEqualTo("answerText2")
                .value("A_Q_ID").isEqualTo(117L)
                .value("A_tr_ID").isEqualTo(12L);
        repository.remove(answer);
    }

    @Test
    @Order(5)
    public void t005_deleteAnswer() {
        Answer answer = new Answer(question, "answerText", transaction);

        answer = repository.save(answer);

        Table table = new Table(ds, "S_ANSWER");
        int before = table.getRowsList().size();
        repository.remove(answer);
        table = new Table(ds, "S_ANSWER");
        int after = table.getRowsList().size();

        assertThat(before).isEqualTo(after + 1);

    }

    @Test
    @Order(6)
    public void t006_findByQuestion(){
        Question question = Question.findById(85L);

        List<Answer> answers = repository.findByQuestion(question);

        Table table = new Table(ds, "S_ANSWER");

        assertEquals(answers.size(), 1);
    }

    @Test
    @Order(7)
    public void t007_removeAll(){
        //repository.remove(repository.findById(15L));
        questionRepository.remove(questionRepository.findById(118L));
        questionRepository.remove(questionRepository.findById(117L));
        questionRepository.remove(questionRepository.findById(116L));
        questionRepository.remove(questionRepository.findById(115L));
        questionRepository.remove(questionRepository.findById(114L));
        questionRepository.remove(questionRepository.findById(113L));
        questionRepository.remove(questionRepository.findById(112L));
        questionRepository.remove(questionRepository.findById(111L));
        questionRepository.remove(questionRepository.findById(109L));
        //transactionRepository.remove(transactionRepository.findById(8L));
        transactionRepository.remove(transactionRepository.findById(5L));
    }

}
