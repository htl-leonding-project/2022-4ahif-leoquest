package at.htl.leosurvey.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {
    
    @Test
    void t010_addSurvey(){
        Questionnaire questionnaire = new Questionnaire(
                        "Fragebogen xy",
                        "Feedback 4ahitm",
                        false);
        Survey survey = new Survey(
                "Befragung",
                "Befragung for class",
                "Seppi",
                questionnaire,
                LocalDate.of(2022, 01, 20),
                false);
        
        assertThat(survey.toString())
                .isEqualTo("Survey{id=null, title='Befragung', description='Befragung for class', interviewer=Seppi, questionnaire=Questionnaire{id=null, interviewernull, name='Fragebogen xy'}, date=2022-01-20, surveyConducted=false}");
    }

}
