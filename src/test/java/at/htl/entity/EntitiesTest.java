package at.htl.entity;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EntitiesTest {

    @Order(1000)
    @Test
    void createSimpleSurvey() {
        Survey survey = new Survey("Wann haben Sie Zeit?");
        AnswerOption a1 = new AnswerOption("1. Dezember", survey);
        AnswerOption a2 = new AnswerOption("2. Dezember", survey);
        AnswerOption a3 = new AnswerOption("3. Dezember", survey);
        AnswerOption a4 = new AnswerOption("4. Dezember", survey);

        assertThat(survey).isNotNull();
    }

    @Order(1010)
    @Test
    void answerSimpleSurvey() {
        Survey survey = new Survey("Wann haben Sie Zeit?");
        AnswerOption ao1 = new AnswerOption("1. Dezember", survey);
        AnswerOption ao2 = new AnswerOption("2. Dezember", survey);
        AnswerOption ao3 = new AnswerOption("3. Dezember", survey);
        AnswerOption ao4 = new AnswerOption("4. Dezember", survey);

        Answer a1 = new Answer(ao1, "Dagobert");

        assertThat(a1.answerOption.text).isEqualTo("1. Dezember");
    }
}