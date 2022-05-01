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

    @Transactional
    public ChosenOption save(ChosenOption chosenOption){
        System.out.println("Test " + chosenOption.getQuestion().getId());
        final Question q = questionRepository.findById(chosenOption.getQuestion().getId());
        chosenOption.getAnswerOption().setQuestion(q);
        var opasd = getEntityManager().merge(chosenOption);
        System.out.println(opasd);
        return opasd;
    }

    public List<ChosenOption> findAllOptions(){
        return listAll();
    }

    public ChosenOption findById(Long id) {
        return getEntityManager().find(ChosenOption.class, id);
    }

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
