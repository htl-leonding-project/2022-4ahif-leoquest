package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.Interviewer;
import at.htl.leosurvey.entity.Question;
import at.htl.leosurvey.entity.QuestionType;
import at.htl.leosurvey.entity.Questionnaire;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class InterviewerRepositoryTest {

    @Inject
    InterviewerRepository repository;

    @Inject
    AgroalDataSource ds;

    @Test
    @Order(1)
    public void t100_insertInterviewer() {

        // arrange
        Interviewer interviewer = new Interviewer("Susi");

        //act
        repository.persist(interviewer);

        // assert
        Table table = new Table(ds, "S_INTERVIEWER");
        output(table).toConsole();

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("I_ID").isNotNull()
                .value("I_NAME").isEqualTo("Susi");
    }


    @Test
    @Order(2)
    void t200_findAll() {
        List<Interviewer> list = repository.findAll().list();

        Table table = new Table(ds, "S_INTERVIEWER");
        Assertions.assertThat(list.size()).isEqualTo(table.getRowsList().size());
        /*assertThat(table).row(0)
                .value("I_ID").isNotNull()
                .value("I_NAME").isEqualTo("HTL Leonding");
        assertThat(table).row(table.getRowsList().size()-1)
                .value("I_ID").isNotNull()
                .value("I_NAME").isEqualTo("Susi");*/

    }

    @Test
    @Order(3)
    public void t300_findInterviewerById() {
        //arrange
        Interviewer interviewer = new Interviewer("Susi");

        //act
        repository.save(interviewer);
        Interviewer interviewer1 = repository.findById(1L);

        //assert
        Table table = new Table(ds, "S_INTERVIEWER");
        output(table).toConsole();

        assertEquals(interviewer1.id.longValue(),1L);
    }

    @Test
    @Order(4)
    public void t400_updateInterviewer() {
        //arrange
        Interviewer interviewer = new Interviewer("Susi");
        Interviewer interviewer2 = new Interviewer("Hans");


        //act
        repository.save(interviewer);
        interviewer.name = interviewer2.name;
        repository.save(interviewer);

        //assert
        Table table = new Table(ds, "S_INTERVIEWER");
        output(table).toConsole();

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("I_ID").isNotNull()
                .value("I_NAME").isEqualTo("Hans");
    }

    @Test
    @Order(5)
    public void t500_deleteInterviewer() {
        //arrange
        Interviewer interviewer = new Interviewer("Susi");

        //act
        repository.save(interviewer);
        repository.delete(interviewer);

        //assert
        Table table = new Table(ds, "S_INTERVIEWER");
        output(table).toConsole();

        assertThat(table).row()
                .value("I_ID").isNotNull();

    }
}
