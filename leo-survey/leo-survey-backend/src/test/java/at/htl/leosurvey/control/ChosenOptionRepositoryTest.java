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

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ChosenOptionRepositoryTest {
    @Inject
    ChosenOptionRepository repository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AnswerRepository answerRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    AgroalDataSource ds;

    Questionnaire questionnaire = new Questionnaire("Q1", "Lehrerfeedback", true, "Susi");
    Question question = new Question("xy", 1, QuestionType.SINGLECHOICE, questionnaire);
    LocalDate date = LocalDate.of(2022, 02, 20);
    Survey survey = new Survey("surv1", "desc1", "Susi", questionnaire, date, false);
    Transaction transaction = new Transaction("1003-2202-abcd", true, survey);
    AnswerOption answerOption = new AnswerOption("text", 1, 1, null, question);
    Answer answer = new Answer(question, "answerText", transaction);


    @Test
    @Order(1)
    void t001_insertChosenOption() {
        // arrange
        ChosenOption chosenOption = new ChosenOption(answerOption, answer,question);

        // act
        chosenOption = repository.save(chosenOption);

        // assert
        Table table = new Table(ds, "S_CHOSEN_OPTION");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("CO_ID").isNotNull()
                .value("CO_A_ID").isEqualTo(23L)
                .value("CO_AO_ID").isEqualTo(364L)
                .value("CO_Q_ID").isEqualTo(119L);
        repository.remove(chosenOption);
    }

    @Test
    @Order(2)
    void t002_findAll() {
        List<ChosenOption> list = repository.findAll().list();

        Table table = new Table(ds, "S_CHOSEN_OPTION");
        org.assertj.core.api.Assertions.assertThat(list.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row(0)
                .value("CO_ID").isNotNull()
                .value("CO_A_ID").isEqualTo(1L)
                .value("CO_AO_ID").isEqualTo(1L)
                .value("CO_Q_ID").isEqualTo(85);
        assertThat(table).row(table.getRowsList().size()-1)
                .value("CO_ID").isNotNull()
                .value("CO_A_ID").isEqualTo(14L)
                .value("CO_Q_ID").isEqualTo(99L);
    }

    @Test
    @Order(3)
    public void t003_findChosenOptionById(){
        //arrange
       ChosenOption chosenOption = new ChosenOption(answerOption, answer,question);

        //act
        chosenOption = repository.save(chosenOption);
        ChosenOption ChosenOption1 = repository.findById(1L);

        //assert
        Table table = new Table(ds, "S_CHOSEN_OPTION");

        assertThat(table).row(table.getRowsList().size()-1)
                .value("CO_ID").isNotNull()
                .value("CO_A_ID").isEqualTo(24L)
                .value("CO_AO_ID").isEqualTo(365L)
                .value("CO_Q_ID").isEqualTo(120L);
        repository.remove(chosenOption);
    }

    @Test
    @Order(4)
    public void t004_updateAnswerOption() {
        //arrange
        Question question2 = new Question("xy", 1, QuestionType.SINGLECHOICE, questionnaire);
        ChosenOption chosenOption = new ChosenOption(answerOption, answer,question);

        //act
        chosenOption = repository.save(chosenOption);
        chosenOption.question = question2;
        chosenOption = repository.save(chosenOption);

        //assert
        Table table = new Table(ds, "S_CHOSEN_OPTION");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("CO_ID").isNotNull()
                .value("CO_A_ID").isEqualTo(25L)
                .value("CO_AO_ID").isEqualTo(366L)
                .value("CO_Q_ID").isEqualTo(122L);
        repository.remove(chosenOption);
    }

    @Test
    @Order(5)
    public void t005_deleteChosenOption() {
        //arrange
        ChosenOption chosenOption = new ChosenOption(answerOption, answer,question);

        chosenOption = repository.save(chosenOption);

        Table table = new Table(ds, "S_CHOSEN_OPTION");
        int before = table.getRowsList().size();
        repository.remove(chosenOption);
        table = new Table(ds, "S_CHOSEN_OPTION");
        int after = table.getRowsList().size();

        assertThat(before).isEqualTo(after + 1);
    }

    @Test
    @Order(6)
    public void t006_findByQuestion() {
        Question question = questionRepository.findById(85L);

        List<ChosenOption> chosenOptions = repository.findByQuestion(question);

        assertThat(chosenOptions.size()).isEqualTo(1);
    }

    @Test
    @Order(7)
    public void t007_findByAnswer() {
        Answer answer = answerRepository.findById(1L);

        List<ChosenOption> chosenOptions = repository.findByAnswer(answer);

        assertThat(chosenOptions.size()).isEqualTo(1);
    }

    @Test
    @Order(8)
    public void t008_findByAnswerOption() {
        AnswerOption answerOption = answerOptionRepository.findById(1L);

        List<ChosenOption> chosenOptions = repository.findByAnswerOption(answerOption);

        assertThat(chosenOptions.size()).isEqualTo(2);
    }

    @Test
    @Order(9)
    public void t009_removeAll(){
        questionRepository.remove(questionRepository.findById(104L));

        questionRepository.remove(questionRepository.findById(107L));
        questionRepository.remove(questionRepository.findById(106L));
        questionRepository.remove(questionRepository.findById(105L));

        //answerRepository.remove(answerRepository.findById(20L));
        //answerRepository.remove(answerRepository.findById(19L));
        //answerRepository.remove(answerRepository.findById(18L));
        //answerRepository.remove(answerRepository.findById(17L));
        //answerOptionRepository.remove(answerOptionRepository.findById(362L));
        //answerOptionRepository.remove(answerOptionRepository.findById(361L));
        //answerOptionRepository.remove(answerOptionRepository.findById(360L));
    }
}



