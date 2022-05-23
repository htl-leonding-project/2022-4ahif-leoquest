package at.htl.leosurvey.entity;

import at.htl.leosurvey.control.AnswerOptionRepository;
import at.htl.leosurvey.control.QuestionRepository;
import at.htl.leosurvey.control.QuestionnaireRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswerOptionTest {
    
    @Test
    void t010_addAnswerOption(){
        Questionnaire questionnaire = new Questionnaire(
                "LehrerFeedbackBogen",
                "FeedBackBogen für Lehrer",
                true);
        Question question = new Question(
                "Der Lehrer ist...",
                4,
                QuestionType.SINGLECHOICE,
                questionnaire);
        AnswerOption answerOption = new AnswerOption(
                "völlig richtig",
                4,
                1,
                false,
                question);

        assertThat(answerOption.toString()).isEqualTo("AnswerOption{id=null, text='völlig richtig', value=4, sequenceNumber=1, isCorrectAnswer=false, question=Question{id=null, text='Der Lehrer ist...', sequenceNumber=4, type=SINGLECHOICE, questionnaire=Questionnaire{id=null, interviewernull, name='LehrerFeedbackBogen'}}}");
    }

}
