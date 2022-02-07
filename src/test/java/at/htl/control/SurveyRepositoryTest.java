package at.htl.control;

import at.htl.entity.*;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
class SurveyRepositoryTest {

    @Inject
    SurveyRepository surveyRepository;

    @Inject
    AgroalDataSource ds;

    Teacher teacher = new Teacher("Max Mustermann");
    Questionnaire questionnaire = new Questionnaire("Quest1", "TestQuestionnaire 1");
    Survey survey = new Survey(LocalDate.of(2022,3,2),teacher,questionnaire);

    @Order(100)
    @Test
    void persistASurvey() {

        //surveyRepository.save(survey);
        surveyRepository.save(survey);

        Table table = new Table(ds,"LD_SURVEY");
        org.assertj.db.api.Assertions.assertThat(table).hasNumberOfRows(1);
        org.assertj.db.api.Assertions.assertThat(table)
                .column("S_TEACHER").value().isEqualTo(teacher.getId())
                .column("S_QUESTIONNAIRE").value().isEqualTo(questionnaire.getId())
                .column("S_DATE").value().isEqualTo("2022-03-02");
    }


}