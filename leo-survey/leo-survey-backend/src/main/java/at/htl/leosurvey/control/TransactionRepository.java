package at.htl.leosurvey.control;
import at.htl.leosurvey.entity.Answer;
import at.htl.leosurvey.entity.Survey;
import at.htl.leosurvey.entity.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class TransactionRepository implements PanacheRepository<Transaction> {

    @Inject
    EntityManager em;

    @Inject
    AnswerRepository answerRepository;

    public Transaction save(Transaction transaction) {
        return em.merge(transaction);
    }

    public void remove(Transaction transaction){
        List<Answer> answers = answerRepository.findByTransaction(transaction);
        for (Answer a : answers) {
            answerRepository.remove(a);
        }
        em.remove(em.contains(transaction) ? transaction : em.merge(transaction));
    }

    public List<Transaction> findBySurvey(Survey survey){
        return em.createQuery(
                "select t from Transaction t where t.survey.id = :id", Transaction.class)
                .setParameter("id", survey.id)
                .getResultList();
    }

    public File generateTransactions(int number, Survey survey){
        Transaction transaction;

        List<Transaction> transactions = new ArrayList<>();
        File file = new File("transactionCodes.csv");
        List<String> ids = new ArrayList<>(findAll()
                .stream()
                .map(t -> t.transactionCode)
                .collect(Collectors.toList()));

        System.out.println(ids);
        for (int i = 0; i < number; i++) {
            String id;

            do {
               id = generateTransactionId(survey.id);
            }while (ids.contains(id));


            transaction = new Transaction(id, false, survey);
            transactions.add(transaction);
            save(transaction);
            ids.add(id);
        }


        try (PrintWriter writer = new PrintWriter("transactionCodes.csv")) {

            StringBuilder sb = new StringBuilder();
            sb.append(number);
            sb.append(" Transactioncodes für ");
            sb.append(survey.title);
            sb.append("\n");
            sb.append(survey.description);
            sb.append("\n");
            sb.append("Fragebogen: ");
            sb.append(survey.questionnaire.name);
            sb.append("\n");
            sb.append("\n");

            for (Transaction t : transactions) {
                sb.append(t.transactionCode);
                sb.append('\n');
            }

            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }

    private String generateTransactionId(Long surveyId){
        surveyId = 1000 + surveyId;

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMM");
        String formatDateTime = LocalDateTime.now().format(format);

        String random = RandomStringUtils.randomAlphabetic(4);

        return surveyId.toString() + "-" + formatDateTime  + "-" + random;
    }

    public Survey findSurveyWithTransaction(Transaction transaction){
        if(transaction.isUsed){
            return null;
        }

        return em.createQuery(
                "select t.survey from Transaction t where t.transactionCode = :code", Survey.class)
                .setParameter("code", transaction.transactionCode)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public Transaction getTransactionByCode(String code){
        return em.createQuery(
                "select t from Transaction t where t.transactionCode = :code", Transaction.class)
                .setParameter("code", code)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
