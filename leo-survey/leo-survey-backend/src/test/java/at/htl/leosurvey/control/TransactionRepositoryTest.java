package at.htl.leosurvey.control;

import at.htl.leosurvey.entity.Questionnaire;
import at.htl.leosurvey.entity.Survey;
import at.htl.leosurvey.entity.Transaction;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TransactionRepositoryTest {
    @Inject
    TransactionRepository repository;

    @Inject
    SurveyRepository surveyRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    AgroalDataSource ds;

    Questionnaire questionnaire = new Questionnaire("Q1", "feedback", true, "Susi");
    LocalDate date = LocalDate.of(2022, 02, 20);
    Survey survey = new Survey("surv1", "desc1", "Susi", questionnaire, date, false);



    @Test
    @Order(1)
    public void t001_insertTransaction() {
        // arrange
        Transaction transaction = new Transaction("1003-2202-fght", true, survey);

        // act
        transaction = repository.save(transaction);
        // assert
        Table table = new Table(ds, "S_TRANSACTION");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("TR_ID").isNotNull()
                .value("TR_TRANSACTION_CODE").isEqualTo("1003-2202-fght")
                .value("T_S_ID").isEqualTo(15L)
                .value("TR_IS_USED").isEqualTo(true);
        repository.remove(transaction);
    }

    @Test
    @Order(2)
    public void t002_findAll() {
        List<Transaction> list = repository.findAll().list();

        Table table = new Table(ds, "S_TRANSACTION");
        Assertions.assertThat(list.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row(0)
                .value("TR_ID").isNotNull()
                .value("TR_TRANSACTION_CODE").isEqualTo("1003-2202-abcd")
                .value("T_S_ID").isEqualTo(1L)
                .value("TR_IS_USED").isEqualTo(false);
    }

    @Test
    @Order(3)
    public void t003_findTransactionById() {
        Transaction transaction = new Transaction("1003-2202-abce", true, survey);

        transaction = repository.save(transaction);
        Transaction transaction1 = repository.findById(transaction.id);

        Table table = new Table(ds, "S_TRANSACTION");

        assertThat(transaction1.id.longValue()).isEqualTo(19);
        repository.remove(transaction);
    }

    @Test
    @Order(4)
    public void t004_updateTransaction() {
        //arrange
        Transaction transaction = new Transaction("1003-2202-abcd", true, survey);

        //act
        transaction = repository.save(transaction);
        transaction.transactionCode = "1003-2202-abce";
        transaction = repository.save(transaction);

        //assert
        Table table = new Table(ds, "S_TRANSACTION");

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("TR_ID").isNotNull()
                .value("TR_TRANSACTION_CODE").isEqualTo("1003-2202-abce")
                .value("T_S_ID").isEqualTo(17L)
                .value("TR_IS_USED").isEqualTo(true);
        repository.remove(transaction);
    }

    @Test
    @Order(5)
    public void t005_deleteTransaction() {
        Transaction transaction = new Transaction("2022-2102-sjej", true, survey);

        transaction = repository.save(transaction);

        Table table = new Table(ds, "S_TRANSACTION");
        int before = table.getRowsList().size();
        repository.remove(transaction);
        table = new Table(ds, "S_TRANSACTION");
        int after = table.getRowsList().size();

        assertThat(before).isEqualTo(after + 1);
    }

    @Test
    @Order(6)
    public void t006_findBySurvey() {
        Survey survey2 = new Survey("surv2", "desc1", "Susi", questionnaire, date, false);
        survey2 = surveyRepository.save(survey2);
        Transaction transaction = new Transaction("1003-2202-abcd", true, survey2);

        Table table = new Table(ds, "S_TRANSACTION");
        int before = table.getRowsList().size();

        transaction = repository.save(transaction);
        List<Transaction> transactions = repository.findBySurvey(survey2);
        table = new Table(ds, "S_TRANSACTION");

        Assertions.assertThat(before + transactions.size()).isEqualTo(table.getRowsList().size());
        assertThat(table).row()
                .value("TR_ID").isNotNull()
                .value("TR_TRANSACTION_CODE").isEqualTo("1003-2202-abcd")
                .value("T_S_ID").isEqualTo(1L)
                .value("TR_IS_USED").isEqualTo(false);
        repository.remove(transaction);
        surveyRepository.remove(survey2);
    }

    @Test
    @Order(7)
    public void t007_generateTransactions(){
        Survey surv = surveyRepository.findById(1L);
        Table table = new Table(ds, "S_TRANSACTION");

        Long before = repository.findAll().count();
        repository.generateTransactions(2, surv);

        assertEquals(before + 2, repository.findAll().count());

        Transaction transaction = repository.findById(11L);
        Transaction transaction2 = repository.findById(10L);
        //repository.remove(transaction);
        //repository.remove(transaction2);

        System.out.println(table.getRowsList().size());
    }

    @Test
    @Order(8)
    public void t008_findSurveyWithTransaction(){
        Transaction transaction = repository.findById(1L);

        Survey survey = repository.findSurveyWithTransaction(transaction);

        Table table = new Table(ds, "S_SURVEY");

        assertThat(table).row()
                .value("S_TITLE").isEqualTo("Befragung")
                .value("S_DESCRIPTION").isEqualTo("Befragung for class")
                .value("S_I_ID").isEqualTo("Isabel Turner")
                .value("S_QN_ID").isEqualTo(2L)
                .value("S_SURVEY_CONDUCTED").isEqualTo(false);
    }

    @Test
    @Order(9)
    public void t009_getTransactionByCode(){
        Transaction transaction = repository.findById(1L);

        transaction = repository.getTransactionByCode(transaction.transactionCode);

        Table table = new Table(ds, "S_TRANSACTION");

        assertThat(table).row()
                .value("TR_ID").isNotNull()
                .value("TR_TRANSACTION_CODE").isEqualTo("1003-2202-abcd")
                .value("T_S_ID").isEqualTo(1L)
                .value("TR_IS_USED").isEqualTo(false);
    }

   @Test
   @Order(10)
    public void t010_removeAll(){
        surveyRepository.remove(survey);
        surveyRepository.remove(surveyRepository.findById(6L));
        surveyRepository.remove(surveyRepository.findById(17L));
        surveyRepository.remove(surveyRepository.findById(16L));
        surveyRepository.remove(surveyRepository.findById(15L));
        //surveyRepository.remove(surveyRepository.findById(14L));
        //surveyRepository.remove(surveyRepository.findById(12L));
       questionnaireRepository.remove(questionnaireRepository.findById(46L));
        questionnaireRepository.remove(questionnaireRepository.findById(45L));
        questionnaireRepository.remove(questionnaireRepository.findById(44L));
        questionnaireRepository.remove(questionnaireRepository.findById(43L));
        questionnaireRepository.remove(questionnaireRepository.findById(42L));
        questionnaireRepository.remove(questionnaireRepository.findById(41L));
        questionnaireRepository.remove(questionnaireRepository.findById(40L));
        questionnaireRepository.remove(questionnaireRepository.findById(32L));
        //questionnaireRepository.remove(questionnaireRepository.findById(31L));
        //questionnaireRepository.remove(questionnaireRepository.findById(30L));
        //questionnaireRepository.remove(questionnaireRepository.findById(29L));
        //questionnaireRepository.remove(questionnaireRepository.findById(28L));
        //questionnaireRepository.remove(questionnaireRepository.findById(27L));
        questionnaireRepository.remove(questionnaireRepository.findById(26L));
        questionnaireRepository.remove(questionnaireRepository.findById(25L));
        questionnaireRepository.remove(questionnaireRepository.findById(23L));
        questionnaireRepository.remove(questionnaireRepository.findById(22L));
        questionnaireRepository.remove(questionnaireRepository.findById(21L));
        questionnaireRepository.remove(questionnaireRepository.findById(20L));
        questionnaireRepository.remove(questionnaireRepository.findById(19L));
        questionnaireRepository.remove(questionnaireRepository.findById(13L));

       //repository.remove(repository.findById(19L));
       repository.remove(repository.findById(24L));
       repository.remove(repository.findById(23L));
       repository.remove(repository.findById(17L));
       repository.remove(repository.findById(16L));
       repository.remove(repository.findById(15L));
       repository.remove(repository.findById(14L));
       repository.remove(repository.findById(13L));
       repository.remove(repository.findById(12L));
       repository.remove(repository.findById(11L));
       repository.remove(repository.findById(10L));
       repository.remove(repository.findById(8L));

       //repository.remove(repository.findById(12L));
       //repository.remove(repository.findById(11L));
       //repository.remove(repository.findById(10L));
       //repository.remove(repository.findById(9L));
       repository.remove(repository.findById(7L));
       repository.remove(repository.findById(6L));
       questionnaireRepository.remove(questionnaireRepository.findById(33L));

   }

}
