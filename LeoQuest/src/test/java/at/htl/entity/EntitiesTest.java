package at.htl.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class EntitiesTest {

    Teacher teacher = new Teacher("Max Mustermann");
    QuestionType questionType = new QuestionType(1L, "FREETEXT");
    Questionnaire questionnaire = new Questionnaire("Schüler zufriedenheit",
            "In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.");
    Question question = new Question("An diesem Lehrer gefällt mir...", 1, questionType, questionnaire);
    Survey survey = new Survey(LocalDate.of(2020, 8,1), teacher, questionnaire);
    Transaction transaction = new Transaction("code", "password123", false, survey);
    Answer answer = new Answer("Alles", transaction, question);

    String text = "Ich denke, dieser Lehrer ballert...";
    int value = 1;
    int sequenceNumber = 2;
    AnswerOption answerOption = new AnswerOption(text, value, sequenceNumber, question);

    EntitiesTest() throws SQLException {}

    @Test
    @Order(10)
    void createAnswer_Test() {
        assertThat(answer.getText()).isEqualTo("Alles");
        assertThat(answer.getTransaction()).isEqualTo(transaction);
        assertThat(answer.getQuestion()).isEqualTo(question);
    }

    @Test
    @Order(20)
    void toStringAnswer_Test() {
        assertThat(answer.toString()).isEqualTo("Answer{id=null, text='Alles', " +
                "transaction=Transaction{id=null, code='code', password='password123', " +
                "isUsed=false, survey=Survey{id=null, date=2020-08-01, " +
                "teacher=Teacher{id=null, name='Max Mustermann'}, " +
                "questionnaire=Questionnaire{id=null, name='Schüler zufriedenheit', " +
                "desc='In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.'}}}," +
                " question=Question{id=null, text='An diesem Lehrer gefällt mir...', sequenceNumber=1, " + "type=FREETEXT, " +
                "questionnaire=Questionnaire{id=null, name='Schüler zufriedenheit', " +
                "desc='In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.'}}}");
    }


    @Test
    @Order(10)
    void createAnswerOption_Test() {
        assertThat(answerOption.getText()).isEqualTo(text);
        assertThat(answerOption.getValue()).isEqualTo(value);
        assertThat(answerOption.getSeqNumber()).isEqualTo(sequenceNumber);
        assertThat(answerOption.getQuestion()).isEqualTo(question);
    }

    @Test
    @Order(20)
    void toStringAnswerOption_Test() {
        assertThat(answerOption.toString()).isEqualTo("AnswerOption{id=null, text='Ich denke, dieser Lehrer ballert...', " +
                "value=1, sequenceNumber=2, question=Question{id=null, " +
                "text='An diesem Lehrer gefällt mir...', sequenceNumber=1, " +
                "type=FREETEXT, questionnaire=Questionnaire{id=null, name='Schüler zufriedenheit', " +
                "desc='In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.'}}}");
    }

    @Test
    @Order(10)
    void createQuestionnaire_Test() {
        //assert
        assertThat(questionnaire.getName()).isEqualTo("Schüler zufriedenheit");
        assertThat(questionnaire.getDesc()).isEqualTo("In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.");
    }

    @Test
    @Order(20)
    void toStringQuestionnaire_Test() {
        assertThat(questionnaire.toString()).isEqualTo("Questionnaire{id=null, name='Schüler zufriedenheit', " +
                "desc='In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.'}");
    }

    @Test
    @Order(10)
    void createSurvey_Test() {
        //assert
        assertThat(survey.getDate()).isEqualTo(LocalDate.of(2020, 8,1));
    }

    @Test
    @Order(20)
    void toStringSurvey_test() {
        assertThat(survey.toString()).isEqualTo("Survey{id=null, date=2020-08-01, teacher=Teacher{id=null, " +
                "name='Max Mustermann'}, questionnaire=Questionnaire{id=null, name='Schüler zufriedenheit', " +
                "desc='In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.'}}");
    }

    @Test
    @Order(10)
    void createQuestion_Test() throws SQLException {
        assertThat(question.getText()).isEqualTo("An diesem Lehrer gefällt mir...");
        assertThat(question.getSeqNumber()).isEqualTo(1);
        assertThat(questionType.name).isEqualTo("FREETEXT");
        assertThat(question.getQuestionnaire()).isEqualTo(questionnaire);
    }

    @Test
    @Order(20)
    void toStringQuestion_Test() {
        assertThat(question.toString()).isEqualTo("Question{id=null, text='An diesem Lehrer gefällt mir...', " +
                "sequenceNumber=1, type=FREETEXT, " +
                "questionnaire=Questionnaire{id=null, name='Schüler zufriedenheit', " +
                "desc='In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.'}}");
    }


    @Test
    @Order(10)
    void createTeacher_Test() {
        //assert
        assertThat(teacher.getName()).isEqualTo("Max Mustermann");
    }

    @Test
    @Order(20)
    void toStringTeacher_Test() {
        //assert
        assertThat(teacher.toString()).isEqualTo("Teacher{id=null, name='Max Mustermann'}");
    }

    @Test
    @Order(10)
    void createTransaction_Test() {
        //assert
        assertThat(transaction.getCode()).isEqualTo("code");
        assertThat(transaction.getPassword()).isEqualTo("password123");
        assertThat(transaction.isUsed).isEqualTo(false);
    }

    @Test
    @Order(20)
    void toStringTransaction_Test(){
        assertThat(transaction.toString()).isEqualTo("Transaction{id=null, code='code', " +
                "password='password123', isUsed=false, survey=Survey{id=null, date=2020-08-01, " +
                "teacher=Teacher{id=null, name='Max Mustermann'}, " +
                "questionnaire=Questionnaire{id=null, name='Schüler zufriedenheit', " +
                "desc='In diesem Fragebogen wird die zufriedenheit der Schüler abgefragt.'}}}");
    }

    // Survey Questionnaire Answer AnswerOption Question
    // TODO: ; ChosenOption; ; QuestionType; ;

}
