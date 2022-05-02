package at.htl.control;

import at.htl.entities.Questionnaire;
import at.htl.entities.Survey;
import at.htl.entities.Teacher;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherRepositoryTest {

    @Inject
    TeacherRepository teacherRepository;
    @Inject
    AgroalDataSource ds;



    @Test
    @Order(10)
    void createTeacherTest(){
        Table teacher = new Table(ds, "lq_teacher");

        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey s = new Survey(dt, q);
        teacherRepository.save(new Teacher("Teach", s));
        assertThat(teacher).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Teach")
                .value().isEqualTo(1);
    }
}
