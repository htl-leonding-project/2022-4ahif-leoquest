package at.htl.control;

import at.htl.entity.Question;
import at.htl.entity.QuestionType;
import at.htl.entity.Questionnaire;
import at.htl.entity.Teacher;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class TeacherRepositoryTest {

    @Inject
    TeacherRepository teacherRepository;

    @Inject
    AgroalDataSource ds;

    @Test
    void GetTeacherById_Ok() {
        Teacher newTeacher = teacherRepository.findById(1L);

        assertThat(newTeacher.getName()).isEqualTo("Parmenter");
    }

    @Test
    void GetTeacherById_Fail() {
        Teacher newTeacher = teacherRepository.findById(2L);

        assertThat(newTeacher.getName()).doesNotMatch("Parmenter");
    }

    @Test
    void DeleteATeacher(){
        Teacher newTeacher = new Teacher("New Musterfrau");
        teacherRepository.save(newTeacher);

        assertThat((teacherRepository.findById(12L)).getName())
                .isEqualTo(newTeacher.getName());

        //teacherRepository.delete(newTeacher);

        //assertThat((teacherRepository.findById(12L)).getName())
        //        .doesNotMatch(newTeacher.getName());
    }
}
