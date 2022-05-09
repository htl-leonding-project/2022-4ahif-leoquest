package at.htl.control;


import at.htl.entities.Survey;
import at.htl.entities.Transaction;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transaction> {
     @Inject
     SurveyRepository surveyRepository;

     @Transactional
     public List<Transaction> generateTransactionCode(Survey survey, int amount){
          final LinkedList<Transaction> transactions = new LinkedList<>();
          System.out.println(survey.getId());
          survey = surveyRepository.find("id", survey.getId()).firstResult();
          System.out.println(survey);

          Random r = new Random();
          String back = "";
          for(int i = 0; i <= amount; i++){
               for(int o = 0; o < 4; o++){
                    char c = (char)(r.nextInt(26) + 'a');
                    back += c;
               }
               Transaction transaction = new Transaction();
               transaction.setCode(back);
               transaction.setIsUsed(false);
               transaction.setSurvey(survey);
               getEntityManager().persist(transaction);
               transactions.add(transaction);
               back = "";
          }
          return transactions;
     }
}
