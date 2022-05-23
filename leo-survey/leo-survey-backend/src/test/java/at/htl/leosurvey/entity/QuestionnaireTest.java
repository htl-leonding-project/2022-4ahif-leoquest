package at.htl.leosurvey.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionnaireTest {

    @Test
    void t010_addQuestionnaire() {
        Questionnaire questionnaire =
                new Questionnaire(
                        "Fragebogen xy",
                        "Feedback 4ahitm",
                        false);


        assertThat(questionnaire.toString())
                .isEqualTo("Questionnaire{id=null, interviewernull, name='Fragebogen xy'}");
    }
}
