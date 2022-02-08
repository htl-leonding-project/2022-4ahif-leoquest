package at.htl.control;

import at.htl.entity.Questionnaire;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class QuestionnaireTest {


    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    AgroalDataSource ds;

    @Order(100)
    @Test
    void persistAQuestion() {

        Questionnaire questionnaire = new Questionnaire("nameTest", "descrTest");
        questionnaireRepository.save(questionnaire);

        Table table = new Table(ds,"LD_QUESTIONNAIRE");
        Assertions.assertThat(table)
                .column("QN_DESC").value().isEqualTo("descrTest")
                .column("QN_NAME").value().isEqualTo("nameTest");
    }
}
