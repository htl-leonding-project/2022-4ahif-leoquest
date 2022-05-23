package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.*;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
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
class SurveyRepositoryTest {
    @Inject
    SurveyRepository repository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    AgroalDataSource ds;

    Questionnaire questionnaire = new Questionnaire("Q1", "Lehrerfeedback", true, "Susi");
    LocalDate date = LocalDate.of(2022, 02, 20);

    @Test
    @Order(1)
    public void t001_insertSurvey() {
        // arrange
        Survey survey = new Survey("surv2", "desc", "Susi", questionnaire, date, false);

        // act
        survey = repository.save(survey);

        // assert
        Table table = new Table(ds, "S_SURVEY");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("S_ID").isNotNull()
                .value("S_TITLE").isEqualTo("surv2")
                .value("S_DESCRIPTION").isEqualTo("desc")
                .value("S_DATE").isEqualTo("2022-02-20")
                .value("S_I_ID").isEqualTo("Susi")
                .value("S_QN_ID").isEqualTo(32L)
                .value("S_SURVEY_CONDUCTED").isEqualTo(false);
        repository.remove(survey);
    }

    @Test
    @Order(2)
    public void t002_findAll() {
        List<Survey> list = repository.findAll().list();

        Table table = new Table(ds, "S_SURVEY");
        Assertions.assertThat(list.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row(0)
                .value("S_ID").isNotNull()
                .value("S_TITLE").isEqualTo("Befragung")
                .value("S_DESCRIPTION").isEqualTo("Befragung for class")
                .value("S_I_ID").isEqualTo("Isabel Turner")
                .value("S_QN_ID").isEqualTo(2L)
                .value("S_SURVEY_CONDUCTED").isEqualTo(false);
        assertThat(table).row(table.getRowsList().size()-1)
                .value("S_TITLE").isEqualTo("Umfrage 4ahitm")
                .value("S_I_ID").isEqualTo("")
                .value("S_QN_ID").isEqualTo(20L);
    }

    @Test
    @Order(3)
    public void t003_findSurveyById() {
       // Survey survey = new Survey("surv", "desc", interviewer, questionnaire, date, false);

        //repository.save(survey);
        Survey survey1 = repository.findById(1L);

        Table table = new Table(ds, "S_Survey");

        assertThat(table).row(table.getRowsList().size()-1)
                .value("S_TITLE").isEqualTo("Umfrage 4ahitm")
                .value("S_I_ID").isEqualTo("")
                .value("S_QN_ID").isEqualTo(20L);
    }

    @Test
    @Order(4)
    public void t004_updateSurvey() {
        //arrange
        Survey survey = new Survey("surv1", "desc1", "Susi", questionnaire, date, false);
        //Survey survey2 = new Survey("surv2", "desc2", interviewer, questionnaire, date, false);

        //act
        survey = repository.save(survey);
        survey.title = "surv2";
        survey.description = "desc2";
        survey = repository.save(survey);

        //assert
        Table table = new Table(ds, "S_SURVEY");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("S_TITLE").isEqualTo("surv2")
                .value("S_DESCRIPTION").isEqualTo("desc2")
                .value("S_DATE").isEqualTo("2022-02-20")
                .value("S_I_ID").isEqualTo("Susi")
                .value("S_QN_ID").isEqualTo(33L)
                .value("S_SURVEY_CONDUCTED").isEqualTo(false);
        repository.remove(survey);
    }

    @Test
    @Order(5)
    public void t005_deleteSurvey() {
        Survey survey = new Survey("surv1", "desc1", "Susi", questionnaire, date, false);

        survey = repository.save(survey);

        Table table = new Table(ds, "S_SURVEY");
        int before = table.getRowsList().size();
        repository.remove(survey);
        table = new Table(ds, "S_SURVEY");
        int after = table.getRowsList().size();

        assertThat(before).isEqualTo(after + 1);
    }

    @Test
    @Order(6)
    public void t006_findByQuestionnaire() {
        Questionnaire que = new Questionnaire("Q1", "Lehrerfeedback", true, "Susi");
        questionnaireRepository.save(que);
        Survey survey = new Survey("surv2", "desc1", "Susi", questionnaire, date, false);
        survey = repository.save(survey);

        Table table = new Table(ds, "S_SURVEY");
        int before = table.getRowsList().size();

        survey = repository.save(survey);
        List<Survey> survs = repository.findByQuestionnaire(que);
        table = new Table(ds, "S_SURVEY");

        Assertions.assertThat(before + survs.size()).isEqualTo(table.getRowsList().size());
        questionnaireRepository.remove(que);
        repository.remove(survey);
    }

    @Test
    @Order(7)
    public void t007_findByInterviewer() {
        Survey survey = new Survey("surv2", "desc", "Susi", questionnaire, date, false);

        repository.save(survey);
        List<Survey> surveys = repository.findByInterviewer("Susi");

        assertThat(surveys.size()).isEqualTo(1);
        repository.remove(survey);
    }

    @Test
    @Order(8)
    public void t008_getQuestionnaireWithSurveyId() {
        Survey survey = new Survey("surv", "desc", "Susi", questionnaire, date, false);

        survey = repository.save(survey);
        Questionnaire questionnaire = repository.getQuestionnaireWithSurveyId(survey.id);

        Table table = new Table(ds, "S_Survey");

        assertThat(table).row()
                .value("S_TITLE").isEqualTo("Befragung")
                .value("S_DESCRIPTION").isEqualTo("Befragung for class")
                .value("S_I_ID").isEqualTo("Isabel Turner")
                .value("S_QN_ID").isEqualTo(2L)
                .value("S_SURVEY_CONDUCTED").isEqualTo(false);
        repository.remove(survey);
    }

    @Test
    @Order(9)
    public void t009_removeAll(){
        //repository.remove(repository.findById(9L));
        //questionnaireRepository.remove(questionnaireRepository.findById(31L));
        //questionnaireRepository.remove(questionnaireRepository.findById(30L));
        //questionnaireRepository.remove(questionnaireRepository.findById(29L));
        //questionnaireRepository.remove(questionnaireRepository.findById(27L));
        //questionnaireRepository.remove(questionnaireRepository.findById(45L));
        //questionnaireRepository.remove(questionnaireRepository.findById(44L));
        //questionnaireRepository.remove(questionnaireRepository.findById(43L));
        //questionnaireRepository.remove(questionnaireRepository.findById(42L));
        //questionnaireRepository.remove(questionnaireRepository.findById(41L));
        //questionnaireRepository.remove(questionnaireRepository.findById(40L));
        questionnaireRepository.remove(questionnaireRepository.findById(39L));
        questionnaireRepository.remove(questionnaireRepository.findById(38L));
        questionnaireRepository.remove(questionnaireRepository.findById(36L));
        questionnaireRepository.remove(questionnaireRepository.findById(35L));
        questionnaireRepository.remove(questionnaireRepository.findById(34L));
        questionnaireRepository.remove(questionnaireRepository.findById(24L));
        questionnaireRepository.remove(questionnaireRepository.findById(18L));
        questionnaireRepository.remove(questionnaireRepository.findById(17L));
        questionnaireRepository.remove(questionnaireRepository.findById(14L));

        questionnaireRepository.remove(questionnaireRepository.findById(15L));
        questionnaireRepository.remove(questionnaireRepository.findById(12L));
        questionnaireRepository.remove(questionnaireRepository.findById(11L));
        questionnaireRepository.remove(questionnaireRepository.findById(10L));
        questionnaireRepository.remove(questionnaireRepository.findById(9L));
    }
}
