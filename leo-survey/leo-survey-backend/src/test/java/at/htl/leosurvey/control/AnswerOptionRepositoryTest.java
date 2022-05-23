package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.*;
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
class AnswerOptionRepositoryTest {
    @Inject
    AnswerOptionRepository repository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AgroalDataSource ds;

    @Test
    @Order(1)
    void t001_insertAnswerOption() {
        // arrange
        Question question = new Question("xy1", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));
        AnswerOption answerOption = new AnswerOption("text1", 1, 1, null, question);

        // act
        answerOption = repository.save(answerOption);

        // assert
        Table table = new Table(ds, "S_ANSWER_OPTION");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("AO_ID").isNotNull()
                .value("AO_TEXT").isEqualTo("text1")
                .value("AO_Q_ID").isEqualTo(111L)
                .value("AO_VALUE").isEqualTo(1);
        repository.remove(answerOption);
    }

    @Test
    @Order(2)
    void t002_findAll() {
        List<AnswerOption> list = repository.findAll().list();

        Table table = new Table(ds, "S_ANSWER_OPTION");
        org.assertj.core.api.Assertions.assertThat(list.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row(0)
                .value("AO_ID").isNotNull()
                .value("AO_TEXT").isEqualTo("trifft völlig zu")
                .value("AO_Q_ID").isEqualTo(1L)
                .value("AO_VALUE").isEqualTo(4);
        assertThat(table).row(table.getRowsList().size()-1)
                .value("AO_ID").isNotNull()
                .value("AO_TEXT").isEqualTo("völlig falsch")
                .value("AO_Q_ID").isEqualTo(107L)
                .value("AO_VALUE").isEqualTo(4);
    }

    @Test
    @Order(3)
    public void t003_findAnswerOptionById(){
        //arrange
        Question question = new Question("xy", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));
        AnswerOption answerOption = new AnswerOption("text", 1, 1, null, question);

        //act
        answerOption = repository.save(answerOption);
        AnswerOption answerOption1 = repository.findById(1L);

        //assert
        Table table = new Table(ds, "S_ANSWER_OPTION");

        assertThat(table).row(table.getRowsList().size()-1)
                .value("AO_ID").isNotNull()
                .value("AO_TEXT").isEqualTo("text")
                .value("AO_Q_ID").isEqualTo(112L)
                .value("AO_VALUE").isEqualTo(1);
        repository.remove(answerOption);
    }

    @Test
    @Order(4)
    public void t004_updateAnswerOption() {
        //arrange
        Question question = new Question("xy", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));
        AnswerOption answerOption = new AnswerOption("text", 1, 1, null, question);

        //act
        answerOption = repository.save(answerOption);
        answerOption.text = "text2";
        answerOption = repository.save(answerOption);

        //assert
        Table table = new Table(ds, "S_ANSWER_OPTION");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("AO_ID").isNotNull()
                .value("AO_TEXT").isEqualTo("text2")
                .value("AO_Q_ID").isEqualTo(113L)
                .value("AO_VALUE").isEqualTo(1);
        repository.remove(answerOption);
    }

    @Test
    @Order(5)
    public void t005_deleteAnswerOption() {
        //arrange
        Question question = new Question("xyz", 1, QuestionType.SINGLECHOICE, Questionnaire.findById(1L));
        AnswerOption answerOption = new AnswerOption("test", 1, 1, null, question);

        answerOption = repository.save(answerOption);

        Table table = new Table(ds, "S_ANSWER_OPTION");
        int before = table.getRowsList().size();
        repository.remove(answerOption);
        table = new Table(ds, "S_ANSWER_OPTION");
        int after = table.getRowsList().size();

        assertThat(before).isEqualTo(after + 1);
    }

    @Test
    @Order(6)
    public void t006_findByQuestionnaireId() {
        Questionnaire questionnaire = questionnaireRepository.findById(1L);

        List<AnswerOption> answerOptions = repository.findByQuestionnaireId(questionnaire.id);

        assertThat(answerOptions.size()).isEqualTo(161);
    }

    @Test
    @Order(7)
    public void t007_findByQuestionId() {
        Question question = questionRepository.findById(1L);

        List<AnswerOption> answerOptions = repository.findByQuestionId(question.id);

        assertThat(answerOptions.size()).isEqualTo(4);
    }

    @Test
    @Order(8)
    public void t008_removeAll(){

        questionRepository.remove(questionRepository.findById(103L));
        questionRepository.remove(questionRepository.findById(102L));
        questionRepository.remove(questionRepository.findById(101L));
        questionRepository.remove(questionRepository.findById(100L));
    }
}



