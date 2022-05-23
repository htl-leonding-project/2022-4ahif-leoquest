package at.htl.leosurvey.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    @Test
    void t010_addAnswer(){
        String id = "1003-2012-abcd";
        Questionnaire questionnaire = new Questionnaire(
                "Fragebogen xy",
                "Feedback 4ahitm",
                false);
        Question question = new Question(
                "Der Lehrer ist...",
                4,
                QuestionType.SINGLECHOICE,
                questionnaire);
        Survey survey = new Survey(
                "Befragung",
                "Befragung for class",
                "Seppi",
                questionnaire,
                LocalDate.of(2022, 01, 20),
                false);
        Transaction transaction = new Transaction(
                id,
                true,
                survey);
        Answer answer = new Answer(question, "völlig richtig", transaction);

        assertThat(answer.toString()).isEqualTo("Answer{id=null, question=Question{id=null, text='Der Lehrer ist...', sequenceNumber=4, type=SINGLECHOICE, questionnaire=Questionnaire{id=null, interviewernull, name='Fragebogen xy'}}, answerText='völlig richtig', transaction=Transaction{id=null, transactionCode='1003-2012-abcd', isUsed=true, survey=Survey{id=null, title='Befragung', description='Befragung for class', interviewer=Seppi, questionnaire=Questionnaire{id=null, interviewernull, name='Fragebogen xy'}, date=2022-01-20, surveyConducted=false}}}");
    }

}
