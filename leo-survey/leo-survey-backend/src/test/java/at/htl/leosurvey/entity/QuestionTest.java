package at.htl.leosurvey.entity;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {

    @Test
    void addQuestion(){
        //arrange
        Questionnaire questionnaire = new Questionnaire(
                "LehrerFeedbackBogen",
                "FeedBackBogen für Lehrer",
                true);
        //act
        Question question = new Question(
                "Der Lehrer ist...",
                4,
                QuestionType.SINGLECHOICE,
                questionnaire);
        //assert
        assertThat(question.toString())
                .isEqualTo("Question{id=null, text='Der Lehrer ist...', " +
                        "sequenceNumber=4, " +
                        "type=SINGLECHOICE, " +
                        "questionnaire=Questionnaire{id=null, " +
                        "interviewernull, " +
                        "name='LehrerFeedbackBogen'}}");
    }


}

