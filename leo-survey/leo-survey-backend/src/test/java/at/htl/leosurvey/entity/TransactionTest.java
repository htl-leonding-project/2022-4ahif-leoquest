package at.htl.leosurvey.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void t010_addTransaction(){
        String id = "10001-2012-abcd";
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
        Transaction transaction = new Transaction(
                id,
                true,
                survey);

        assertThat(transaction.toString()).isEqualTo("Transaction{id=null, transactionCode='10001-2012-abcd', isUsed=true, survey=Survey{id=null, title='Befragung', description='Befragung for class', interviewer=Seppi, questionnaire=Questionnaire{id=null, interviewernull, name='Fragebogen xy'}, date=2022-01-20, surveyConducted=false}}");
    }

}
