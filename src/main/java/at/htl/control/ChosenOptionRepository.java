package at.htl.control;


import at.htl.entities.ChosenOption;
import at.htl.entities.Question;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ChosenOptionRepository implements PanacheRepository<ChosenOption> {


    @Inject
    QuestionRepository questionRepository;

    public List<ChosenOption> findChosenOptionsByQuestionnaire(long id){
        Query q = getEntityManager().createQuery("select co from " +
                "ChosenOption co where co.question.questionnaire.id = :id");
        q.setParameter("id", id);
        List<ChosenOption> chosenOptions = q.getResultList();
        return chosenOptions;
    }

    public List<ChosenOption> getByTrCode(String trcode){
        var co = this.find("transaction_code", trcode).list();
        return co;
    }

}
