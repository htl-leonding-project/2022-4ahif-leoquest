package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.Questionnaire;
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
class QuestionnaireRepositoryTest {

    @Inject
    QuestionnaireRepository repository;

    @Inject
    AgroalDataSource ds;

    @Test
    @Order(1)
    public void t001_insertQuestionnaire() {
        // arrange
        Questionnaire questionnaire = new Questionnaire("Fragebogen xy", "Feedback 4ahitm", false, "Susi");

        // act
        repository.save(questionnaire);

        // assert
        Table table = new Table(ds, "S_QUESTIONNAIRE");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("QN_ID").isNotNull()
                .value("QN_NAME").isEqualTo("Fragebogen xy")
                .value("QN_DESCR").isEqualTo("Feedback 4ahitm");
        repository.remove(questionnaire);
    }

    @Test
    @Order(2)
    public void t002_findAll() {
        List<Questionnaire> list = repository.findAll().list();

        Table table = new Table(ds, "S_QUESTIONNAIRE");
        assertThat(list.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row(0)
                .value("QN_ID").isNotNull()
                .value("QN_DESCR").isEqualTo("Feedbackbogen für Lehrer")
                .value("QN_IS_PUBLIC").isEqualTo(true)
                .value("QN_NAME").isEqualTo("Lehrer-Feedbackbogen")
                .value("QN_I_ID").isEqualTo("HTL Leonding");
        assertThat(table).row(table.getRowsList().size()-1)
                .value("QN_ID").isNotNull()
                .value("QN_DESCR").isEqualTo("Feedback 4ahitm")
                .value("QN_IS_PUBLIC").isEqualTo(false)
                .value("QN_NAME").isEqualTo("Fragebogen xy")
                .value("QN_I_ID").isNotNull();
    }

    @Test
    @Order(3)
    public void t003_findQuestionnaireById() {
        Questionnaire questionnaire = new Questionnaire("Q1", "Lehrerfeedback", true, "Susi");

        questionnaire = repository.save(questionnaire);
        Questionnaire questionnaire1 = repository.findById(questionnaire.id);

        Table table = new Table(ds, "S_QUESTIONNAIRE");

        assertEquals(questionnaire1.id.longValue(),28);
        repository.remove(questionnaire);
    }

    @Test
    @Order(4)
    public void t004_updateQuestionnaire() {
        //arrange
        Questionnaire q1 = new Questionnaire("Q1", "descr", false);


        //act
        q1 = repository.save(q1);
        q1.name = "Q2";
        q1.description = "desc2";
        q1 = repository.save(q1);

        //assert
        Table table = new Table(ds, "S_QUESTIONNAIRE");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("QN_ID").isNotNull()
                .value("QN_NAME").isEqualTo("Q2");
        repository.remove(q1);

    }

    @Test
    @Order(5)
    public void t005_deleteQuestionnaire() {
        Questionnaire questionnaire = new Questionnaire("Q1", "Lehrerfeedback", false, "Susi");

        questionnaire = repository.save(questionnaire);

        Table table = new Table(ds, "S_QUESTIONNAIRE");
        int before = table.getRowsList().size();
        repository.remove(questionnaire);
        table = new Table(ds, "S_QUESTIONNAIRE");
        int after = table.getRowsList().size();

        assertThat(before).isEqualTo(after + 1);
    }

    @Test
    @Order(6)
    public void t006_findQuestionnairesByInterviewer(){
        String interviewer = "HTL Leonding";

        List<Questionnaire> questionnaires = repository.findByInterviewer(interviewer);

        assertThat(questionnaires.size()).isEqualTo(2);
    }

    @Test
    @Order(7)
    public void t007_findPublicQuestionnaires(){
        List<Questionnaire> questionnaires = repository.findPublicQuestionnaires();

        assertThat(questionnaires.size()).isEqualTo(7);
    }

    @Test
    @Order(8)
    public void t008_findPublicAndOwnedQuestionnaires(){
        List<Questionnaire> questionnaires = repository.findPublicAndOwnedQuestionnaires("Htl Leonding");

        assertThat(questionnaires.size()).isEqualTo(7);
    }

    @Test
    @Order(9)
    public void t009_findQuestionnairesByInterviewerId(){
       List<Questionnaire> questionnaires = repository.findByInterviewer("HTL Leonding");

        assertThat(questionnaires.size()).isEqualTo(2);
    }

    @Test
    @Order(10)
    public void t010_findQuestionnaire(){
        Questionnaire questionnaire = repository.findById(1L);

        Questionnaire questionnaires = repository.findQuestionnaire(questionnaire);

        Table table = new Table(ds, "S_QUESTIONNAIRE");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("QN_ID").isNotNull()
                .value("QN_NAME").isEqualTo("Fragebogen xy")
                .value("QN_DESCR").isEqualTo("Feedback 4ahitm")
                .value("QN_IS_PUBLIC").isEqualTo(false)
                .value("QN_I_ID").isNotNull();
    }

    @Test
    @Order(11)
    public void t011_duplicateQuestionnaire(){
        Questionnaire questionnaire = repository.findById(1L);

        Questionnaire questionnaires = repository.duplicateQuestionnaire(questionnaire, "Susi");

        Table table = new Table(ds, "S_QUESTIONNAIRE");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("QN_ID").isNotNull()
                .value("QN_NAME").isEqualTo("Lehrer-Feedbackbogen")
                .value("QN_DESCR").isEqualTo("Feedbackbogen für Lehrer")
                .value("QN_IS_PUBLIC").isEqualTo(false)
                .value("QN_I_ID").isNotNull();
        repository.remove(questionnaires);
    }

    @Test
    @Order(12)
    public void t012_removeAll(){
        repository.remove(repository.findById(8L));
        //repository.remove(repository.findById(31L));
        //repository.remove(repository.findById(30L));
        //repository.remove(repository.findById(29L));
        //repository.remove(repository.findById(27L));
        //repository.remove(repository.findById(45L));
        //repository.remove(repository.findById(44L));
        //repository.remove(repository.findById(43L));
        //repository.remove(repository.findById(42L));
        //repository.remove(repository.findById(41L));
        //repository.remove(repository.findById(40L));
        //repository.remove(repository.findById(39L));
        //repository.remove(repository.findById(38L));
        //repository.remove(repository.findById(36L));
        //repository.remove(repository.findById(35L));
        //repository.remove(repository.findById(34L));

    }
}

