package at.htl.control;

import at.htl.entities.Questionnaire;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.db.output.Outputs.output;

@QuarkusTest
public class MyTest {

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    AgroalDataSource ds;

    @Transactional
    @Test
    void createQuestionnaire() {

        Questionnaire q = new Questionnaire(1000L, "test", "Testdesc");
        questionnaireRepository.persistAndFlush(q);

        Table table = new Table(ds, "lq_questionnaire");
        output(table).toConsole();

    }
}
