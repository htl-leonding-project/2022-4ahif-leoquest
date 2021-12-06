package at.htl.control;

import at.htl.entity.Answer;
import at.htl.entity.AnswerOption;
import at.htl.entity.Survey;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SurveyRepositoryTest {

    @Inject
    SurveyRepository surveyRepository;

    @Inject
    AnswerOptionRepository answerOptionRepository;

    @Inject
    AnswerRepository answerRepository;

    @Inject
    AgroalDataSource ds;


    @Order(100)
    @Test
    void persistASurvey() {
        Survey survey = new Survey("Frage A");
        surveyRepository.save(survey);
        surveyRepository.save(survey);
        Table table = new Table(ds, "LD_SURVEY");
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
        org.assertj.db.api.Assertions.assertThat(table)
                .column("S_ID").value().isEqualTo(1000)
                .column("S_QUESTION").value().isEqualTo("Frage A")
                .column("S_CREATE_DATE_TIME").value().isEqualTo(survey.createDateTime);
    }

    @Order(1000)
    @Test
    void persistSurveyAndAnswerOptions() {
        Survey survey = new Survey("Frage XY?");
        AnswerOption ao1 = new AnswerOption("Antwortmöglichkeit 1", survey);
        AnswerOption ao2 = new AnswerOption("Antwortmöglichkeit 2", survey);
        AnswerOption ao3 = new AnswerOption("Antwortmöglichkeit 3", survey);
        AnswerOption ao4 = new AnswerOption("Antwortmöglichkeit 4", survey);

        answerOptionRepository.save(ao1);
        answerOptionRepository.save(ao2);
        answerOptionRepository.save(ao3);
        answerOptionRepository.save(ao4);

//        Request table = new Request(
//                ds,
//                "select * from LD_SURVEY where s_question = 'Frage XY?'");
        Table table = new Table(
                ds,
                "LD_SURVEY", new Table.Order[]{Table.Order.asc("S_ID")});

        output(table).toConsole();

        org.assertj.db.api.Assertions.assertThat(table)
                .row(1)
                .value("S_ID").isEqualTo(1001)
                .value("S_QUESTION").isEqualTo("Frage XY?")
                .value("S_CREATE_DATE_TIME").isEqualTo(survey.createDateTime);
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(2);


    }


    @Order(1010)
    @Test
    void persistSurveyWithOneAnswerPerOption() {
        Survey survey = new Survey("Wann haben Sie Zeit?");
        AnswerOption ao1 = new AnswerOption("1. Dezember", survey);
        AnswerOption ao2 = new AnswerOption("2. Dezember", survey);
        AnswerOption ao3 = new AnswerOption("3. Dezember", survey);
        AnswerOption ao4 = new AnswerOption("4. Dezember", survey);

        Answer a1 = new Answer(ao1, "Mickey");
        Answer a2 = new Answer(ao2, "Minney");
        Answer a3 = new Answer(ao3, "Daniel");
        Answer a4 = new Answer(ao4, "Carlo");
        Answer a5 = new Answer(ao2, "Goofy");

        answerRepository.save(a1);
        answerRepository.save(a2);
        answerRepository.save(a3);
        answerRepository.save(a4);
        answerRepository.save(a5);

        assertThat(a1.answerOption.text).isEqualTo("1. Dezember");
    }

    @Order(1020)
    @Test
    void persistSurveyWithMultipleAnswersPerOption() {
        Survey survey = new Survey("Sind sie mit -... einverstanden?");
        AnswerOption ao1 = new AnswerOption("ja", survey);
        AnswerOption ao2 = new AnswerOption("nein", survey);

        ao1 = answerOptionRepository.save(ao1);
        ao2 = answerOptionRepository.save(ao2);

        Answer a1 = new Answer(ao1, "Dagobert");
        Answer a2 = new Answer(ao2, "Donald");
        Answer a3 = new Answer(ao1, "Daisy");
        Answer a4 = new Answer(ao1, "Klarabella");
        Answer a5 = new Answer(ao2, "Tick");
        Answer a6 = new Answer(ao1, "Trick");
        Answer a7 = new Answer(ao1, "Track");

        a1 = answerRepository.save(a1);
        a2 = answerRepository.save(a2);
        a3 = answerRepository.save(a3);
        a4 = answerRepository.save(a4);
        a5 = answerRepository.save(a5);
        a6 = answerRepository.save(a6);
        a7 = answerRepository.save(a7);

        assertThat(a1.answerOption.text).isEqualTo("ja");
        assertThat(a2.answerOption.text).isEqualTo("nein");
        assertThat(a3.answerOption.text).isEqualTo("ja");
        assertThat(a4.answerOption.text).isEqualTo("ja");
        assertThat(a5.answerOption.text).isEqualTo("nein");
        assertThat(a6.answerOption.text).isEqualTo("ja");
        assertThat(a7.answerOption.text).isEqualTo("ja");

        // Assert
        Table surveyTable = new Table(ds, "LD_SURVEY");
        Table answerOptionsTable = new Table(ds, "LD_ANSWER_OPTION");
        Table answerTable = new Table(ds, "LD_ANSWER");

        output(surveyTable).toConsole();
        output(answerOptionsTable).toConsole();
        output(answerTable).toConsole();

        org.assertj.db.api.Assertions.assertThat(surveyTable).hasNumberOfRows(4);
        org.assertj.db.api.Assertions.assertThat(answerOptionsTable).hasNumberOfRows(10);
        org.assertj.db.api.Assertions.assertThat(answerTable).hasNumberOfRows(12);
    }


}